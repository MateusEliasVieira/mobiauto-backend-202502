package com.mobiauto.api.dto.cliente;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteDadosBasicosOutputDTO {
    private Long idCliente;
    private String nome;
    private String email;
    private String telefone;
}
