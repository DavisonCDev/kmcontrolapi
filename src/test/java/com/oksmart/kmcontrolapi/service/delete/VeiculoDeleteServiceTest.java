// Caminho: src/test/java/com/oksmart/kmcontrolapi/service/delete/VeiculoDeleteServiceTest.java

package com.oksmart.kmcontrolapi.service.delete;

import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VeiculoDeleteServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoDeleteService veiculoDeleteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveDeletarVeiculoQuandoExistir() {
        Long id = 1L;

        when(veiculoRepository.existsById(id)).thenReturn(true);
        doNothing().when(veiculoRepository).deleteById(id);

        assertDoesNotThrow(() -> veiculoDeleteService.deletarPorId(id));
        verify(veiculoRepository).deleteById(id);
    }

    @Test
    void deveLancarExcecaoQuandoVeiculoNaoExistir() {
        Long id = 99L;

        when(veiculoRepository.existsById(id)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                veiculoDeleteService.deletarPorId(id));

        assertEquals("Veículo não encontrado com ID: " + id, exception.getMessage());
        verify(veiculoRepository, never()).deleteById(any());
    }
}
