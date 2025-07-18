// src/test/java/com/oksmart/kmcontrolapi/service/delete/FuncionarioDeleteServiceTest.java
package com.oksmart.kmcontrolapi.service.delete;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.repository.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class FuncionarioDeleteServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioDeleteService deleteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteFuncionario_Success() {
        Long id = 1L;

        when(funcionarioRepository.existsById(id)).thenReturn(true);
        doNothing().when(funcionarioRepository).deleteById(id);

        deleteService.deletarPorId(id);

        verify(funcionarioRepository, times(1)).existsById(id);
        verify(funcionarioRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteFuncionario_NotFound() {
        Long id = 1L;

        when(funcionarioRepository.existsById(id)).thenReturn(false);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            deleteService.deletarPorId(id);
        });

        assertTrue(exception.getMessage().contains("Funcionário não encontrado"));
        verify(funcionarioRepository, times(1)).existsById(id);
        verify(funcionarioRepository, never()).deleteById(id);
    }
}
