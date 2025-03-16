package com.mobiauto.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.Email;

import java.util.List;

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
    private String email;
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private List<Oportunidade> oportunidades;
}
