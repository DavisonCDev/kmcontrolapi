// src/main/java/com/oksmart/kmcontrolapi/service/update/ControleKmUpdateService.java
package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.model.ControleKm;
import com.oksmart.kmcontrolapi.repository.ControleKmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ControleKmUpdateService {

    private final ControleKmRepository controleKmRepository;

    public ControleKm atualizarKmAtual(Long id, Integer novoKm) {
        ControleKm controleKm = controleKmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Controle de KM não encontrado com id: " + id));

        controleKm.setKmAtual(novoKm);

        return controleKmRepository.save(controleKm);
    }
}
