// Caminho: src/main/java/com/oksmart/kmcontrolapi/dto/VeiculoCreateRequest.java

package com.oksmart.kmcontrolapi.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeiculoCreateRequest {

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
    private BigDecimal valorAluguel;
    private Integer kmAtual;

}
