package com.mobiauto.api.mapper.veiculo;

import com.mobiauto.api.dto.veiculo.VeiculoInputDTO;
import com.mobiauto.api.dto.veiculo.VeiculoOutputDTO;
import com.mobiauto.domain.model.Veiculo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VeiculoMapper {

    private final ModelMapper modelMapper;

    public VeiculoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Veiculo converterVeiculoInputDTOEmVeiculo(VeiculoInputDTO veiculoInputDTO){
        return modelMapper.map(veiculoInputDTO, Veiculo.class);
    }

    public VeiculoOutputDTO converterVeiculoEmVeiculoOutputDTO(Veiculo veiculo){
        return modelMapper.map(veiculo, VeiculoOutputDTO.class);
    }

    public List<VeiculoOutputDTO> converterListaVeiculoEmListaVeiculoOutputDTO(List<Veiculo> listaVeiculo){
        List<VeiculoOutputDTO> listaVeiculoOutputDTO = new ArrayList<>();
        listaVeiculo.forEach((lv)->{
            listaVeiculoOutputDTO.add(converterVeiculoEmVeiculoOutputDTO(lv));
        });
        return listaVeiculoOutputDTO;
    }

}
