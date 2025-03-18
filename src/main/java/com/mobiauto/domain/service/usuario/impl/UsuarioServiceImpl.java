package com.mobiauto.domain.service.usuario.impl;

import com.mobiauto.domain.enums.RolePerfilUsuario;
import com.mobiauto.domain.exception.RegrasDeNegocioException;
import com.mobiauto.domain.model.Usuario;
import com.mobiauto.domain.repository.RevendaRepository;
import com.mobiauto.domain.repository.UsuarioRepository;
import com.mobiauto.domain.service.usuario.UsuarioService;
import com.mobiauto.security.JwtToken;
import com.mobiauto.utils.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private RevendaRepository revendaRepository;

    @Autowired
    private UsuarioLogado usuarioLogado;

    private final PasswordEncoder encriptadorDeSenha = new BCryptPasswordEncoder();

    @Transactional(readOnly = false)
    @Override
    public Usuario salvar(Usuario usuario, String token) {

        if (usuarioLogado.getPerfilTokenUsuarioLogado(token).equals(RolePerfilUsuario.ROLE_ADMINISTRADOR.toString())) {
            return prepararParaSalvarNovoUsuario(usuario);
        } else {
            // Verificamos se pelo menos a pessoa que está tentando adicionar um novo usuário seja um gerente ou proprietario da mesma loja que o novo usuário

            // Buscamos o cnpj da loja do novo usuário
            String cnpjRevendaDoNovoUsuario = revendaRepository.findById(usuario.getRevenda().getIdRevenda()).get().getCnpj();

            // Verificamos se são da mesma loja
            if (usuarioLogado.getCnpjTokenUsuarioLogado(token).equals(cnpjRevendaDoNovoUsuario)) {

                // Verificamos se a pessoa que esta querendo cadastrar o novo usuário é proprietario ou gerente dessa loja
                if (usuarioLogado.getPerfilTokenUsuarioLogado(token).equals(RolePerfilUsuario.ROLE_PROPRIETARIO.toString()) ||
                        usuarioLogado.getPerfilTokenUsuarioLogado(token).equals(RolePerfilUsuario.ROLE_GERENTE.toString())) {

                    // Podemos cadastrar
                    return prepararParaSalvarNovoUsuario(usuario);
                } else {
                    throw new RegrasDeNegocioException("Você não tem autorização para cadastrar um novo usuário. Apenas Proprietários e Gerentes podem fazer isso, e você é " + usuarioLogado.getPerfilTokenUsuarioLogado(token).split("_")[1] + ".");
                }

            } else {
                // Lojas diferentes
                throw new RegrasDeNegocioException("Você não tem autorização para cadastrar um novo usuário e em outra loja. Apenas Administradores podem fazer isso, e você é " + usuarioLogado.getPerfilTokenUsuarioLogado(token).split("_")[1] + ".");
            }
        }
    }

    @Transactional(readOnly = false)
    private Usuario prepararParaSalvarNovoUsuario(Usuario usuario) {
        if (repository.findByEmail(usuario.getEmail()).isEmpty()) {
            // usuario ainda não existe
            String tokenDoUsuario = JwtToken.gerarTokenJWT(usuario);
            usuario.setToken(tokenDoUsuario);
            usuario.setPerfil(usuario.getPerfil());
            usuario.setSenha(encriptadorDeSenha.encode(usuario.getPassword()));
            return repository.save(usuario);
        } else {
            throw new RegrasDeNegocioException("Já existe um usuário cadastrado com o email " + usuario.getEmail());
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void atualizar(Usuario usuario, String token) {

        if (usuario.getIdUsuario() != null) {
            Optional<Usuario> usuarioPorEmail = repository.findByEmail(usuario.getEmail());
            if (usuarioPorEmail.isPresent() && !usuarioPorEmail.get().getIdUsuario().equals(usuario.getIdUsuario())) {
                throw new RegrasDeNegocioException("Já existe um usuário cadastrado com o E-mail " + usuarioPorEmail.get().getEmail());
            } else {

                if (usuario.getIdUsuario() != null) {

                    Optional<Usuario> usuarioPorId = repository.findById(usuario.getIdUsuario());

                    // Verifica se mudou o perfil do usuário
                    if (!usuario.getPerfil().equals(usuarioPorId.get().getPerfil())) {

                        // Verificamos se quem mudou tem autorização para atualizar o perfil do usuário
                        if (usuarioLogado.getPerfilTokenUsuarioLogado(token).equals(RolePerfilUsuario.ROLE_ADMINISTRADOR.toString())) {

                            // Pode atualizar, pois é um adm
                            repository.save(usuario);
                        } else {

                            // Verificamos se o usuário logado é proprietário
                            if (usuarioLogado.getPerfilTokenUsuarioLogado(token).equals(RolePerfilUsuario.ROLE_PROPRIETARIO.toString())) {

                                // Buscamos o cnpj da loja do novo usuário
                                String cnpjRevendaDoNovoUsuario = revendaRepository.findById(usuario.getRevenda().getIdRevenda()).get().getCnpj();

                                // Verifificamos se o usuário logado proprietário é da mesma loja do usuário a ser atualizado
                                if (usuarioLogado.getCnpjTokenUsuarioLogado(token).equals(cnpjRevendaDoNovoUsuario)) {

                                    // É proprietário e pertence a mesma loja, pode atualizar
                                    repository.save(usuario);
                                } else {
                                    throw new RegrasDeNegocioException("Você não pertence a mesma loja do usuário a ser atualizado!");
                                }
                            } else {
                                throw new RegrasDeNegocioException("Você não tem permissão para atualizar o perfil deste usuário, pois você não é proprietário!");
                            }
                        }
                    } else {
                        // Não alterou o perfil do usuário, pode salvar!
                        repository.save(usuario);
                    }
                } else {
                    throw new RegrasDeNegocioException("Informe o id do usuário a ser atualizado!");
                }
            }
        } else {
            throw new RegrasDeNegocioException("Informe o código identificador único do usuário!");
        }

    }

    @Transactional(readOnly = false)
    @Override
    public void deletar(Long idUsuario) {
        repository.findById(idUsuario).ifPresentOrElse((usuario) -> {
            repository.deleteById(idUsuario);
        }, () -> {
            throw new RegrasDeNegocioException("Não foi possível deletar este usuário, pois ele não existe!");
        });
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario listarPorId(Long idUsuario) {
        Optional<Usuario> UsuarioOptional = repository.findById(idUsuario);
        return UsuarioOptional.orElseThrow(() -> new RegrasDeNegocioException("Usuário não encontrado!"));
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario listarPorEmail(String email) {
        Optional<Usuario> UsuarioOptional = repository.findByEmail(email);
        return UsuarioOptional.orElseThrow(() -> new RegrasDeNegocioException("Usuário não encontrado!"));
    }

    @Transactional(readOnly = false)
    @Override
    public Usuario login(Usuario usuario) {
        usuario.setToken(JwtToken.gerarTokenJWT(usuario));
        return repository.save(usuario);
    }

}
