package com.mobiauto.domain.service.oportunidade;

import com.mobiauto.api.dto.atendimento.AtendimentoOportunidadeInputDTO;
import com.mobiauto.api.dto.oportunidade.OportunidadeTransferenciaInputDTO;
import com.mobiauto.domain.model.Oportunidade;

import java.util.List;

public interface OportunidadeService {

    public void criar(Oportunidade oportunidade);
    public void concluir(Oportunidade oportunidade);
    public List<Oportunidade> listar();
    public void transferirOportunidade(OportunidadeTransferenciaInputDTO oportunidadeTransferenciaInputDTO,String token);
    public Oportunidade listarPorId(Long id);
    public void atualizar(Oportunidade oportunidade,String token);

}
