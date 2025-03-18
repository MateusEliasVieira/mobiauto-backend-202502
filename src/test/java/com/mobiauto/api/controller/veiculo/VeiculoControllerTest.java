package com.mobiauto.api.controller.veiculo;

import com.mobiauto.api.dto.veiculo.VeiculoInputDTO;
import com.mobiauto.api.dto.veiculo.VeiculoOutputDTO;
import com.mobiauto.api.mapper.veiculo.VeiculoMapper;
import com.mobiauto.domain.model.Veiculo;
import com.mobiauto.domain.service.veiculo.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
class VeiculoControllerTest {

    @Mock
    private VeiculoService service;

    @Mock
    private VeiculoMapper mapper;

    @InjectMocks
    private VeiculoController controller;

    private MockMvc mockMvc;
    private Veiculo veiculo;
    private VeiculoInputDTO veiculoInputDTO;
    private VeiculoOutputDTO veiculoOutputDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        veiculo = new Veiculo();
        veiculoInputDTO = new VeiculoInputDTO();
        veiculoOutputDTO = new VeiculoOutputDTO();
    }

    @Test
    void salvar() throws Exception {
        doNothing().when(service).salvar(veiculo);
        when(mapper.converterVeiculoInputDTOEmVeiculo(any(VeiculoInputDTO.class))).thenReturn(veiculo);

        mockMvc.perform(post("/veiculo/salvar")
                        .contentType("application/json")
                        .content("{}")) // Exemplo de dados JSON para o veiculo
                .andExpect(status().isCreated())
                .andExpect(content().string("Veículo adicionado com sucesso!"));

        verify(service, times(1)).salvar(veiculo);
    }

    @Test
    void listar() throws Exception {
        when(service.listar()).thenReturn(Arrays.asList(veiculo));
        when(mapper.converterListaVeiculoEmListaVeiculoOutputDTO(Arrays.asList(veiculo))).thenReturn(Arrays.asList(veiculoOutputDTO));

        mockMvc.perform(get("/veiculo/listar"))
                .andExpect(status().isOk());

        verify(service, times(1)).listar();
    }
}
