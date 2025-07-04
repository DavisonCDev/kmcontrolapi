// Caminho: src/main/java/com/oksmart/kmcontrolapi/service/create/VeiculoCreateService.java

package com.oksmart.kmcontrolapi.service.create;

import com.oksmart.kmcontrolapi.dto.VeiculoCreateRequest;
import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoCreateService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoResponse criar(VeiculoCreateRequest request) {
        Veiculo novoVeiculo = Veiculo.builder()
                .marca(request.getMarca())
                .modelo(request.getModelo())
                .cor(request.getCor())
                .placa(request.getPlaca())
                .kmInicial(request.getKmInicial())
                .dataRegistro(request.getDataRegistro())
                .build();

        Veiculo salvo = veiculoRepository.save(novoVeiculo);

        return VeiculoResponse.builder()
                .id(salvo.getId())
                .marca(salvo.getMarca())
                .modelo(salvo.getModelo())
                .cor(salvo.getCor())
                .placa(salvo.getPlaca())
                .kmInicial(salvo.getKmInicial())
                .dataRegistro(salvo.getDataRegistro())
                .build();
    }
}
