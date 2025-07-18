// src/main/java/com/oksmart/kmcontrolapi/model/ControleKm.java
package com.oksmart.kmcontrolapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ControleKm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @Column(nullable = false)
    private Integer kmAtual;

    @ManyToOne(optional = false)
    @JoinColumn(name = "condutor_id")
    private Funcionario condutor;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime dataAtual;
}
