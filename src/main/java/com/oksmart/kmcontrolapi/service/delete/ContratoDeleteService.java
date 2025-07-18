// src/main/java/com/oksmart/kmcontrolapi/service/delete/ContratoDeleteService.java
package com.oksmart.kmcontrolapi.service.delete;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContratoDeleteService {

    private final ContratoRepository contratoRepository;

    public void deletarPorId(Long id) {
        if (!contratoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrato não encontrado com id: " + id);
        }
        contratoRepository.deleteById(id);
    }
}
