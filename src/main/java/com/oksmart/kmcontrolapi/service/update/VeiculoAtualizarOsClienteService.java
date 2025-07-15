// Caminho: src/main/java/com/oksmart/kmcontrolapi/service/update/VeiculoAtualizarOsClienteService.java

package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.AtualizarOsClienteRequest;
import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VeiculoAtualizarOsClienteService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public VeiculoResponse atualizar(Long id, AtualizarOsClienteRequest request) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException(id));

        veiculo.setOsCliente(request.getOsCliente());
        veiculo.setDataAtual(LocalDate.now()); // ✅ definido automaticamente

        veiculoRepository.save(veiculo);

        return VeiculoResponse.fromEntity(veiculo);
    }
}
