package com.mobiauto.domain.service.usuario;

import com.mobiauto.domain.model.Usuario;

import java.util.List;

public interface UsuarioService {

    public Usuario salvar(Usuario usuario);
    public void atualizar(Usuario usuario);
    public void deletar(Long idUsuario);
    public List<Usuario> listar();
    public Usuario listarPorId(Long idUsuario);
    public Usuario listarPorEmail(String email);
    public Usuario login(Usuario usuario);
}