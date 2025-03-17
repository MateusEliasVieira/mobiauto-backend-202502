package com.mobiauto.api.controller.oportunidade;

import com.mobiauto.api.dto.oportunidade.OportunidadeConcluirInputDTO;
import com.mobiauto.api.dto.oportunidade.OportunidadeCriarInputDTO;
import com.mobiauto.api.dto.oportunidade.OportunidadeOutputDTO;
import com.mobiauto.api.dto.oportunidade.OportunidadeTransferenciaInputDTO;
import com.mobiauto.api.mapper.oportunidade.OportunidadeMapper;
import com.mobiauto.domain.service.oportunidade.OportunidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oportunidade")
public class OportunidadeController {

    @Autowired
    private OportunidadeMapper mapper;

    @Autowired
    private OportunidadeService service;

    @PostMapping("/criar")
    public ResponseEntity<String> criar(@RequestBody OportunidadeCriarInputDTO oportunidadeCriarInputDTO){
        service.criar(mapper.converterOportunidadeCriarInputDTOEmOportunidade(oportunidadeCriarInputDTO));
        return new ResponseEntity<String>("Nova oportunidade criada com sucesso!", HttpStatus.CREATED);
    }

    @PostMapping("/concluir")
    public ResponseEntity<String> concluir(@RequestBody OportunidadeConcluirInputDTO oportunidadeConcluirInputDTO){
        service.concluir(mapper.converterOportunidadeConcluirInputDTOEmOportunidade(oportunidadeConcluirInputDTO));
        return new ResponseEntity<String>("Oportunidade concluída com sucesso!", HttpStatus.CREATED);
    }

    @PostMapping("/transferir")
    public ResponseEntity<String> transferir(@RequestBody OportunidadeTransferenciaInputDTO oportunidadeTransferenciaInputDTO,@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");

        service.transferirOportunidade(oportunidadeTransferenciaInputDTO,token);
        return new ResponseEntity<String>("Oportunidade transferida com sucesso!", HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<OportunidadeOutputDTO>> listar(){
        return new ResponseEntity<List<OportunidadeOutputDTO>>(mapper.converterListaOportunidadeEmListaOportunidadeOutputDTO(service.listar()),HttpStatus.OK);
    }

    @GetMapping("/listar/id/{id}")
    public ResponseEntity<OportunidadeOutputDTO> listar(@PathVariable("id") Long id){
        return new ResponseEntity<OportunidadeOutputDTO>(mapper.converterOportunidadeEmOportunidadeOutputDTO(service.listarPorId(id)),HttpStatus.OK);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody OportunidadeCriarInputDTO oportunidadeInputDTO,@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");

        service.atualizar(mapper.converterOportunidadeCriarInputDTOEmOportunidade(oportunidadeInputDTO),token);
        return new ResponseEntity<String>("Oportunidade atualizada com sucesso!", HttpStatus.CREATED);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") Long id){
        service.deletar(id);
        return new ResponseEntity<String>("Oportunidade deletada com sucesso!", HttpStatus.NO_CONTENT);
    }

}
