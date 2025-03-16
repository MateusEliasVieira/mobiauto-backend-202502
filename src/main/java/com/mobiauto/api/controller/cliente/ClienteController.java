package com.mobiauto.api.controller.cliente;

import com.mobiauto.api.dto.cliente.ClienteInputDTO;
import com.mobiauto.api.dto.cliente.ClienteOutputDTO;
import com.mobiauto.api.mapper.cliente.ClienteMapper;
import com.mobiauto.domain.service.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Autowired
    private ClienteMapper mapper;

    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(@RequestBody ClienteInputDTO clienteInputDTO) {
        service.salvar(mapper.converterClienteInputDTOEmCliente(clienteInputDTO));
        return new ResponseEntity<String>("Cliente adicionado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ClienteOutputDTO>> listar(){
        return new ResponseEntity<List<ClienteOutputDTO>>(mapper.converterListaClienteEmListaClienteOutputDTO(service.listar()),HttpStatus.OK);
    }

}
