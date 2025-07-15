// Caminho: src/main/java/com/oksmart/kmcontrolapi/service/update/VeiculoAtualizarCondutorPrincipalService.java

package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.AtualizarCondutorPrincipalRequest;
import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VeiculoAtualizarCondutorPrincipalService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public VeiculoResponse atualizar(Long id, AtualizarCondutorPrincipalRequest request) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException(id));

        veiculo.setCondutorPrincipal(request.getCondutorPrincipal());
        veiculo.setDataAtual(LocalDate.now()); // ✅ Define internamente

        veiculoRepository.save(veiculo);

        return VeiculoResponse.fromEntity(veiculo);
    }
}
