// src/main/java/com/oksmart/kmcontrolapi/service/list/ContratoListService.java
package com.oksmart.kmcontrolapi.service.list;

import com.oksmart.kmcontrolapi.model.Contrato;
import com.oksmart.kmcontrolapi.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratoListService {

    private final ContratoRepository contratoRepository;

    public List<Contrato> listarTodos() {
        return contratoRepository.findAll();
    }
}
