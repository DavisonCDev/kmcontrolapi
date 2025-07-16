// Caminho: src/main/java/com/oksmart/kmcontrolapi/service/create/VeiculoCreateService.java

package com.oksmart.kmcontrolapi.service.create;

import com.oksmart.kmcontrolapi.dto.VeiculoCreateRequest;
import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import com.oksmart.kmcontrolapi.service.historico.RegistroHistoricoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class VeiculoCreateService {

    private final VeiculoRepository veiculoRepository;
    private final RegistroHistoricoService registroHistoricoService;

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
                .diarias(request.getDiarias())
                .franquiaKm(request.getFranquiaKm())
                .locadora(request.getLocadora())
                .numeroContrato(request.getNumeroContrato())
                .osCliente(request.getOsCliente())
                .valorAluguel(request.getValorAluguel())
                .dataAtual(LocalDate.now())
                .build();

        Veiculo salvo = veiculoRepository.save(veiculo);

        // ✅ Registro no histórico após salvar
        registroHistoricoService.registrar(
                "CRIADO",
                "Veiculo",
                salvo.getId(),
                "Veículo criado com placa " + salvo.getPlaca()
        );

        return VeiculoResponse.builder()
                .id(salvo.getId())
                .marca(salvo.getMarca())
                .modelo(salvo.getModelo())
                .cor(salvo.getCor())
                .placa(salvo.getPlaca())
                .kmInicial(salvo.getKmInicial())
                .kmAtual(salvo.getKmAtual())
                .dataRegistro(salvo.getDataRegistro())
                .condutorPrincipal(salvo.getCondutorPrincipal())
                .condutorResponsavel(salvo.getCondutorResponsavel())
                .diarias(salvo.getDiarias())
                .franquiaKm(salvo.getFranquiaKm())
                .locadora(salvo.getLocadora())
                .numeroContrato(salvo.getNumeroContrato())
                .osCliente(salvo.getOsCliente())
                .valorAluguel(salvo.getValorAluguel())
                .dataAtual(salvo.getDataAtual())
                .build();
    }
}
