// src/test/java/com/oksmart/kmcontrolapi/service/list/FuncionarioListServiceTest.java
package com.oksmart.kmcontrolapi.service.list;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.model.Funcionario;
import com.oksmart.kmcontrolapi.repository.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FuncionarioListServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioListService listService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAll() {
        Funcionario f1 = Funcionario.builder().id(1L).nome("João").sobrenome("Silva").build();
        Funcionario f2 = Funcionario.builder().id(2L).nome("Maria").sobrenome("Oliveira").build();

        when(funcionarioRepository.findAll()).thenReturn(Arrays.asList(f1, f2));

        List<Funcionario> funcionarios = listService.listAll();

        assertNotNull(funcionarios);
        assertEquals(2, funcionarios.size());
        verify(funcionarioRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Found() {
        Funcionario f = Funcionario.builder().id(1L).nome("João").build();

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(f));

        Funcionario resultado = listService.findById(1L);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
        verify(funcionarioRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            listService.findById(1L);
        });

        assertEquals("Funcionário não encontrado com id: 1", exception.getMessage());
        verify(funcionarioRepository, times(1)).findById(1L);
    }
}
