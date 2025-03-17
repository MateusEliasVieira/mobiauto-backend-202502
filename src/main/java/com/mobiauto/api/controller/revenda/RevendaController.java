package com.mobiauto.api.controller.revenda;

import com.mobiauto.api.dto.revenda.RevendaInputDTO;
import com.mobiauto.api.dto.revenda.RevendaOutputDTO;
import com.mobiauto.api.mapper.revenda.RevendaMapper;
import com.mobiauto.domain.service.revenda.RevendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/revenda")
public class RevendaController {

    @Autowired
    private RevendaService service;

    @Autowired
    private RevendaMapper mapper;

    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(@RequestBody RevendaInputDTO revendaInputDTO, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        service.salvar(mapper.converterRevendaInputDTOEmRevenda(revendaInputDTO),token);
        return new ResponseEntity<String>("Revenda adicionada com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<RevendaOutputDTO>> listar() {
        return new ResponseEntity<List<RevendaOutputDTO>>(mapper.converterListaRevendaEmListaRevendaOutputDTO(service.listar()), HttpStatus.OK);
    }

    @GetMapping("/listar/id/{id}")
    public ResponseEntity<RevendaOutputDTO> listarPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<RevendaOutputDTO>(mapper.converterRevendaEmRevendaOutputDTO(service.listarPorId(id)), HttpStatus.OK);
    }

    @GetMapping("/listar/cnpj/{cnpj}")
    public ResponseEntity<RevendaOutputDTO> listarPorId(@PathVariable("cnpj") String cnpj) {
        return new ResponseEntity<RevendaOutputDTO>(mapper.converterRevendaEmRevendaOutputDTO(service.listarPorCnpj(cnpj)), HttpStatus.OK);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody RevendaInputDTO revendaInputDTO) {
        service.atualizar(mapper.converterRevendaInputDTOEmRevenda(revendaInputDTO));
        return new ResponseEntity<String>("Revenda atualizada com sucesso!", HttpStatus.CREATED);
    }
    @PutMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return new ResponseEntity<String>("Revenda deletada com sucesso!", HttpStatus.NO_CONTENT);
    }


}
