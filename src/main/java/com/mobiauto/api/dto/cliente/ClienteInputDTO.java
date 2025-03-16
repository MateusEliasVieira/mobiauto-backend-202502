package com.mobiauto.api.dto.cliente;

import lombok.*;
import org.hibernate.validator.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteInputDTO {

    private Long idCliente;
    private String nome;
    @Email
    private String email;
    private String telefone;

}
