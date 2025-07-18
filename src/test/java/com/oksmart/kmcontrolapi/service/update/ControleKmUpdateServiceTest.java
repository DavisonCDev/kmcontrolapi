// src/test/java/com/oksmart/kmcontrolapi/service/update/ControleKmUpdateServiceTest.java
package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.model.ControleKm;
import com.oksmart.kmcontrolapi.repository.ControleKmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControleKmUpdateServiceTest {

    private ControleKmRepository controleKmRepository;
    private ControleKmUpdateService controleKmUpdateService;

    @BeforeEach
    void setUp() {
        controleKmRepository = Mockito.mock(ControleKmRepository.class);
        controleKmUpdateService = new ControleKmUpdateService(controleKmRepository);
    }

    @Test
    void deveAtualizarKmAtualComSucesso() {
        Long id = 1L;
        Integer kmNovo = 5000;

        ControleKm controleKmExistente = new ControleKm();
        controleKmExistente.setId(id);
        controleKmExistente.setKmAtual(4500);
        controleKmExistente.setDataAtual(LocalDateTime.now().minusDays(1));

        when(controleKmRepository.findById(id)).thenReturn(Optional.of(controleKmExistente));
        when(controleKmRepository.save(any(ControleKm.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ControleKm atualizado = controleKmUpdateService.atualizarKmAtual(id, kmNovo);

        assertEquals(kmNovo, atualizado.getKmAtual());
        assertNotNull(atualizado.getDataAtual());
        verify(controleKmRepository, times(1)).findById(id);
        verify(controleKmRepository, times(1)).save(controleKmExistente);
    }

    @Test
    void deveLancarExcecaoSeControleKmNaoExistir() {
        Long id = 999L;
        when(controleKmRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            controleKmUpdateService.atualizarKmAtual(id, 1000);
        });

        assertEquals("Controle de KM não encontrado com id: " + id, exception.getMessage());
    }
}
