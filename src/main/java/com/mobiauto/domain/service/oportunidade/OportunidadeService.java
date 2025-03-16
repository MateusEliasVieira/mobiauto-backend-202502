package com.mobiauto.domain.service.oportunidade;

import com.mobiauto.api.dto.oportunidade.OportunidadeTransferenciaInputDTO;
import com.mobiauto.domain.model.Oportunidade;

import java.util.List;

public interface OportunidadeService {

    public void criar(Oportunidade oportunidade);
    public void concluir(Oportunidade oportunidade);
    public List<Oportunidade> listar();
    public void transferirOportunidade(OportunidadeTransferenciaInputDTO oportunidadeTransferenciaInputDTO);
    public Oportunidade listarPorId(Long id);
    public void deletar(Long id);
    public void atualizar(Oportunidade oportunidade);

}
