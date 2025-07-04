// Caminho: src/main/java/com/oksmart/kmcontrolapi/dto/VeiculoUpdateRequest.java

package com.oksmart.kmcontrolapi.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeiculoUpdateRequest {
    private String marca;
    private String modelo;
    private String cor;
    private String placa;
    private Integer kmInicial;
    private LocalDate dataRegistro;
}
