package com.mobiauto.domain.service.atendimento.impl;

import com.mobiauto.domain.exception.RegrasDeNegocioException;
import com.mobiauto.domain.model.Atendimento;
import com.mobiauto.domain.model.Oportunidade;
import com.mobiauto.domain.model.Usuario;
import com.mobiauto.domain.repository.AtendimentoRepository;
import com.mobiauto.domain.repository.OportunidadeRepository;
import com.mobiauto.domain.repository.UsuarioRepository;
import com.mobiauto.domain.service.atendimento.AtendimentoService;
import com.mobiauto.utils.UsuarioLogado;
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
    public Atendimento salvar(Atendimento atendimento, String token) {
        // É preciso saber se essa oportunidade e o atendimento que será vinculado a ela, pertencem a mesma revenda que o usuário que acionou esse método
        Oportunidade oportunidade = oportunidadeRepository.findById(atendimento.getOportunidade().getIdOportunidade()).orElseThrow(() -> new RegrasDeNegocioException("Não foi encontrado a oportunidade informado para ser vinculada ao atendimento!"));
        Usuario usuario = usuarioRepository.findById(atendimento.getUsuario().getIdUsuario()).orElseThrow(() -> new RegrasDeNegocioException("Não foi encontrado o usuário para ser vinculado ao atendimento!"));

        String cnpjDonoDaOportunidade = oportunidade.getRevenda().getCnpj();
        String cnpjOndeUsuarioTrabalha = usuario.getRevenda().getCnpj();
        String cnpjUsuarioLogado = new UsuarioLogado().getCnpjTokenUsuarioLogado(token);

        if (cnpjUsuarioLogado.equals(cnpjOndeUsuarioTrabalha) && cnpjOndeUsuarioTrabalha.equals(cnpjDonoDaOportunidade)) {

            atendimento.setUsuario(usuario);
            usuario.getAtendimentos().add(atendimento);

            atendimento.setOportunidade(oportunidade);
            oportunidade.setAtendimento(atendimento);

            atendimento.setDataAtendimento(LocalDateTime.now());
            repository.save(atendimento);
            usuarioRepository.save(usuario);
            oportunidadeRepository.save(oportunidade);
        }else{
            throw new RegrasDeNegocioException("Para atender a uma oportunidade, você deve ser um usuário da revenda que possui a oportunidade a ser atendida!");
        }
        return atendimento;
    }

    @Override
    public List<Atendimento> listar() {
        return repository.findAll();
    }
}
