package com.mobiauto.api.controller.atendimento;

import com.mobiauto.api.dto.atendimento.AtendimentoInputDTO;
import com.mobiauto.api.dto.atendimento.AtendimentoOutputDTO;
import com.mobiauto.api.mapper.atendimento.AtendimentoMapper;
import com.mobiauto.domain.service.atendimento.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {

    @Autowired
    private AtendimentoService service;

    @Autowired
    private AtendimentoMapper mapper;

    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(@RequestBody AtendimentoInputDTO atendimentoInputDTO,@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");

        service.salvar(mapper.converterAtendimentoInputDTOEmAtendimento(atendimentoInputDTO),token);
        return new ResponseEntity<String>("Atendimento registrado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AtendimentoOutputDTO>> listar(){
        return new ResponseEntity<List<AtendimentoOutputDTO>>(mapper.converterListaAtendimentoEmListaAtendimentoOutputDTO(service.listar()), HttpStatus.CREATED);
    }

}
