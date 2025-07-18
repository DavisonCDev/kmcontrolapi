// src/test/java/com/oksmart/kmcontrolapi/service/update/FuncionarioUpdateServiceTest.java
package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.model.Funcionario;
import com.oksmart.kmcontrolapi.repository.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FuncionarioUpdateServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioUpdateService updateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateFuncionario_Success() {
        Funcionario existente = Funcionario.builder()
                .id(1L)
                .nome("João")
                .sobrenome("Silva")
                .dataNascimento(LocalDate.of(1980, 5, 15))
                .rg("123456789")
                .cpf("11122233344")
                .habilitacao("AB123456")
                .matricula("M1234")
                .build();

        Funcionario atualizado = Funcionario.builder()
                .nome("João Carlos")
                .sobrenome("Silva Neto")
                .dataNascimento(LocalDate.of(1980, 5, 15))
                .rg("123456789")
                .cpf("11122233344")
                .habilitacao("AB123456")
                .matricula("M1234")
                .build();

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(funcionarioRepository.save(any(Funcionario.class))).thenAnswer(i -> i.getArgument(0));

        Funcionario resultado = updateService.updateFuncionario(1L, atualizado);

        assertNotNull(resultado);
        assertEquals("João Carlos", resultado.getNome());
        assertEquals("Silva Neto", resultado.getSobrenome());
        verify(funcionarioRepository, times(1)).findById(1L);
        verify(funcionarioRepository, times(1)).save(any(Funcionario.class));
    }

    @Test
    void testUpdateFuncionario_NotFound() {
        Funcionario atualizado = Funcionario.builder().nome("João Carlos").build();

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            updateService.updateFuncionario(1L, atualizado);
        });

        assertEquals("Funcionário não encontrado com id: 1", exception.getMessage());
        verify(funcionarioRepository, times(1)).findById(1L);
        verify(funcionarioRepository, never()).save(any(Funcionario.class));
    }
}
