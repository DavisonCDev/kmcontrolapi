// src/main/java/com/oksmart/kmcontrolapi/service/delete/ControleKmDeleteService.java
package com.oksmart.kmcontrolapi.service.delete;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.repository.ControleKmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ControleKmDeleteService {

    private final ControleKmRepository controleKmRepository;

    public void deletarPorId(Long id) {
        if (!controleKmRepository.existsById(id)) {
            throw new ResourceNotFoundException("Controle de KM não encontrado com id: " + id);
        }
        controleKmRepository.deleteById(id);
    }
}
