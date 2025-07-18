// src/test/java/com/oksmart/kmcontrolapi/service/delete/ContratoDeleteServiceTest.java
package com.oksmart.kmcontrolapi.service.delete;

import com.oksmart.kmcontrolapi.repository.ContratoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class ContratoDeleteServiceTest {

    private ContratoRepository contratoRepository;
    private ContratoDeleteService contratoDeleteService;

    @BeforeEach
    void setUp() {
        contratoRepository = Mockito.mock(ContratoRepository.class);
        contratoDeleteService = new ContratoDeleteService(contratoRepository);
    }

    @Test
    void deveDeletarContratoPorId() {
        Long idContrato = 1L;

        contratoDeleteService.deletarPorId(idContrato);

        verify(contratoRepository, times(1)).deleteById(idContrato);
    }
}
