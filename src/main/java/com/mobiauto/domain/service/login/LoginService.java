package com.mobiauto.domain.service.login;

import com.mobiauto.api.dto.login.LoginInputDTO;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    public ResponseEntity<?> processarLogin(LoginInputDTO loginInputDTO);

}
