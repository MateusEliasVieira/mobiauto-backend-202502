package com.mobiauto.api.controller.revenda;

import com.mobiauto.api.dto.revenda.RevendaInputDTO;
import com.mobiauto.api.dto.revenda.RevendaOutputDTO;
import com.mobiauto.api.mapper.revenda.RevendaMapper;
import com.mobiauto.domain.model.Revenda;
import com.mobiauto.domain.service.revenda.RevendaService;
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

class RevendaControllerTest {

    @InjectMocks
    private RevendaController revendaController;

    @Mock
    private RevendaService service;

    @Mock
    private RevendaMapper mapper;

    private RevendaInputDTO revendaInputDTO;
    private RevendaOutputDTO revendaOutputDTO;
    private Revenda revenda;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(revendaController).build();

        // Criar instâncias dos DTOs
        revendaInputDTO = new RevendaInputDTO();
        revendaOutputDTO = new RevendaOutputDTO();
        revenda = new Revenda();
    }

    @Test
    void salvar() throws Exception {
        // Arranjando o comportamento do mock
        when(mapper.converterRevendaInputDTOEmRevenda(any(RevendaInputDTO.class))).thenReturn(revenda);

        doNothing().when(service).salvar(revenda, "token");

        // Chamando o método do controlador com MockMvc
        mockMvc.perform(post("/revenda/salvar")
                        .header("Authorization", "Bearer token")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Revenda adicionada com sucesso!"));

        // Verificando se o método foi chamado no serviço
        verify(service, times(1)).salvar(revenda, "token");
    }

    @Test
    void listar() throws Exception {
        // Arranjando o comportamento do mock
        when(service.listar()).thenReturn(List.of(revenda));
        when(mapper.converterListaRevendaEmListaRevendaOutputDTO(List.of(revenda))).thenReturn(List.of(revendaOutputDTO));

        // Chamando o método do controlador com MockMvc
        mockMvc.perform(get("/revenda/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());

        // Verificando se o método foi chamado no serviço
        verify(service, times(1)).listar();
    }

    @Test
    void listarPorId() throws Exception {
        // Arranjando o comportamento do mock
        Long revendaId = 1L;
        when(service.listarPorId(revendaId)).thenReturn(revenda);
        when(mapper.converterRevendaEmRevendaOutputDTO(revenda)).thenReturn(revendaOutputDTO);

        // Chamando o método do controlador com MockMvc
        mockMvc.perform(get("/revenda/listar/id/{id}", revendaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

        // Verificando se o método foi chamado no serviço
        verify(service, times(1)).listarPorId(revendaId);
    }

    @Test
    void atualizar() throws Exception {
        // Arranjando o comportamento do mock
        when(mapper.converterRevendaInputDTOEmRevenda(any(RevendaInputDTO.class))).thenReturn(revenda);
        doReturn(revenda).when(service).atualizar(revenda);

        // Chamando o método do controlador com MockMvc
        mockMvc.perform(put("/revenda/atualizar")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Revenda atualizada com sucesso!"));

        // Verificando se o método foi chamado no serviço
        verify(service, times(1)).atualizar(revenda);
    }

}
