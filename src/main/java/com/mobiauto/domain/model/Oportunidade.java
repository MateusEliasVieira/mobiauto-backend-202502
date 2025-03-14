package com.mobiauto.domain.model;

import com.mobiauto.domain.enums.StatusOportunidade;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Oportunidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOportunidade;
    private StatusOportunidade status;
    private String motivoDaConclusao;
    private Date dataDeAtribuicao;
    private Date dataDeConclusao;

    @ManyToOne
    @JoinColumn(name = "revenda_id")
    private Revenda revenda;

    @OneToOne
    @JoinColumn(name = "cliente_id", unique = true)
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "veiculo_id", unique = true)
    private Veiculo veiculo;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;


}
