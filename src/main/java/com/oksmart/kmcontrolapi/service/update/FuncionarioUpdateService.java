// src/main/java/com/oksmart/kmcontrolapi/service/update/FuncionarioUpdateService.java
package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.model.Funcionario;
import com.oksmart.kmcontrolapi.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FuncionarioUpdateService {

    private final FuncionarioRepository funcionarioRepository;

    public Funcionario updateFuncionario(Long id, Funcionario funcionarioAtualizado) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com id: " + id));

        funcionarioExistente.setNome(funcionarioAtualizado.getNome());
        funcionarioExistente.setSobrenome(funcionarioAtualizado.getSobrenome());
        funcionarioExistente.setDataNascimento(funcionarioAtualizado.getDataNascimento());
        funcionarioExistente.setRg(funcionarioAtualizado.getRg());
        funcionarioExistente.setCpf(funcionarioAtualizado.getCpf());
        funcionarioExistente.setHabilitacao(funcionarioAtualizado.getHabilitacao());
        funcionarioExistente.setMatricula(funcionarioAtualizado.getMatricula());
        // dataRegistro não é alterado, dataAtual atualizado automaticamente

        return funcionarioRepository.save(funcionarioExistente);
    }
}
