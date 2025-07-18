// src/test/java/com/oksmart/kmcontrolapi/service/update/VeiculoUpdateServiceTest.java
package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VeiculoUpdateServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoUpdateService updateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateVeiculo_Success() {
        Veiculo existente = Veiculo.builder()
                .id(1L)
                .marca("Toyota")
                .modelo("Corolla")
                .placa("ABC1234")
                .cor("Prata")
                .locadora("Locadora X")
                .km(10000)
                .build();

        Veiculo atualizado = Veiculo.builder()
                .marca("Toyota")
                .modelo("Corolla Altis")
                .placa("ABC1234")
                .cor("Preto")
                .locadora("Locadora Y")
                .km(12000)
                .build();

        when(veiculoRepository.findById(1L)).thenReturn(java.util.Optional.of(existente));
        when(veiculoRepository.save(any(Veiculo.class))).thenAnswer(i -> i.getArgument(0));

        Veiculo resultado = updateService.updateVeiculo(1L, atualizado);

        assertNotNull(resultado);
        assertEquals("Corolla Altis", resultado.getModelo());
        assertEquals("Preto", resultado.getCor());
        assertEquals("Locadora Y", resultado.getLocadora());
        assertEquals(12000, resultado.getKm());
        verify(veiculoRepository, times(1)).findById(1L);
        verify(veiculoRepository, times(1)).save(any(Veiculo.class));
    }

    @Test
    void testUpdateVeiculo_NotFound() {
        Veiculo atualizado = Veiculo.builder().marca("Toyota").build();

        when(veiculoRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            updateService.updateVeiculo(1L, atualizado);
        });

        assertEquals("Veículo não encontrado com id: 1", exception.getMessage());
        verify(veiculoRepository, times(1)).findById(1L);
        verify(veiculoRepository, never()).save(any(Veiculo.class));
    }
}
