// src/main/java/com/oksmart/kmcontrolapi/service/create/ContratoCreateService.java
package com.oksmart.kmcontrolapi.service.create;

import com.oksmart.kmcontrolapi.model.Contrato;
import com.oksmart.kmcontrolapi.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContratoCreateService {

    private final ContratoRepository contratoRepository;

    public Contrato salvarContrato(Contrato contrato) {
        return contratoRepository.save(contrato);
    }
}
