package com.mobiauto.api.controller.atendimento;

import com.mobiauto.api.dto.atendimento.AtendimentoInputDTO;
import com.mobiauto.api.dto.atendimento.AtendimentoOutputDTO;
import com.mobiauto.api.mapper.atendimento.AtendimentoMapper;
import com.mobiauto.domain.model.Atendimento;
import com.mobiauto.domain.service.atendimento.AtendimentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
class AtendimentoControllerTest {

    @Mock
    private AtendimentoService service;

    @Mock
    private AtendimentoMapper mapper;

    @InjectMocks
    private AtendimentoController controller;

    private MockMvc mockMvc;
    private Atendimento atendimento;
    private AtendimentoInputDTO atendimentoInputDTO;
    private AtendimentoOutputDTO atendimentoOutputDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        atendimento = new Atendimento();
        atendimentoInputDTO = new AtendimentoInputDTO();
        atendimentoOutputDTO = new AtendimentoOutputDTO();
    }

    @Test
    void salvar() throws Exception {
        when(mapper.converterAtendimentoInputDTOEmAtendimento(any(AtendimentoInputDTO.class))).thenReturn(atendimento);
        doNothing().when(service).salvar(atendimento, "token");

        mockMvc.perform(post("/atendimento/salvar")
                        .header("Authorization", "Bearer token")
                        .contentType("application/json")
                        .content("{}"))  // Exemplo de dados JSON
                .andExpect(status().isCreated())
                .andExpect(content().string("Atendimento registrado com sucesso!"));

        verify(service, times(1)).salvar(atendimento, "token");
    }

    @Test
    void listar() throws Exception {
        when(service.listar()).thenReturn(Arrays.asList(atendimento));
        when(mapper.converterListaAtendimentoEmListaAtendimentoOutputDTO(Arrays.asList(atendimento))).thenReturn(Arrays.asList(atendimentoOutputDTO));

        mockMvc.perform(get("/atendimento/listar"))
                .andExpect(status().isOk());

        verify(service, times(1)).listar();
    }
}
