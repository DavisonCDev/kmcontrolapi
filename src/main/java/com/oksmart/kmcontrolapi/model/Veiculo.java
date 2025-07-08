// Caminho: src/main/java/com/oksmart/kmcontrolapi/model/Veiculo.java

package com.oksmart.kmcontrolapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modelo;
    private String cor;
    private String placa;
    private Integer kmInicial;
    private LocalDate dataRegistro;

    // Novos campos
    private String condutorPrincipal;
    private String condutorResponsavel;
    private LocalDate dataAtual;
    private Integer diarias;
    private Integer franquiaKm;
    private String locadora;
    private String numeroContrato;
    private String osCliente;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorAluguel;

    @Column(nullable = true)
    private Integer kmAtual;
}
