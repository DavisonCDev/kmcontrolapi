// src/main/java/com/oksmart/kmcontrolapi/service/delete/FuncionarioDeleteService.java
package com.oksmart.kmcontrolapi.service.delete;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FuncionarioDeleteService {

    private final FuncionarioRepository funcionarioRepository;

    public void deletarPorId(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Funcionário não encontrado com id: " + id);
        }
        funcionarioRepository.deleteById(id);
    }
}
