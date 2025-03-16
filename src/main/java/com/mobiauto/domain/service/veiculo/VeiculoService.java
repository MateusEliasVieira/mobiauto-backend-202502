package com.mobiauto.domain.service.veiculo;

import com.mobiauto.domain.model.Veiculo;

import java.util.List;

public interface VeiculoService {

    public void salvar(Veiculo veiculo);
    public List<Veiculo> listar();
}
