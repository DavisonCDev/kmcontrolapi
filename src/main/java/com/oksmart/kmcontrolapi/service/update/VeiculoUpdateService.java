// Caminho: src/main/java/com/oksmart/kmcontrolapi/service/update/VeiculoUpdateService.java

package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.dto.VeiculoUpdateRequest;
import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoUpdateService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoResponse atualizar(Long id, VeiculoUpdateRequest request) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException(id));

        veiculo.setMarca(request.getMarca());
        veiculo.setModelo(request.getModelo());
        veiculo.setCor(request.getCor());
        veiculo.setPlaca(request.getPlaca());
        veiculo.setKmInicial(request.getKmInicial());
        veiculo.setDataRegistro(request.getDataRegistro());

        Veiculo atualizado = veiculoRepository.save(veiculo);

        return VeiculoResponse.builder()
                .id(atualizado.getId())
                .marca(atualizado.getMarca())
                .modelo(atualizado.getModelo())
                .cor(atualizado.getCor())
                .placa(atualizado.getPlaca())
                .kmInicial(atualizado.getKmInicial())
                .dataRegistro(atualizado.getDataRegistro())
                .build();
    }
}
