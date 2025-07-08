// Caminho: src/main/java/com/oksmart/kmcontrolapi/service/list/VeiculoListService.java

package com.oksmart.kmcontrolapi.service.list;

import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VeiculoListService {

    private final VeiculoRepository veiculoRepository;

    public List<VeiculoResponse> listarTodos() {
        List<Veiculo> veiculos = veiculoRepository.findAll();

        return veiculos.stream()
                .map(veiculo -> VeiculoResponse.builder()
                        .id(veiculo.getId())
                        .marca(veiculo.getMarca())
                        .modelo(veiculo.getModelo())
                        .cor(veiculo.getCor())
                        .placa(veiculo.getPlaca())
                        .kmInicial(veiculo.getKmInicial())
                        .kmAtual(veiculo.getKmAtual())
                        .dataRegistro(veiculo.getDataRegistro())
                        .build())
                .collect(Collectors.toList());
    }
}
