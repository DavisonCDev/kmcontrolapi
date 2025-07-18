// src/test/java/com/oksmart/kmcontrolapi/service/list/ContratoListServiceTest.java
package com.oksmart.kmcontrolapi.service.list;

import com.oksmart.kmcontrolapi.model.Contrato;
import com.oksmart.kmcontrolapi.repository.ContratoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ContratoListServiceTest {

    private ContratoRepository contratoRepository;
    private ContratoListService contratoListService;

    @BeforeEach
    void setUp() {
        contratoRepository = Mockito.mock(ContratoRepository.class);
        contratoListService = new ContratoListService(contratoRepository);
    }

    @Test
    void deveListarTodosContratos() {
        Contrato contrato1 = new Contrato();
        contrato1.setNumeroContrato("CT001");

        Contrato contrato2 = new Contrato();
        contrato2.setNumeroContrato("CT002");

        when(contratoRepository.findAll()).thenReturn(Arrays.asList(contrato1, contrato2));

        List<Contrato> lista = contratoListService.listarTodos();

        assertEquals(2, lista.size());
        assertEquals("CT001", lista.get(0).getNumeroContrato());
        assertEquals("CT002", lista.get(1).getNumeroContrato());
        verify(contratoRepository, times(1)).findAll();
    }
}
