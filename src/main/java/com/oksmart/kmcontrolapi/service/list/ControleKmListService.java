// src/main/java/com/oksmart/kmcontrolapi/service/list/ControleKmListService.java
package com.oksmart.kmcontrolapi.service.list;

import com.oksmart.kmcontrolapi.model.ControleKm;
import com.oksmart.kmcontrolapi.repository.ControleKmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ControleKmListService {

    private final ControleKmRepository controleKmRepository;

    public List<ControleKm> listarTodos() {
        return controleKmRepository.findAll();
    }
}
