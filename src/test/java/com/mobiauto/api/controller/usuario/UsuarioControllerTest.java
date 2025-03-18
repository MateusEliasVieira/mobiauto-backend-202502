package com.mobiauto.api.controller.usuario;

import com.mobiauto.api.dto.revenda.RevendaInputDTO;
import com.mobiauto.api.dto.usuario.UsuarioInputDTO;
import com.mobiauto.api.dto.usuario.UsuarioOutputDTO;
import com.mobiauto.api.mapper.usuario.UsuarioMapper;
import com.mobiauto.domain.model.Usuario;
import com.mobiauto.domain.service.usuario.UsuarioService;
import com.mobiauto.utils.ValidadorDeSenha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioServico;

    @Mock
    private UsuarioMapper mapper;

    @Mock
    private ValidadorDeSenha validadorDeSenha;

    private MockMvc mockMvc;
    private Usuario usuario;
    private UsuarioInputDTO usuarioInputDTO;
    private UsuarioOutputDTO usuarioOutputDTO;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
        usuario = new Usuario();
        usuarioInputDTO = new UsuarioInputDTO();
        usuarioInputDTO.setSenha("Ma25!!");

        usuarioOutputDTO = new UsuarioOutputDTO();
    }

    @Test
    void salvar() throws Exception {

        // Mock do serviço e validador
        when(mapper.converterUsuarioInputDTOEmUsuario(any(UsuarioInputDTO.class))).thenReturn(usuario);
        when(usuarioServico.salvar(usuario, "token")).thenReturn(usuario);

        // Act & Assert
        mockMvc.perform(post("/usuario/salvar")
                        .header("Authorization", "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"senha\": \"Ma25!!\"}")) // Adicionando a senha no JSON
                 .andExpect(status().isCreated())
                .andExpect(content().string("Usuário cadastrado com sucesso!"));

        verify(usuarioServico, times(1)).salvar(usuario, "token");

    }

    @Test
    void listar() throws Exception {
        // Arrange
        when(usuarioServico.listar()).thenReturn(List.of(usuario));
        when(mapper.converterListaUsuarioEmListaUsuarioOutputDTO(anyList())).thenReturn(List.of(usuarioOutputDTO));

        // Act & Assert
        mockMvc.perform(get("/usuario/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());

        verify(usuarioServico, times(1)).listar();

    }

    @Test
    void listarPorID() throws Exception {
        // Arrange
        Long id = 1L;
        when(usuarioServico.listarPorId(id)).thenReturn(usuario);
        when(mapper.converterUsuarioEmUsuarioOutputDTO(usuario)).thenReturn(usuarioOutputDTO);

        // Act & Assert
        mockMvc.perform(get("/usuario/listar/id/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

        verify(usuarioServico, times(1)).listarPorId(id);

    }

    @Test
    void atualizar() throws Exception {
        // Arrange
        String token = "token";
        when(mapper.converterUsuarioInputDTOEmUsuario(any(UsuarioInputDTO.class))).thenReturn(usuario);
        doNothing().when(usuarioServico).atualizar(usuario, token);

        // Act & Assert
        mockMvc.perform(put("/usuario/atualizar")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Usuário atualizado com sucesso!"));

        verify(usuarioServico, times(1)).atualizar(usuario, token);
    }

    @Test
    void deletar() throws Exception {
        // Arrange
        Long id = 1L;
        doNothing().when(usuarioServico).deletar(id);
        // Act & Assert
        mockMvc.perform(delete("/usuario/deletar/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuário deletado com sucesso!"));

        verify(usuarioServico, times(1)).deletar(id);
    }
}
