package com.mobiauto.domain.service.atendimento;

import com.mobiauto.domain.model.Atendimento;

import java.util.List;

public interface AtendimentoService {

    public void salvar(Atendimento atendimento,String token);
    public List<Atendimento> listar();
}
