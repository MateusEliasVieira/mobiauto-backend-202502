package com.mobiauto.api.controller.veiculo;

import com.mobiauto.api.dto.veiculo.VeiculoInputDTO;
import com.mobiauto.api.dto.veiculo.VeiculoOutputDTO;
import com.mobiauto.api.mapper.veiculo.VeiculoMapper;
import com.mobiauto.domain.service.veiculo.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoMapper mapper;

    @Autowired
    private VeiculoService service;

    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(VeiculoInputDTO veiculoInputDTO){
        service.salvar(mapper.converterVeiculoInputDTOEmVeiculo(veiculoInputDTO));
        return new ResponseEntity<String>("Veículo adicionado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<VeiculoOutputDTO>> listar(){
        return new ResponseEntity<List<VeiculoOutputDTO>>(mapper.converterListaVeiculoEmListaVeiculoOutputDTO(service.listar()),HttpStatus.OK);
    }

}
