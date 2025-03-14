package com.mobiauto.api.mapper.revenda;

import com.mobiauto.api.dto.revenda.RevendaInputDTO;
import com.mobiauto.api.dto.revenda.RevendaOutputDTO;
import com.mobiauto.domain.model.Revenda;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RevendaMapper {

    private final ModelMapper modelMapper;

    public RevendaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Revenda converterRevendaInputDTOEmRevenda(RevendaInputDTO revendaInputDTO){
        return this.modelMapper.map(revendaInputDTO, Revenda.class);
    }

    public RevendaOutputDTO converterRevendaEmRevendaOutputDTO(Revenda revenda){
        return this.modelMapper.map(revenda, RevendaOutputDTO.class);
    }

    public List<RevendaOutputDTO> converterListaRevendaEmListaRevendaOutputDTO(List<Revenda> listaRevenda){
        List<RevendaOutputDTO> listaRevendaOutputDTO = new ArrayList<>();
        listaRevenda.forEach(lr -> {
            listaRevendaOutputDTO.add(converterRevendaEmRevendaOutputDTO(lr));
        });
        return listaRevendaOutputDTO;
    }

}
