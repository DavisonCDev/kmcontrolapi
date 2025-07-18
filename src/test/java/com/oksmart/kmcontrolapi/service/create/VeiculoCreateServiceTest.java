// src/test/java/com/oksmart/kmcontrolapi/service/create/VeiculoCreateServiceTest.java
package com.oksmart.kmcontrolapi.service.create;

import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class VeiculoCreateServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoCreateService createService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateVeiculo() {
        Veiculo veiculo = Veiculo.builder()
                .marca("Toyota")
                .modelo("Corolla")
                .placa("ABC1234")
                .cor("Prata")
                .locadora("Locadora X")
                .km(10000)
                .build();

        when(veiculoRepository.save(veiculo)).thenReturn(veiculo);

        Veiculo criado = createService.createVeiculo(veiculo);

        assertNotNull(criado);
        assertEquals("Toyota", criado.getMarca());
        verify(veiculoRepository, times(1)).save(veiculo);
    }
}
