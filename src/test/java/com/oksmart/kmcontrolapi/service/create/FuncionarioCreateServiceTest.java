// src/test/java/com/oksmart/kmcontrolapi/service/create/FuncionarioCreateServiceTest.java
package com.oksmart.kmcontrolapi.service.create;

import com.oksmart.kmcontrolapi.model.Funcionario;
import com.oksmart.kmcontrolapi.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FuncionarioCreateServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioCreateService createService;

    public FuncionarioCreateServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateFuncionario() {
        Funcionario funcionario = Funcionario.builder()
                .nome("João")
                .sobrenome("Silva")
                .dataNascimento(LocalDate.of(1980, 5, 15))
                .rg("123456789")
                .cpf("11122233344")
                .habilitacao("AB123456")
                .matricula("M1234")
                .build();

        when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);

        Funcionario criado = createService.createFuncionario(funcionario);

        assertNotNull(criado);
        assertEquals("João", criado.getNome());
        verify(funcionarioRepository, times(1)).save(funcionario);
    }
}
