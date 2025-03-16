package com.mobiauto.domain.service.atendimento.impl;

import com.mobiauto.domain.model.Atendimento;
import com.mobiauto.domain.repository.AtendimentoRepository;
import com.mobiauto.domain.service.atendimento.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {

    @Autowired
    private AtendimentoRepository repository;

    @Override
    public void salvar(Atendimento atendimento) {
        atendimento.setDataAtendimento(LocalDateTime.now());
        repository.save(atendimento);
    }

    @Override
    public List<Atendimento> listar() {
        return repository.findAll();
    }
}
