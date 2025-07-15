// Caminho: src/main/java/com/oksmart/kmcontrolapi/service/update/VeiculoAtualizarCondutorPrincipalService.java

package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.AtualizarCondutorPrincipalRequest;
import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

@Service
public class VeiculoAtualizarCondutorPrincipalService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoAtualizarCondutorPrincipalService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public VeiculoResponse atualizar(Long id, AtualizarCondutorPrincipalRequest request) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException(id));

        veiculo.setCondutorPrincipal(request.getCondutorPrincipal());
        veiculoRepository.save(veiculo);

        return VeiculoResponse.fromEntity(veiculo);
    }
}
