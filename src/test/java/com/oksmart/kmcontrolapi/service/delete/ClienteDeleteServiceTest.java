// src/test/java/com/oksmart/kmcontrolapi/service/delete/ClienteDeleteServiceTest.java
package com.oksmart.kmcontrolapi.service.delete;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ClienteDeleteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteDeleteService deleteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteCliente_Success() {
        Long id = 1L;

        when(clienteRepository.existsById(id)).thenReturn(true);
        doNothing().when(clienteRepository).deleteById(id);

        deleteService.deletarPorId(id);

        verify(clienteRepository, times(1)).existsById(id);
        verify(clienteRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteCliente_NotFound() {
        Long id = 1L;

        when(clienteRepository.existsById(id)).thenReturn(false);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            deleteService.deletarPorId(id);
        });

        assert(exception.getMessage().contains("Cliente não encontrado"));
        verify(clienteRepository, times(1)).existsById(id);
        verify(clienteRepository, never()).deleteById(id);
    }
}
