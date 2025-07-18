// src/main/java/com/oksmart/kmcontrolapi/service/delete/VeiculoDeleteService.java
package com.oksmart.kmcontrolapi.service.delete;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoDeleteService {

    private final VeiculoRepository veiculoRepository;

    public void deleteVeiculo(Long id) {
        if (!veiculoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Veículo não encontrado com id: " + id);
        }
        veiculoRepository.deleteById(id);
    }
}
