package com.mobiauto.api.mapper.cliente;

import com.mobiauto.api.dto.cliente.ClienteInputDTO;
import com.mobiauto.api.dto.cliente.ClienteOutputDTO;
import com.mobiauto.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteMapper {

    private final ModelMapper modelMapper;

    public ClienteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Cliente converterClienteInputDTOEmCliente(ClienteInputDTO clienteInputDTO) {
        return modelMapper.map(clienteInputDTO, Cliente.class);
    }

    public ClienteOutputDTO converterClienteEmClienteOutputDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteOutputDTO.class);
    }

    public List<ClienteOutputDTO> converterListaClienteEmListaClienteOutputDTO(List<Cliente> listaCliente) {
        List<ClienteOutputDTO> listaClienteOutputDTO = new ArrayList<>();
        listaCliente.forEach((lc) -> {
            listaClienteOutputDTO.add(converterClienteEmClienteOutputDTO(lc));
        });
        return listaClienteOutputDTO;
    }

}
