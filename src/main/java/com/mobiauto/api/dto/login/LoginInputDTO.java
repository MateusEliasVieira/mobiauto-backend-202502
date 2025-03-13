package com.mobiauto.api.dto.login;

import com.mobiauto.utils.Resposta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginInputDTO {

    @NotBlank(message = Resposta.EMAIL_LOGIN)
    private String email;
    @NotBlank(message = Resposta.SENHA_LOGIN)
    @Size(min = 6, message = Resposta.SENHA)
    private String senha;

}