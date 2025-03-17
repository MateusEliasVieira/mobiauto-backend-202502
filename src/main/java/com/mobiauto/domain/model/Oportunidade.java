package com.mobiauto.domain.model;

import com.mobiauto.domain.enums.StatusOportunidade;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    private LocalDateTime dataDeAtribuicao;
    private LocalDateTime dataDeConclusao;

    @ManyToOne
    @JoinColumn(name = "revenda_id")
    private Revenda revenda;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne(mappedBy = "oportunidade", cascade = CascadeType.ALL)
    private Atendimento atendimento;


}
