// src/main/java/com/oksmart/kmcontrolapi/model/Funcionario.java
package com.oksmart.kmcontrolapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "funcionarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private LocalDate dataNascimento;

    private String rg;

    private String cpf;

    private String habilitacao;

    private String matricula;

    @CreationTimestamp
    private LocalDateTime dataRegistro;

    @UpdateTimestamp
    private LocalDateTime dataAtual;
}
