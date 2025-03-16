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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Criar instâncias dos DTOs
        revendaInputDTO = new RevendaInputDTO();
        revendaOutputDTO = new RevendaOutputDTO();
        revenda = new Revenda();
    }

    @Test
    void salvarRevendaTest() {

        // Arranjando o comportamento do mock
        when(mapper.converterRevendaInputDTOEmRevenda(revendaInputDTO)).thenReturn(revenda);
        doNothing().when(service).salvar(revenda);

        // Chamando o método do controlador
        ResponseEntity<String> response = revendaController.salvar(revendaInputDTO);

        // Verificando o resultado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Revenda adicionada com sucesso!", response.getBody());

        // Verificando se o método foi chamado no serviço
        verify(service, times(1)).salvar(revenda);
    }

    @Test
    void listarRevendasTest() {
        // Arranjando o comportamento do mock
        when(service.listar()).thenReturn(List.of(revenda));  // Mock da lista
        when(mapper.converterListaRevendaEmListaRevendaOutputDTO(List.of(revenda))).thenReturn(List.of(revendaOutputDTO));

        // Chamando o método do controlador
        ResponseEntity<List<RevendaOutputDTO>> response = revendaController.listar();

        // Verificando o resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());

        // Verificando se o método foi chamado no serviço
        verify(service, times(1)).listar();
    }

    @Test
    void listarPorIdRevendaTest() {
        // Arranjando o comportamento do mock
        Long revendaId = 1L;
        when(service.listarPorId(revendaId)).thenReturn(revenda);
        when(mapper.converterRevendaEmRevendaOutputDTO(revenda)).thenReturn(revendaOutputDTO);

        // Chamando o método do controlador
        ResponseEntity<RevendaOutputDTO> response = revendaController.listarPorId(revendaId);

        // Verificando o resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        // Verificando se o método foi chamado no serviço
        verify(service, times(1)).listarPorId(revendaId);
    }

    @Test
    void atualizarRevendaTest() {
        // Arranjando o comportamento do mock
        when(mapper.converterRevendaInputDTOEmRevenda(revendaInputDTO)).thenReturn(revenda);
        doReturn(revenda).when(service).atualizar(revenda);

        // Chamando o método do controlador
        ResponseEntity<String> response = revendaController.atualizar(revendaInputDTO);

        // Verificando o resultado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Revenda atualizada com sucesso!", response.getBody());

        // Verificando se o método foi chamado no serviço
        verify(service, times(1)).atualizar(revenda);
    }

    @Test
    void deletarRevendaTest() {
        // Arranjando o comportamento do mock
        Long revendaId = 1L;
        doNothing().when(service).deletar(revendaId);

        // Chamando o método do controlador
        ResponseEntity<String> response = revendaController.deletar(revendaId);

        // Verificando o resultado
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Revenda deletada com sucesso!", response.getBody());

        // Verificando se o método foi chamado no serviço
        verify(service, times(1)).deletar(revendaId);
    }
}