// Caminho: src/main/java/com/oksmart/kmcontrolapi/service/historico/RegistroHistoricoService.java

package com.oksmart.kmcontrolapi.service.historico;

import com.oksmart.kmcontrolapi.model.Historico;
import com.oksmart.kmcontrolapi.repository.HistoricoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistroHistoricoService {

    private final HistoricoRepository historicoRepository;

    public void registrar(String acao, String entidade, Long entidadeId, String descricao) {
        Historico historico = Historico.builder()
                .acao(acao)
                .entidade(entidade)
                .entidadeId(entidadeId)
                .descricao(descricao)
                .dataHora(LocalDateTime.now())
                .build();

        historicoRepository.save(historico);
    }
}
