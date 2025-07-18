// src/main/java/com/oksmart/kmcontrolapi/model/Contrato.java
package com.oksmart.kmcontrolapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionarioTitular;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @Column(nullable = false)
    private String osCliente;

    @Column(nullable = false)
    private String numeroContrato;

    @Column(nullable = false)
    private LocalDate dataContrato;

    @Column(nullable = false)
    private Integer diarias;

    @Column(nullable = false)
    private Integer franquiaKm;

    @Column(nullable = false)
    private BigDecimal valorAluguel;

    @CreationTimestamp
    private LocalDate dataAtual;
}
