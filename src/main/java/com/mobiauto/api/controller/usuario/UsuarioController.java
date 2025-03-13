package com.mobiauto.api.controller.usuario;

import com.mobiauto.api.controller.utils.Mensagem;
import com.mobiauto.api.dto.usuario.UsuarioAtualizacaoInputDTO;
import com.mobiauto.api.dto.usuario.UsuarioInputDTO;
import com.mobiauto.api.dto.usuario.UsuarioOutputDTO;
import com.mobiauto.api.mapper.usuario.UsuarioMapeador;
import com.mobiauto.domain.model.Usuario;
import com.mobiauto.domain.service.usuario.UsuarioService;
import com.mobiauto.utils.Resposta;
import com.mobiauto.utils.ValidadorDeSenha;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioServico;

    @PostMapping("/add")
    public ResponseEntity<Mensagem> salvar(@RequestBody @Valid UsuarioInputDTO usuarioInputDTO) {
        ValidadorDeSenha.isStrong(usuarioInputDTO.getSenha());
        usuarioServico.salvar(UsuarioMapeador.converterUsuarioEntradaDTOEmUsuario(usuarioInputDTO));
        return new ResponseEntity<Mensagem>(new Mensagem(Resposta.USUARIO_CAD_OK), HttpStatus.CREATED);
    }

    @DeleteMapping("/del")
    public ResponseEntity<?> deletar(@RequestParam("id") Long id) {
        usuarioServico.deletarUsuarioPorId(id);
        return new ResponseEntity<Mensagem>(new Mensagem("Usuário deletado com sucesso!"), HttpStatus.OK);
    }

    @PutMapping("/up")
    public ResponseEntity<Mensagem> atualizar(@RequestBody @Valid UsuarioAtualizacaoInputDTO usuarioAtualizacaoEntradaDTO) {
        usuarioServico.atualizarUsuario(UsuarioMapeador.converterUsuarioUpdateEntradaDTOEmUsuario(usuarioAtualizacaoEntradaDTO));
        return new ResponseEntity<Mensagem>(new Mensagem(Resposta.USUARIO_UP_OK), HttpStatus.CREATED);
    }

    @GetMapping("/id")
    public ResponseEntity<?> pesquisarUsuarioPorID(@RequestParam("id") Long id) {
        Usuario usuario = usuarioServico.buscarUsuarioPorId(id);
        return new ResponseEntity<UsuarioOutputDTO>(UsuarioMapeador.converterUsuarioEmUsuarioSaidaDTO(usuario), HttpStatus.OK);
    }

}
