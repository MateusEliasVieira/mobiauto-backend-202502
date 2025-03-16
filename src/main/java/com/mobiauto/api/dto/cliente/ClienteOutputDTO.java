package com.mobiauto.api.dto.cliente;

import com.mobiauto.domain.model.Oportunidade;
import lombok.*;
import org.hibernate.validator.Email;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteOutputDTO {
    private Long idCliente;
    private String nome;
    private String email;
    private String telefone;
    private List<Oportunidade> oportunidades;
}
