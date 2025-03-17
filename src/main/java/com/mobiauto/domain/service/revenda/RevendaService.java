package com.mobiauto.domain.service.revenda;

import com.mobiauto.domain.model.Revenda;

import java.util.List;

public interface RevendaService {
    public void salvar(Revenda revenda,String token);
    public List<Revenda> listar();
    public Revenda listarPorId(Long id);
    public Revenda listarPorCnpj(String cnpj);
    public Revenda atualizar(Revenda revenda);
    public void deletar(Long id);
}
