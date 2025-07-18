// src/test/java/com/oksmart/kmcontrolapi/service/list/VeiculoListServiceTest.java
package com.oksmart.kmcontrolapi.service.list;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
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

class VeiculoListServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoListService listService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAll() {
        Veiculo v1 = Veiculo.builder().id(1L).marca("Toyota").placa("ABC1234").build();
        Veiculo v2 = Veiculo.builder().id(2L).marca("Honda").placa("DEF5678").build();

        when(veiculoRepository.findAll()).thenReturn(Arrays.asList(v1, v2));

        List<Veiculo> veiculos = listService.listAll();

        assertNotNull(veiculos);
        assertEquals(2, veiculos.size());
        verify(veiculoRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Found() {
        Veiculo v = Veiculo.builder().id(1L).marca("Toyota").build();

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(v));

        Veiculo resultado = listService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Toyota", resultado.getMarca());
        verify(veiculoRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            listService.findById(1L);
        });

        assertEquals("Veículo não encontrado com id: 1", exception.getMessage());
        verify(veiculoRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByPlaca_Found() {
        Veiculo v = Veiculo.builder().id(1L).marca("Toyota").placa("ABC1234").build();

        when(veiculoRepository.findByPlaca("ABC1234")).thenReturn(Optional.of(v));

        Veiculo resultado = listService.findByPlaca("ABC1234");

        assertNotNull(resultado);
        assertEquals("Toyota", resultado.getMarca());
        verify(veiculoRepository, times(1)).findByPlaca("ABC1234");
    }

    @Test
    void testFindByPlaca_NotFound() {
        when(veiculoRepository.findByPlaca("ABC1234")).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            listService.findByPlaca("ABC1234");
        });

        assertEquals("Veículo não encontrado com placa: ABC1234", exception.getMessage());
        verify(veiculoRepository, times(1)).findByPlaca("ABC1234");
    }
}
