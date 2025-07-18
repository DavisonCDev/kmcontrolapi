// src/main/java/com/oksmart/kmcontrolapi/repository/ClienteRepository.java
package com.oksmart.kmcontrolapi.repository;

import com.oksmart.kmcontrolapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Podemos adicionar consultas específicas no futuro, se necessário
}
