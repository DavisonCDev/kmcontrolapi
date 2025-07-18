// src/main/java/com/oksmart/kmcontrolapi/service/create/ControleKmCreateService.java
package com.oksmart.kmcontrolapi.service.create;

import com.oksmart.kmcontrolapi.model.ControleKm;
import com.oksmart.kmcontrolapi.repository.ControleKmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ControleKmCreateService {

    private final ControleKmRepository controleKmRepository;

    public ControleKm salvar(ControleKm controleKm) {
        return controleKmRepository.save(controleKm);
    }
}
