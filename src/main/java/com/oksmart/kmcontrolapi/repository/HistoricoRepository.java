// Caminho: src/main/java/com/oksmart/kmcontrolapi/repository/HistoricoRepository.java

package com.oksmart.kmcontrolapi.repository;

import com.oksmart.kmcontrolapi.model.Historico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
}
