// Caminho: src/main/java/com/oksmart/kmcontrolapi/service/create/FuncionarioCreateService.java
package com.oksmart.kmcontrolapi.service.create;

import com.oksmart.kmcontrolapi.dto.FuncionarioRequestDTO;
import com.oksmart.kmcontrolapi.dto.FuncionarioResponseDTO;
import com.oksmart.kmcontrolapi.model.Funcionario;
import com.oksmart.kmcontrolapi.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FuncionarioCreateService {

    private final FuncionarioRepository repository;

    public FuncionarioResponseDTO create(FuncionarioRequestDTO dto) {
        Funcionario funcionario = Funcionario.builder()
                .nome(dto.getNome())
                .sobrenome(dto.getSobrenome())
                .dataNascimento(dto.getDataNascimento())
                .rg(dto.getRg())
                .cpf(dto.getCpf())
                .habilitacao(dto.getHabilitacao())
                .matricula(dto.getMatricula())
                .dataRegistro(dto.getDataRegistro())
                .build();

        return toResponse(repository.save(funcionario));
    }

    private FuncionarioResponseDTO toResponse(Funcionario funcionario) {
        return FuncionarioResponseDTO.builder()
                .id(funcionario.getId())
                .nome(funcionario.getNome())
                .sobrenome(funcionario.getSobrenome())
                .dataNascimento(funcionario.getDataNascimento())
                .rg(funcionario.getRg())
                .cpf(funcionario.getCpf())
                .habilitacao(funcionario.getHabilitacao())
                .matricula(funcionario.getMatricula())
                .dataRegistro(funcionario.getDataRegistro())
                .dataAtual(funcionario.getDataAtual())
                .build();
    }
}
