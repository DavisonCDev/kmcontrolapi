// src/main/java/com/oksmart/kmcontrolapi/model/Cliente.java
package com.oksmart.kmcontrolapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "os", nullable = false)
    private String os;

    @Embedded
    private Endereco endereco;

    @CreationTimestamp
    @Column(name = "data_atual", nullable = false, updatable = false)
    private LocalDateTime dataAtual;
}
