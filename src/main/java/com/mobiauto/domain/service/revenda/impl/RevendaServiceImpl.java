package com.mobiauto.domain.service.revenda.impl;

import com.mobiauto.domain.exception.RegrasDeNegocioException;
import com.mobiauto.domain.model.Revenda;
import com.mobiauto.domain.repository.RevendaRepository;
import com.mobiauto.domain.service.revenda.RevendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevendaServiceImpl implements RevendaService {

    @Autowired
    private RevendaRepository repository;

    @Override
    public void salvar(Revenda revenda, String token) {
        if(repository.findByCnpj(revenda.getCnpj()).isEmpty()){
            repository.save(revenda);
        }else{
            throw new RegrasDeNegocioException("Já existe uma revenda cadastrada com o CNPJ "+revenda.getCnpj());
        }
    }

    @Override
    public List<Revenda> listar() {
        return repository.findAll();
    }

    @Override
    public Revenda listarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RegrasDeNegocioException("Revenda não encontrada!"));
    }

    @Override
    public Revenda listarPorCnpj(String cnpj) {
        return repository.findByCnpj(cnpj)
                .orElseThrow(() -> new RegrasDeNegocioException("Revenda não encontrada!"));
    }

    @Override
    public Revenda atualizar(Revenda revenda) {
        return repository.save(revenda);
    }

    @Override
    public void deletar(Long id) {
        listarPorId(id); // Se não encontrar, lança exceção
        repository.deleteById(id);
    }

}
