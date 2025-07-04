// Caminho: src/main/java/com/oksmart/kmcontrolapi/service/delete/VeiculoDeleteService.java

package com.oksmart.kmcontrolapi.service.delete;

import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoDeleteService {

    private final VeiculoRepository veiculoRepository;

    public void deletarPorId(Long id) {
        if (!veiculoRepository.existsById(id)) {
            throw new VeiculoNotFoundException(id);
        }
        veiculoRepository.deleteById(id);
    }
}
