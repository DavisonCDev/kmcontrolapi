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
        Veiculo veiculo = Veiculo.builder()
                .marca(request.getMarca())
                .modelo(request.getModelo())
                .cor(request.getCor())
                .placa(request.getPlaca())
                .kmInicial(request.getKmInicial())
                .kmAtual(request.getKmAtual())
                .dataRegistro(request.getDataRegistro())
                .condutorPrincipal(request.getCondutorPrincipal())
                .condutorResponsavel(request.getCondutorResponsavel())
                .dataAtual(request.getDataAtual())
                .diarias(request.getDiarias())
                .franquiaKm(request.getFranquiaKm())
                .locadora(request.getLocadora())
                .numeroContrato(request.getNumeroContrato())
                .osCliente(request.getOsCliente())
                .valorAluguel(request.getValorAluguel())
                .build();

        Veiculo salvo = veiculoRepository.save(veiculo);

        return VeiculoResponse.builder()
                .id(salvo.getId())
                .marca(salvo.getMarca())
                .modelo(salvo.getModelo())
                .cor(salvo.getCor())
                .placa(salvo.getPlaca())
                .kmInicial(salvo.getKmInicial())
                .dataRegistro(salvo.getDataRegistro())
                .condutorPrincipal(salvo.getCondutorPrincipal())
                .condutorResponsavel(salvo.getCondutorResponsavel())
                .dataAtual(salvo.getDataAtual())
                .diarias(salvo.getDiarias())
                .franquiaKm(salvo.getFranquiaKm())
                .locadora(salvo.getLocadora())
                .numeroContrato(salvo.getNumeroContrato())
                .osCliente(salvo.getOsCliente())
                .valorAluguel(salvo.getValorAluguel())
                .build();
    }
}
