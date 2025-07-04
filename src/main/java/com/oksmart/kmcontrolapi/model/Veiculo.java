// Caminho: src/main/java/com/oksmart/kmcontrolapi/model/Veiculo.java

package com.oksmart.kmcontrolapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "veiculos")
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

    @Column(name = "km_inicial")
    private Integer kmInicial;

    @Column(name = "data_registro")
    private LocalDate dataRegistro;
}
