package com.mobiauto.domain.service.revenda.impl;

import com.mobiauto.domain.enums.RolePerfilUsuario;
import com.mobiauto.domain.exception.RegrasDeNegocioException;
import com.mobiauto.domain.model.Revenda;
import com.mobiauto.domain.repository.RevendaRepository;
import com.mobiauto.domain.service.revenda.RevendaService;
import com.mobiauto.utils.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevendaServiceImpl implements RevendaService {

    @Autowired
    private RevendaRepository repository;

    @Override
    public void salvar(Revenda revenda) {
        validarAdministrador();
        repository.save(revenda);
    }

    @Override
    public List<Revenda> listar() {
        if (isAdministrador()) {
            return repository.findAll();
        } else {
            return List.of(buscarRevendaUsuario());
        }
    }

    @Override
    public Revenda listarPorId(Long id) {
        Revenda revenda = repository.findById(id)
                .orElseThrow(() -> new RegrasDeNegocioException("Revenda não encontrada!"));

        validarAcessoRevenda(revenda);
        return revenda;
    }

    @Override
    public Revenda listarPorCnpj(String cnpj) {
        Revenda revenda = repository.findByCnpj(cnpj)
                .orElseThrow(() -> new RegrasDeNegocioException("Revenda não encontrada!"));

        validarAcessoRevenda(revenda);
        return revenda;
    }

    @Override
    public Revenda atualizar(Revenda revenda) {
        validarAcessoRevenda(revenda);
        return repository.save(revenda);
    }

    @Override
    public void deletar(Long id) {
        validarAdministrador();
        listarPorId(id); // Se não encontrar, lança exceção
        repository.deleteById(id);
    }

    // Método para validar se o usuário tem acesso à revenda específica.
    private void validarAcessoRevenda(Revenda revenda) {
        if (!isAdministrador()) {
            String cnpjUsuario = UsuarioLogado.getCnpjTokenUsuarioLogado();
            if (!revenda.getCnpj().equals(cnpjUsuario)) {
                throw new RegrasDeNegocioException("Usuário não tem permissão para acessar essa revenda!");
            }
        }
    }


    // Método para buscar a revenda associada ao usuário logado.
    private Revenda buscarRevendaUsuario() {
        String cnpjUsuario = UsuarioLogado.getCnpjTokenUsuarioLogado();
        return repository.findByCnpj(cnpjUsuario)
                .orElseThrow(() -> new RegrasDeNegocioException("Revenda vinculada ao usuário não encontrada!"));
    }

    // Método para verificar se o usuário logado é administrador.
    private boolean isAdministrador() {
        return UsuarioLogado.getPerfilTokenUsuarioLogado().equals(RolePerfilUsuario.ROLE_ADMINISTRADOR.toString());
    }


    // Método para garantir que apenas administradores executem determinadas ações.
    private void validarAdministrador() {
        if (!isAdministrador()) {
            throw new RegrasDeNegocioException("Somente administradores podem realizar esta ação!");
        }
    }
}
