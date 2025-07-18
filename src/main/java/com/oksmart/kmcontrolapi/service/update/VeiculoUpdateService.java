// src/main/java/com/oksmart/kmcontrolapi/service/update/VeiculoUpdateService.java
package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoUpdateService {

    private final VeiculoRepository veiculoRepository;

    public Veiculo updateVeiculo(Long id, Veiculo veiculoAtualizado) {
        Veiculo veiculoExistente = veiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado com id: " + id));

        veiculoExistente.setMarca(veiculoAtualizado.getMarca());
        veiculoExistente.setModelo(veiculoAtualizado.getModelo());
        veiculoExistente.setPlaca(veiculoAtualizado.getPlaca());
        veiculoExistente.setCor(veiculoAtualizado.getCor());
        veiculoExistente.setLocadora(veiculoAtualizado.getLocadora());
        veiculoExistente.setKm(veiculoAtualizado.getKm());

        return veiculoRepository.save(veiculoExistente);
    }
}
