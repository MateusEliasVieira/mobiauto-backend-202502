package com.mobiauto.api.controller.cliente;

import com.mobiauto.api.dto.cliente.ClienteInputDTO;
import com.mobiauto.api.dto.cliente.ClienteOutputDTO;
import com.mobiauto.api.mapper.cliente.ClienteMapper;
import com.mobiauto.domain.model.Cliente;
import com.mobiauto.domain.service.cliente.ClienteService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @Mock
    private ClienteService service;

    @Mock
    private ClienteMapper mapper;

    @InjectMocks
    private ClienteController controller;

    private MockMvc mockMvc;
    private Cliente cliente;
    private ClienteInputDTO clienteInputDTO;
    private ClienteOutputDTO clienteOutputDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        cliente = new Cliente();
        clienteInputDTO = new ClienteInputDTO();
        clienteOutputDTO = new ClienteOutputDTO();
    }

    @Test
    void salvar() throws Exception {
        when(mapper.converterClienteInputDTOEmCliente(any(ClienteInputDTO.class))).thenReturn(cliente);
        doNothing().when(service).salvar(cliente);

        mockMvc.perform(post("/cliente/salvar")
                        .contentType("application/json")
                        .content("{}"))  // Exemplo de dados JSON
                .andExpect(status().isCreated())
                .andExpect(content().string("Cliente adicionado com sucesso!"));

        verify(service, times(1)).salvar(cliente);
    }

    @Test
    void listar() throws Exception {
        when(service.listar()).thenReturn(Arrays.asList(cliente));
        when(mapper.converterListaClienteEmListaClienteOutputDTO(Arrays.asList(cliente))).thenReturn(Arrays.asList(clienteOutputDTO));

        mockMvc.perform(get("/cliente/listar"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}]"));  // Esperando um JSON com um objeto vazio representando o cliente

        verify(service, times(1)).listar();
    }
}
