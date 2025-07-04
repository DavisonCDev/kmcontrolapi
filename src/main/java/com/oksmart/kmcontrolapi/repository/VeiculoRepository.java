// Caminho: src/main/java/com/oksmart/kmcontrolapi/repository/VeiculoRepository.java

package com.oksmart.kmcontrolapi.repository;

import com.oksmart.kmcontrolapi.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    // Aqui você pode adicionar métodos customizados, como findByPlaca, se necessário
}
