// src/main/java/com/oksmart/kmcontrolapi/repository/VeiculoRepository.java
package com.oksmart.kmcontrolapi.repository;

import com.oksmart.kmcontrolapi.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    Optional<Veiculo> findByPlaca(String placa);
}
