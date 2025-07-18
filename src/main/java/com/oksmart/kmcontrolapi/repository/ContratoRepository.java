// src/main/java/com/oksmart/kmcontrolapi/repository/ContratoRepository.java
package com.oksmart.kmcontrolapi.repository;

import com.oksmart.kmcontrolapi.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
}
