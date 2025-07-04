// Caminho: src/test/java/com/oksmart/kmcontrolapi/service/create/VeiculoCreateServiceTest.java

package com.oksmart.kmcontrolapi.service.create;

import com.oksmart.kmcontrolapi.dto.VeiculoCreateRequest;
import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VeiculoCreateServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoCreateService veiculoCreateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarVeiculoComSucesso() {
        // Arrange
        VeiculoCreateRequest request = VeiculoCreateRequest.builder()
                .marca("Chevrolet")
                .modelo("Onix")
                .cor("Branco")
                .placa("DEF5678")
                .kmInicial(5000)
                .dataRegistro(LocalDate.now())
                .build();

        Veiculo veiculoSalvo = Veiculo.builder()
                .id(10L)
                .marca("Chevrolet")
                .modelo("Onix")
                .cor("Branco")
                .placa("DEF5678")
                .kmInicial(5000)
                .dataRegistro(request.getDataRegistro())
                .build();

        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculoSalvo);

        // Act
        VeiculoResponse response = veiculoCreateService.criar(request);

        // Assert
        assertNotNull(response);
        assertEquals(10L, response.getId());
        assertEquals("Chevrolet", response.getMarca());
        assertEquals("Onix", response.getModelo());

        verify(veiculoRepository, times(1)).save(any(Veiculo.class));
    }
}
