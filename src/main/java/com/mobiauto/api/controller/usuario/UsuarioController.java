package com.mobiauto.api.controller.usuario;

import com.mobiauto.api.dto.usuario.UsuarioInputDTO;
import com.mobiauto.api.dto.usuario.UsuarioOutputDTO;
import com.mobiauto.api.mapper.usuario.UsuarioMapper;
import com.mobiauto.domain.model.Usuario;
import com.mobiauto.domain.service.usuario.UsuarioService;
import com.mobiauto.utils.ValidadorDeSenha;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioServico;

    @Autowired
    private UsuarioMapper mapper;

    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(@RequestBody @Valid UsuarioInputDTO usuarioInputDTO,  @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");

        new ValidadorDeSenha().isStrong(usuarioInputDTO.getSenha());
        usuarioServico.salvar(mapper.converterUsuarioInputDTOEmUsuario(usuarioInputDTO),token);
        return new ResponseEntity<String>("Usuário cadastrado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioOutputDTO>> listar() {
        return new ResponseEntity<List<UsuarioOutputDTO>>(mapper.converterListaUsuarioEmListaUsuarioOutputDTO(usuarioServico.listar()), HttpStatus.OK);
    }

    @GetMapping("/listar/id/{id}")
    public ResponseEntity<UsuarioOutputDTO> listarPorID(@PathVariable("id") Long id) {
        Usuario usuario = usuarioServico.listarPorId(id);
        return new ResponseEntity<UsuarioOutputDTO>(mapper.converterUsuarioEmUsuarioOutputDTO(usuario), HttpStatus.OK);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody UsuarioInputDTO usuarioInputDTO, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");

        usuarioServico.atualizar(mapper.converterUsuarioInputDTOEmUsuario(usuarioInputDTO),token);
        return new ResponseEntity<String>("Usuário atualizado com sucesso!", HttpStatus.CREATED);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") Long id) {
        usuarioServico.deletar(id);
        return new ResponseEntity<String>("Usuário deletado com sucesso!", HttpStatus.OK);
    }

}
