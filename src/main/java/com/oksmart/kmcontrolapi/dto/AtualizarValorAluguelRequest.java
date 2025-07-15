// Caminho: src/main/java/com/oksmart/kmcontrolapi/dto/AtualizarValorAluguelRequest.java

package com.oksmart.kmcontrolapi.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtualizarValorAluguelRequest {
    private BigDecimal valorAluguel;
}
