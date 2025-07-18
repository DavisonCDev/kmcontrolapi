// src/main/java/com/oksmart/kmcontrolapi/service/create/FuncionarioCreateService.java
package com.oksmart.kmcontrolapi.service.create;

import com.oksmart.kmcontrolapi.model.Funcionario;
import com.oksmart.kmcontrolapi.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FuncionarioCreateService {

    private final FuncionarioRepository funcionarioRepository;

    public Funcionario createFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }
}
