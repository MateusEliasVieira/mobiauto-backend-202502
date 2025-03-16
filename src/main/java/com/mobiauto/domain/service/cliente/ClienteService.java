package com.mobiauto.domain.service.cliente;

import com.mobiauto.domain.model.Cliente;

import java.util.List;

public interface ClienteService {

    public void salvar(Cliente cliente);
    public List<Cliente> listar();
}
