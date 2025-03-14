package com.mobiauto.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVeiculo;
    private String marca;
    private String modelo;
    private String versao;
    private String anoModelo;

    @OneToOne(mappedBy = "veiculo")
    private Oportunidade oportunidade;
}
