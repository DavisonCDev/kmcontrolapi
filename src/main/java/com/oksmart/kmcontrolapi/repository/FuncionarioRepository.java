// src/main/java/com/oksmart/kmcontrolapi/repository/FuncionarioRepository.java
package com.oksmart.kmcontrolapi.repository;

import com.oksmart.kmcontrolapi.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    // Aqui podemos adicionar métodos customizados se precisar
}
