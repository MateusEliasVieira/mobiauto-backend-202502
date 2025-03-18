package com.mobiauto.api.controller.login;

import com.mobiauto.api.dto.login.LoginInputDTO;
import com.mobiauto.domain.service.login.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    private LoginService servico;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private LoginController controller;

    private MockMvc mockMvc;
    private LoginInputDTO loginEntradaDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        loginEntradaDTO = new LoginInputDTO();
    }

//    @Test
//    void login_DeveRetornarResponseEntity_QuandoLoginEhProcessado() throws Exception {
//        ResponseEntity<?> expectedResponse = ResponseEntity.ok("Login bem-sucedido");
//        when(servico.processarLogin(loginEntradaDTO)).thenReturn(expectedResponse);
//
//        mockMvc.perform(post("/login/logar")
//                        .contentType("application/json")
//                        .content("{}"))  // JSON fictício do DTO
//                .andExpect(status().isOk())
//                .andExpect(content().string("Login bem-sucedido"));
//
//        verify(servico, times(1)).processarLogin(loginEntradaDTO);
//    }
}
