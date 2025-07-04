// Caminho: src/main/java/com/oksmart/kmcontrolapi/dto/VeiculoResponse.java

package com.oksmart.kmcontrolapi.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeiculoResponse {
    private Long id;
    private String marca;
    private String modelo;
    private String cor;
    private String placa;
    private Integer kmInicial;
    private LocalDate dataRegistro;
}
