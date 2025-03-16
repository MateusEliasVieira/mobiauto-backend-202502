package com.mobiauto.domain.service.cliente.impl;

import com.mobiauto.domain.model.Cliente;
import com.mobiauto.domain.repository.ClienteRepository;
import com.mobiauto.domain.service.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public void salvar(Cliente cliente) {
        repository.save(cliente);
    }

    @Override
    public List<Cliente> listar() {
        return repository.findAll();
    }
}
