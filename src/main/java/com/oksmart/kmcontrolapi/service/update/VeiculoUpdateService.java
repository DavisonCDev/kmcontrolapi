// Caminho: src/main/java/com/oksmart/kmcontrolapi/service/update/VeiculoUpdateService.java

package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.dto.VeiculoUpdateRequest;
import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import com.oksmart.kmcontrolapi.service.historico.RegistroHistoricoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class VeiculoUpdateService {

    private final VeiculoRepository veiculoRepository;
    private final RegistroHistoricoService registroHistoricoService;

    public VeiculoResponse atualizar(Long id, VeiculoUpdateRequest request) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException(id));

        veiculo.setMarca(request.getMarca());
        veiculo.setModelo(request.getModelo());
        veiculo.setCor(request.getCor());
        veiculo.setPlaca(request.getPlaca());
        veiculo.setKmInicial(request.getKmInicial());
        veiculo.setKmAtual(request.getKmAtual());
        veiculo.setDataRegistro(request.getDataRegistro());
        veiculo.setCondutorPrincipal(request.getCondutorPrincipal());
        veiculo.setCondutorResponsavel(request.getCondutorResponsavel());
        veiculo.setDiarias(request.getDiarias());
        veiculo.setFranquiaKm(request.getFranquiaKm());
        veiculo.setLocadora(request.getLocadora());
        veiculo.setNumeroContrato(request.getNumeroContrato());
        veiculo.setOsCliente(request.getOsCliente());
        veiculo.setValorAluguel(request.getValorAluguel());
        veiculo.setDataAtual(LocalDate.now());

        Veiculo atualizado = veiculoRepository.save(veiculo);

        // ✅ Registro no histórico
        registroHistoricoService.registrar(
                "ATUALIZADO",
                "Veiculo",
                atualizado.getId(),
                "Veículo atualizado com placa " + atualizado.getPlaca()
        );

        return VeiculoResponse.builder()
                .id(atualizado.getId())
                .marca(atualizado.getMarca())
                .modelo(atualizado.getModelo())
                .cor(atualizado.getCor())
                .placa(atualizado.getPlaca())
                .kmInicial(atualizado.getKmInicial())
                .dataRegistro(atualizado.getDataRegistro())
                .condutorPrincipal(atualizado.getCondutorPrincipal())
                .condutorResponsavel(atualizado.getCondutorResponsavel())
                .dataAtual(atualizado.getDataAtual())
                .diarias(atualizado.getDiarias())
                .franquiaKm(atualizado.getFranquiaKm())
                .locadora(atualizado.getLocadora())
                .numeroContrato(atualizado.getNumeroContrato())
                .osCliente(atualizado.getOsCliente())
                .valorAluguel(atualizado.getValorAluguel())
                .build();
    }
}
