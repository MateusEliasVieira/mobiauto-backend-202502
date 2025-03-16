package com.mobiauto.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Revenda {// Loja

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRevenda;
    @Column(unique = true)
    private String cnpj;
    private String nomeSocial;

    @OneToMany(mappedBy = "revenda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "revenda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Oportunidade> oportunidades;

}
