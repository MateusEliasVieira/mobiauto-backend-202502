package com.mobiauto.api.controller.atendimento;

import com.mobiauto.api.dto.atendimento.AtendimentoInputDTO;
import com.mobiauto.api.mapper.atendimento.AtendimentoMapper;
import com.mobiauto.domain.service.atendimento.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {

    @Autowired
    private AtendimentoService service;

    @Autowired
    private AtendimentoMapper mapper;

    @PostMapping("/add")
    public ResponseEntity<String> salvar(@RequestBody AtendimentoInputDTO atendimentoInputDTO){
        service.salvar(mapper.converterAtendimentoInputDTOEmAtendimento(atendimentoInputDTO));
        return new ResponseEntity<String>("Atendimento registrado com sucesso!", HttpStatus.CREATED);
    }

}
