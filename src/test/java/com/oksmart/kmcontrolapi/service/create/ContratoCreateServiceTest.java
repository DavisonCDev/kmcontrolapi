// src/test/java/com/oksmart/kmcontrolapi/service/create/ContratoCreateServiceTest.java
package com.oksmart.kmcontrolapi.service.create;

import com.oksmart.kmcontrolapi.model.Contrato;
import com.oksmart.kmcontrolapi.repository.ContratoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ContratoCreateServiceTest {

    private ContratoRepository contratoRepository;
    private ContratoCreateService contratoCreateService;

    @BeforeEach
    void setUp() {
        contratoRepository = Mockito.mock(ContratoRepository.class);
        contratoCreateService = new ContratoCreateService(contratoRepository);
    }

    @Test
    void deveSalvarContratoComSucesso() {
        Contrato contrato = new Contrato();
        contrato.setNumeroContrato("CT123");

        when(contratoRepository.save(contrato)).thenReturn(contrato);

        Contrato salvo = contratoCreateService.salvarContrato(contrato);

        assertNotNull(salvo);
        assertEquals("CT123", salvo.getNumeroContrato());
        verify(contratoRepository, times(1)).save(contrato);
    }
}
