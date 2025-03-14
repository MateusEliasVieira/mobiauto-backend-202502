package com.mobiauto.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String nome;
    @Email
    private String email;
    private String telefone;

    @OneToOne(mappedBy = "cliente")
    private Oportunidade oportunidade;
}
