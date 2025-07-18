// src/test/java/com/oksmart/kmcontrolapi/service/update/ClienteUpdateServiceTest.java
package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.model.Cliente;
import com.oksmart.kmcontrolapi.model.Endereco;
import com.oksmart.kmcontrolapi.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteUpdateServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteUpdateService updateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateCliente_Success() {
        Cliente existente = Cliente.builder()
                .id(1L)
                .nome("Ana")
                .os("OS0001")
                .endereco(Endereco.builder().cep("12345-678").build())
                .build();

        Cliente atualizado = Cliente.builder()
                .nome("Ana Maria")
                .os("OS0001-UPDATED")
                .endereco(Endereco.builder().cep("98765-432").build())
                .build();

        when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.of(existente));
        when(clienteRepository.save(any(Cliente.class))).thenAnswer(i -> i.getArgument(0));

        Cliente resultado = updateService.updateCliente(1L, atualizado);

        assertNotNull(resultado);
        assertEquals("Ana Maria", resultado.getNome());
        assertEquals("OS0001-UPDATED", resultado.getOs());
        assertEquals("98765-432", resultado.getEndereco().getCep());

        verify(clienteRepository, times(1)).findById(1L);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testUpdateCliente_NotFound() {
        Cliente atualizado = Cliente.builder()
                .nome("Ana Maria")
                .os("OS0001-UPDATED")
                .build();

        when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            updateService.updateCliente(1L, atualizado);
        });

        assertEquals("Cliente não encontrado com id: 1", exception.getMessage());
        verify(clienteRepository, times(1)).findById(1L);
        verify(clienteRepository, never()).save(any(Cliente.class));
    }
}
