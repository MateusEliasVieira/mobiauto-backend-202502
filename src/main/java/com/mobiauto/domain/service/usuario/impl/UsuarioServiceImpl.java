package com.mobiauto.domain.service.usuario.impl;

import com.mobiauto.domain.exception.RegrasDeNegocioException;
import com.mobiauto.domain.model.Usuario;
import com.mobiauto.domain.repository.UsuarioRepository;
import com.mobiauto.domain.service.usuario.UsuarioService;
import com.mobiauto.security.JwtToken;
import com.mobiauto.utils.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    private final PasswordEncoder encriptadorDeSenha = new BCryptPasswordEncoder();

    @Transactional(readOnly = false)
    @Override
    public Usuario salvar(Usuario usuario) {
        if (repository.findByEmail(usuario.getEmail()).isEmpty()) {
            // usuario ainda não existe
            String tokenDoUsuario = JwtToken.gerarTokenJWT(usuario);
            usuario.setToken(tokenDoUsuario);
            usuario.setPerfil(usuario.getPerfil());
            usuario.setSenha(encriptadorDeSenha.encode(usuario.getPassword()));
            Usuario usuarioSalvo = repository.save(usuario);
            if (usuarioSalvo.getIdUsuario() == null) {
                throw new RegrasDeNegocioException(Resposta.ERRO_SALVAR_USUARIO + usuario.getNome());
            } else {
                return usuarioSalvo;
            }
        } else {
            throw new RegrasDeNegocioException("Já existe um usuário cadastrado com o email " + usuario.getEmail());
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void atualizarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioPorEmail = repository.findByEmail(usuario.getEmail());
        if (usuarioPorEmail.isPresent() && !usuarioPorEmail.get().getIdUsuario().equals(usuario.getIdUsuario())) {
            throw new RegrasDeNegocioException("Já existe um usuário cadastrado com o E-mail " + usuarioPorEmail.get().getEmail());
        }
        repository.atualizarUsuarioPorId(usuario.getIdUsuario(), usuario.getNome(), usuario.getEmail(), usuario.getPerfil());
    }

    @Override
    public void deletarUsuarioPorId(Long idUsuario) {
        repository.findById(idUsuario).ifPresentOrElse((usuario) -> {
            repository.deleteById(idUsuario);
        }, () -> {
            throw new RegrasDeNegocioException("Não foi possível deletar este usuário, pois ele não existe!");
        });
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario buscarUsuarioPorId(Long idUsuario) {
        Optional<Usuario> UsuarioOptional = repository.findById(idUsuario);
        return UsuarioOptional.orElseThrow(() -> new RegrasDeNegocioException("Usuário não encontrado!"));
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
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
