package com.mobiauto.domain.service.atendimento;

import com.mobiauto.domain.model.Atendimento;

import java.util.List;

public interface AtendimentoService {

    public void salvar(Atendimento atendimento);
    public List<Atendimento> listar();
}
