// src/main/java/com/oksmart/kmcontrolapi/service/create/VeiculoCreateService.java
package com.oksmart.kmcontrolapi.service.create;

import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoCreateService {

    private final VeiculoRepository veiculoRepository;

    public Veiculo createVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }
}
