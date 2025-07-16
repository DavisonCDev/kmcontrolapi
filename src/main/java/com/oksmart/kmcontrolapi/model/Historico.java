// Caminho: src/main/java/com/oksmart/kmcontrolapi/model/Historico.java

package com.oksmart.kmcontrolapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String acao; // "CRIADO", "ATUALIZADO", "DELETADO"
    private String entidade; // sempre "Veiculo"
    private Long entidadeId; // ID do veículo afetado
    private String descricao; // Ex: "Placa atualizada de ABC1234 para XYZ9999"
    private LocalDateTime dataHora; // momento da ação
}