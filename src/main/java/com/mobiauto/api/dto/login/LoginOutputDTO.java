package com.mobiauto.api.dto.login;

import com.mobiauto.domain.enums.RolePerfilUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginOutputDTO {

    @NotNull
    private Long idUser;
    @NotBlank
    private String email;
    @NotBlank
    private String token;
    private LocalDateTime validadeToken = LocalDateTime.now().plus(60, ChronoUnit.MINUTES);
    @NotNull
    private RolePerfilUsuario perfil;

}