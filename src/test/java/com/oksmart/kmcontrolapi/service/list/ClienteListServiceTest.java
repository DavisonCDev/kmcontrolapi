// src/test/java/com/oksmart/kmcontrolapi/service/list/ClienteListServiceTest.java
package com.oksmart.kmcontrolapi.service.list;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.model.Cliente;
import com.oksmart.kmcontrolapi.model.Endereco;
import com.oksmart.kmcontrolapi.repository.ClienteRepository;
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

class ClienteListServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteListService listService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAll() {
        Cliente c1 = Cliente.builder()
                .id(1L)
                .nome("Ana")
                .os("OS0001")
                .endereco(Endereco.builder().cep("12345-678").build())
                .build();
        Cliente c2 = Cliente.builder()
                .id(2L)
                .nome("Beto")
                .os("OS0002")
                .endereco(Endereco.builder().cep("87654-321").build())
                .build();

        when(clienteRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Cliente> clientes = listService.listAll();

        assertNotNull(clientes);
        assertEquals(2, clientes.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Found() {
        Cliente cliente = Cliente.builder()
                .id(1L)
                .nome("Ana")
                .os("OS0001")
                .endereco(Endereco.builder().cep("12345-678").build())
                .build();

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente resultado = listService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Ana", resultado.getNome());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            listService.findById(1L);
        });

        assertEquals("Cliente não encontrado com id: 1", exception.getMessage());
        verify(clienteRepository, times(1)).findById(1L);
    }
}
