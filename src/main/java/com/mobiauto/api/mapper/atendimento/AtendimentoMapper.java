package com.mobiauto.api.mapper.atendimento;

import com.mobiauto.api.dto.atendimento.AtendimentoInputDTO;
import com.mobiauto.api.dto.atendimento.AtendimentoOutputDTO;
import com.mobiauto.domain.model.Atendimento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AtendimentoMapper {

    private final ModelMapper modelMapper;

    public AtendimentoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Atendimento converterAtendimentoInputDTOEmAtendimento(AtendimentoInputDTO atendimentoInputDTO){
        return this.modelMapper.map(atendimentoInputDTO, Atendimento.class);
    }

    public AtendimentoOutputDTO converterAtendimentoEmAtendimentoOutputDTO(Atendimento atendimento){
        return this.modelMapper.map(atendimento, AtendimentoOutputDTO.class);
    }

    public List<AtendimentoOutputDTO> converterListaAtendimentoEmListaAtendimentoOutputDTO(List<Atendimento> listaAtendimento){
        List<AtendimentoOutputDTO> listaAtendimentoOutputDTO = new ArrayList<>();
        listaAtendimento.forEach((la)->{
            listaAtendimentoOutputDTO.add(converterAtendimentoEmAtendimentoOutputDTO(la));
        });
        return listaAtendimentoOutputDTO;
    }


}
