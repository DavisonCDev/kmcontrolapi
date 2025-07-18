// src/main/java/com/oksmart/kmcontrolapi/service/update/ContratoUpdateService.java
package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.model.Contrato;
import com.oksmart.kmcontrolapi.model.Funcionario;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.model.Cliente;
import com.oksmart.kmcontrolapi.repository.ContratoRepository;
import com.oksmart.kmcontrolapi.repository.FuncionarioRepository;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import com.oksmart.kmcontrolapi.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContratoUpdateService {

    private final ContratoRepository contratoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;

    public Contrato atualizarContrato(Long id, Contrato contratoAtualizado) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrato não encontrado com id: " + id));

        // Atualiza funcionário titular
        if (contratoAtualizado.getFuncionarioTitular() != null) {
            Long funcionarioId = contratoAtualizado.getFuncionarioTitular().getId();
            Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                    .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com id: " + funcionarioId));
            contrato.setFuncionarioTitular(funcionario);
        }

        // Atualiza veículo
        if (contratoAtualizado.getVeiculo() != null) {
            Long veiculoId = contratoAtualizado.getVeiculo().getId();
            Veiculo veiculo = veiculoRepository.findById(veiculoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado com id: " + veiculoId));
            contrato.setVeiculo(veiculo);
        }

        // Atualiza OS Cliente
        if (contratoAtualizado.getOsCliente() != null) {
            Long clienteId = contratoAtualizado.getId();
            Cliente cliente = clienteRepository.findById(clienteId)
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id: " + clienteId));
            contrato.setOsCliente(String.valueOf(cliente));
        }

        // Atualiza outros campos simples
        contrato.setNumeroContrato(contratoAtualizado.getNumeroContrato());
        contrato.setDataContrato(contratoAtualizado.getDataContrato());
        contrato.setDiarias(contratoAtualizado.getDiarias());
        contrato.setFranquiaKm(contratoAtualizado.getFranquiaKm());
        contrato.setValorAluguel(contratoAtualizado.getValorAluguel());

        // Atualiza dataAtual para o momento da atualização
        contrato.setDataAtual(LocalDate.from(LocalDateTime.now()));

        return contratoRepository.save(contrato);
    }
}
