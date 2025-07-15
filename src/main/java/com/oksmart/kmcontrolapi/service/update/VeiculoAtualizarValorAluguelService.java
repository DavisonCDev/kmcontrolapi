// Caminho: src/main/java/com/oksmart/kmcontrolapi/service/update/VeiculoAtualizarValorAluguelService.java

package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.AtualizarValorAluguelRequest;
import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoAtualizarValorAluguelService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public VeiculoResponse atualizarValor(Long id, AtualizarValorAluguelRequest request) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException(id));

        veiculo.setValorAluguel(request.getValorAluguel());
        veiculoRepository.save(veiculo);

        return VeiculoResponse.fromEntity(veiculo);
    }
}
