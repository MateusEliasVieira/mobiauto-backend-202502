package com.mobiauto.api.mapper.oportunidade;

import com.mobiauto.api.dto.oportunidade.OportunidadeConcluirInputDTO;
import com.mobiauto.api.dto.oportunidade.OportunidadeCriarInputDTO;
import com.mobiauto.api.dto.oportunidade.OportunidadeOutputDTO;
import com.mobiauto.domain.model.Oportunidade;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OportunidadeMapper {

    private final ModelMapper modelMapper;

    public OportunidadeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Oportunidade converterOportunidadeCriarInputDTOEmOportunidade(OportunidadeCriarInputDTO oportunidadeCriarInputDTO) {
        return modelMapper.map(oportunidadeCriarInputDTO, Oportunidade.class);
    }

    public Oportunidade converterOportunidadeConcluirInputDTOEmOportunidade(OportunidadeConcluirInputDTO oportunidadeConcluirInputDTO) {
        return modelMapper.map(oportunidadeConcluirInputDTO, Oportunidade.class);
    }

    public OportunidadeOutputDTO converterOportunidadeEmOportunidadeOutputDTO(Oportunidade oportunidade) {
        return modelMapper.map(oportunidade, OportunidadeOutputDTO.class);
    }

    public List<OportunidadeOutputDTO> converterListaOportunidadeEmListaOportunidadeOutputDTO(List<Oportunidade> listaOportunidade) {
        List<OportunidadeOutputDTO> listaOportunidadeOutputDTO = new ArrayList<>();
        listaOportunidade.forEach((lo)->{
            listaOportunidadeOutputDTO.add(converterOportunidadeEmOportunidadeOutputDTO(lo));
        });
        return listaOportunidadeOutputDTO;
    }


}
