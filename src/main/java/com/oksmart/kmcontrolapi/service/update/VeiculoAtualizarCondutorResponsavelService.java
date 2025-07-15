// Caminho: src/main/java/com/oksmart/kmcontrolapi/service/update/VeiculoAtualizarCondutorResponsavelService.java

package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.AtualizarCondutorResponsavelRequest;
import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VeiculoAtualizarCondutorResponsavelService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public VeiculoResponse atualizar(Long id, AtualizarCondutorResponsavelRequest request) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException(id));

        veiculo.setCondutorResponsavel(request.getCondutorResponsavel());
        veiculo.setDataAtual(LocalDate.now()); // ✅ atualiza automaticamente

        veiculoRepository.save(veiculo);

        return VeiculoResponse.fromEntity(veiculo);
    }
}
