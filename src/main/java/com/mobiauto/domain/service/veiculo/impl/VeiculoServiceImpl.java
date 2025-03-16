package com.mobiauto.domain.service.veiculo.impl;

import com.mobiauto.domain.model.Veiculo;
import com.mobiauto.domain.repository.VeiculoRepository;
import com.mobiauto.domain.service.veiculo.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    @Override
    public void salvar(Veiculo veiculo) {
        repository.save(veiculo);
    }

    @Override
    public List<Veiculo> listar() {
        return repository.findAll();
    }
}
