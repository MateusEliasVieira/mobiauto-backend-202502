package com.mobiauto.domain.service.usuario;

import com.mobiauto.domain.model.Usuario;

public interface UsuarioService {

    public Usuario salvar(Usuario usuario);
    public void atualizarUsuario(Usuario usuario);
    public void deletarUsuarioPorId(Long idUsuario);
    public Usuario buscarUsuarioPorId(Long idUsuario);
    public Usuario buscarUsuarioPorEmail(String email);
    public Usuario login(Usuario usuario);
}