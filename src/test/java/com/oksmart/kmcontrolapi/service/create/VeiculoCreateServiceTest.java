// Caminho: src/test/java/com/oksmart/kmcontrolapi/service/create/VeiculoCreateServiceTest.java

package com.oksmart.kmcontrolapi.service.create;

import com.oksmart.kmcontrolapi.dto.VeiculoCreateRequest;
import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
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
        LocalDate dataRegistro = LocalDate.of(2025, 7, 1);
        LocalDate dataAtual = LocalDate.of(2025, 7, 2);

        VeiculoCreateRequest request = VeiculoCreateRequest.builder()
                .marca("Chevrolet")
                .modelo("Onix")
                .cor("Branco")
                .placa("DEF5678")
                .kmInicial(5000)
                .kmAtual(5001)
                .dataRegistro(dataRegistro)
                .condutorPrincipal("Ana Souza")
                .condutorResponsavel("João Lima")
                .diarias(12)
                .franquiaKm(1500)
                .locadora("Localiza")
                .numeroContrato("CT20250701")
                .osCliente("OS123456")
                .valorAluguel(new BigDecimal("1899.90"))
                .build();

        Veiculo veiculoSalvo = Veiculo.builder()
                .id(10L)
                .marca(request.getMarca())
                .modelo(request.getModelo())
                .cor(request.getCor())
                .placa(request.getPlaca())
                .kmInicial(request.getKmInicial())
                .dataRegistro(request.getDataRegistro())
                .condutorPrincipal(request.getCondutorPrincipal())
                .condutorResponsavel(request.getCondutorResponsavel())
                .diarias(request.getDiarias())
                .franquiaKm(request.getFranquiaKm())
                .locadora(request.getLocadora())
                .numeroContrato(request.getNumeroContrato())
                .osCliente(request.getOsCliente())
                .valorAluguel(request.getValorAluguel())
                .build();

        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculoSalvo);

        // Act
        VeiculoResponse response = veiculoCreateService.criar(request);

        // Assert
        assertNotNull(response);
        assertEquals(10L, response.getId());
        assertEquals("Chevrolet", response.getMarca());
        assertEquals("Onix", response.getModelo());
        assertEquals("Ana Souza", response.getCondutorPrincipal());
        assertEquals("João Lima", response.getCondutorResponsavel());
        assertEquals(LocalDate.of(2025, 7, 2), response.getDataAtual());
        assertEquals(12, response.getDiarias());
        assertEquals(1500, response.getFranquiaKm());
        assertEquals("Localiza", response.getLocadora());
        assertEquals("CT20250701", response.getNumeroContrato());
        assertEquals("OS123456", response.getOsCliente());
        assertEquals(new BigDecimal("1899.90"), response.getValorAluguel());

        verify(veiculoRepository, times(1)).save(any(Veiculo.class));
    }
}
