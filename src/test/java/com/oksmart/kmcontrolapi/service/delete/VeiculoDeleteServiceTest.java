// src/test/java/com/oksmart/kmcontrolapi/service/delete/VeiculoDeleteServiceTest.java
package com.oksmart.kmcontrolapi.service.delete;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class VeiculoDeleteServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoDeleteService deleteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteVeiculo_Success() {
        Long id = 1L;

        when(veiculoRepository.existsById(id)).thenReturn(true);
        doNothing().when(veiculoRepository).deleteById(id);

        deleteService.deleteVeiculo(id);

        verify(veiculoRepository, times(1)).existsById(id);
        verify(veiculoRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteVeiculo_NotFound() {
        Long id = 1L;

        when(veiculoRepository.existsById(id)).thenReturn(false);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            deleteService.deleteVeiculo(id);
        });

        assertTrue(exception.getMessage().contains("Veículo não encontrado"));
        verify(veiculoRepository, times(1)).existsById(id);
        verify(veiculoRepository, never()).deleteById(id);
    }
}
