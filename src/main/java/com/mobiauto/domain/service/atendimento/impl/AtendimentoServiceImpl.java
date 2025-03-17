package com.mobiauto.domain.service.atendimento.impl;

import com.mobiauto.domain.exception.RegrasDeNegocioException;
import com.mobiauto.domain.model.Atendimento;
import com.mobiauto.domain.model.Oportunidade;
import com.mobiauto.domain.model.Usuario;
import com.mobiauto.domain.repository.AtendimentoRepository;
import com.mobiauto.domain.repository.OportunidadeRepository;
import com.mobiauto.domain.repository.UsuarioRepository;
import com.mobiauto.domain.service.atendimento.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {

    @Autowired
    private AtendimentoRepository repository;

    @Autowired
    private OportunidadeRepository oportunidadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void salvar(Atendimento atendimento) {
        Oportunidade oportunidade = oportunidadeRepository.findById(atendimento.getOportunidade().getIdOportunidade()).orElseThrow(()->new RegrasDeNegocioException("Não foi encontrado a oportunidade informado para ser vinculada ao atenidmento!"));
        Usuario usuario = usuarioRepository.findById(atendimento.getUsuario().getIdUsuario()).orElseThrow(()->new RegrasDeNegocioException("Não foi encontrado o usuário para ser vinculado ao atendimento!"));

        atendimento.setUsuario(usuario);
        usuario.getAtendimentos().add(atendimento);

        atendimento.setOportunidade(oportunidade);
        oportunidade.setAtendimento(atendimento);

        atendimento.setDataAtendimento(LocalDateTime.now());
        repository.save(atendimento);
        usuarioRepository.save(usuario);
        oportunidadeRepository.save(oportunidade);
    }

    @Override
    public List<Atendimento> listar() {
        return repository.findAll();
    }
}
