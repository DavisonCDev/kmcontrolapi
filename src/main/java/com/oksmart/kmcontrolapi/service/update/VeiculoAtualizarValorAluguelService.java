package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.AtualizarValorAluguelRequest;
import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import com.oksmart.kmcontrolapi.service.historico.RegistroHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VeiculoAtualizarValorAluguelService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private RegistroHistoricoService registroHistoricoService;

    public VeiculoResponse atualizarValor(Long id, AtualizarValorAluguelRequest request) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException(id));

        veiculo.setValorAluguel(request.getValorAluguel());
        veiculo.setDataAtual(LocalDate.now());

        veiculoRepository.save(veiculo);

        // ✅ Registrar histórico da atualização
        registroHistoricoService.registrar(
                "ATUALIZADO",
                "Veiculo",
                veiculo.getId(),
                "Valor do aluguel atualizado para R$ " + request.getValorAluguel()
        );

        return VeiculoResponse.fromEntity(veiculo);
    }
}
