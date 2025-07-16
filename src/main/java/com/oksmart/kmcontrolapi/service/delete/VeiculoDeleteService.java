// Caminho: src/main/java/com/oksmart/kmcontrolapi/service/delete/VeiculoDeleteService.java

package com.oksmart.kmcontrolapi.service.delete;

import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import com.oksmart.kmcontrolapi.service.historico.RegistroHistoricoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoDeleteService {

    private final VeiculoRepository veiculoRepository;
    private final RegistroHistoricoService registroHistoricoService;

    public void deletar(Long id) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException(id));

        veiculoRepository.deleteById(id);

        registroHistoricoService.registrar(
                "DELETADO",
                "Veiculo",
                id,
                "Veículo com placa " + veiculo.getPlaca() + " foi deletado do sistema"
        );
    }
}
