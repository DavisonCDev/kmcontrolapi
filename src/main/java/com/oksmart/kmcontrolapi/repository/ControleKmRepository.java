// src/main/java/com/oksmart/kmcontrolapi/repository/ControleKmRepository.java
package com.oksmart.kmcontrolapi.repository;

import com.oksmart.kmcontrolapi.model.ControleKm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControleKmRepository extends JpaRepository<ControleKm, Long> {
}
