package com.mobiauto.api.controller.oportunidade;

import com.mobiauto.api.dto.oportunidade.OportunidadeConcluirInputDTO;
import com.mobiauto.api.dto.oportunidade.OportunidadeCriarInputDTO;
import com.mobiauto.api.dto.oportunidade.OportunidadeOutputDTO;
import com.mobiauto.api.dto.oportunidade.OportunidadeTransferenciaInputDTO;
import com.mobiauto.api.mapper.oportunidade.OportunidadeMapper;
import com.mobiauto.domain.model.Oportunidade;
import com.mobiauto.domain.service.oportunidade.OportunidadeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OportunidadeControllerTest {

    @InjectMocks
    private OportunidadeController oportunidadeController;

    @Mock
    private OportunidadeService service;

    @Mock
    private OportunidadeMapper mapper;

    private OportunidadeCriarInputDTO oportunidadeCriarInputDTO;
    private OportunidadeConcluirInputDTO oportunidadeConcluirInputDTO;
    private OportunidadeTransferenciaInputDTO oportunidadeTransferenciaInputDTO;
    private OportunidadeOutputDTO oportunidadeOutputDTO;
    private Oportunidade oportunidade;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(oportunidadeController).build();

        // Criar instâncias dos DTOs
        oportunidadeCriarInputDTO = new OportunidadeCriarInputDTO();
        oportunidadeConcluirInputDTO = new OportunidadeConcluirInputDTO();
        oportunidadeTransferenciaInputDTO = new OportunidadeTransferenciaInputDTO();
        oportunidadeOutputDTO = new OportunidadeOutputDTO();
        oportunidade = new Oportunidade();
    }

    @Test
    void criar() throws Exception {
        // Arranjando o comportamento do mock
        when(mapper.converterOportunidadeCriarInputDTOEmOportunidade(any(OportunidadeCriarInputDTO.class))).thenReturn(oportunidade);
        doNothing().when(service).criar(any());

        // Chamando o método do controlador com MockMvc
        mockMvc.perform(post("/oportunidade/criar")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Nova oportunidade criada com sucesso!"));

        // Verificando se o método foi chamado no serviço
        verify(service, times(1)).criar(any());
    }

    @Test
    void concluir() throws Exception {
        // Arranjando o comportamento do mock
        when(mapper.converterOportunidadeConcluirInputDTOEmOportunidade(any(OportunidadeConcluirInputDTO.class))).thenReturn(oportunidade);
        doNothing().when(service).concluir(oportunidade);

        // Chamando o método do controlador com MockMvc
        mockMvc.perform(post("/oportunidade/concluir")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Oportunidade concluída com sucesso!"));

        // Verificando se o método foi chamado no serviço
        verify(service, times(1)).concluir(any());
    }

    @Test
    void transferir() throws Exception {
        // Arranjando o comportamento do mock
        doNothing().when(service).transferirOportunidade(oportunidadeTransferenciaInputDTO, "token");

        // Chamando o método do controlador com MockMvc
        mockMvc.perform(post("/oportunidade/transferir")
                        .header("Authorization", "Bearer token")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Oportunidade transferida com sucesso!"));
    }

    @Test
    void listar() throws Exception {
        // Arranjando o comportamento do mock
        when(service.listar()).thenReturn(List.of(new Oportunidade()));
        when(mapper.converterListaOportunidadeEmListaOportunidadeOutputDTO(List.of(oportunidade))).thenReturn(List.of(oportunidadeOutputDTO));

        // Chamando o método do controlador com MockMvc
        mockMvc.perform(get("/oportunidade/listar"))
                .andExpect(status().isOk());

        // Verificando se o método foi chamado no serviço
        verify(service, times(1)).listar();
    }

    @Test
    void listarPorId() throws Exception {
        // Arranjando o comportamento do mock
        Long oportunidadeId = 1L;
        when(service.listarPorId(oportunidadeId)).thenReturn(oportunidade);
        when(mapper.converterOportunidadeEmOportunidadeOutputDTO(oportunidade)).thenReturn(oportunidadeOutputDTO);

        // Chamando o método do controlador com MockMvc
        mockMvc.perform(get("/oportunidade/listar/id/{id}", oportunidadeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

        // Verificando se o método foi chamado no serviço
        verify(service, times(1)).listarPorId(oportunidadeId);
    }

    @Test
    void atualizar() throws Exception {
        // Arranjando o comportamento do mock
        when(mapper.converterOportunidadeCriarInputDTOEmOportunidade(any(OportunidadeCriarInputDTO.class))).thenReturn(oportunidade);
        doNothing().when(service).atualizar(any(), anyString());

        // Chamando o método do controlador com MockMvc
        mockMvc.perform(put("/oportunidade/atualizar")
                        .header("Authorization", "Bearer token")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Oportunidade atualizada com sucesso!"));

        // Verificando se o método foi chamado no serviço
        verify(service, times(1)).atualizar(any(), eq("token"));
    }
}
