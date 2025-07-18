// src/test/java/com/oksmart/kmcontrolapi/service/create/ClienteCreateServiceTest.java
package com.oksmart.kmcontrolapi.service.create;

import com.oksmart.kmcontrolapi.model.Cliente;
import com.oksmart.kmcontrolapi.model.Endereco;
import com.oksmart.kmcontrolapi.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ClienteCreateServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteCreateService createService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCliente() {
        Cliente cliente = Cliente.builder()
                .nome("Ana")
                .os("OS0001")
                .endereco(Endereco.builder()
                        .cep("12345-678")
                        .rua("Rua 1")
                        .numero("10")
                        .bairro("Centro")
                        .cidade("Cidade X")
                        .estado("Estado Y")
                        .pais("Brasil")
                        .build())
                .build();

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente criado = createService.createCliente(cliente);

        assertNotNull(criado);
        assertEquals("Ana", criado.getNome());
        verify(clienteRepository, times(1)).save(cliente);
    }
}
