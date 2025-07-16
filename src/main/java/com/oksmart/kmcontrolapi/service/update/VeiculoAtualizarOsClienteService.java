package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.AtualizarOsClienteRequest;
import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import com.oksmart.kmcontrolapi.service.historico.RegistroHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VeiculoAtualizarOsClienteService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private RegistroHistoricoService registroHistoricoService;

    public VeiculoResponse atualizar(Long id, AtualizarOsClienteRequest request) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException(id));

        veiculo.setOsCliente(request.getOsCliente());
        veiculo.setDataAtual(LocalDate.now());

        veiculoRepository.save(veiculo);

        // ✅ Registro do histórico
        registroHistoricoService.registrar(
                "ATUALIZADO",
                "Veiculo",
                veiculo.getId(),
                "OS Cliente alterada para: " + request.getOsCliente()
        );

        return VeiculoResponse.fromEntity(veiculo);
    }
}
