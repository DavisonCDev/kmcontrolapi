// Caminho: src/test/java/com/oksmart/kmcontrolapi/service/update/VeiculoUpdateServiceTest.java

package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.VeiculoUpdateRequest;
import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VeiculoUpdateServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoUpdateService veiculoUpdateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarVeiculoQuandoIdExistir() {
        Long id = 1L;

        Veiculo existente = Veiculo.builder()
                .id(id)
                .marca("Fiat")
                .modelo("Uno")
                .cor("Vermelho")
                .placa("ABC1234")
                .kmInicial(10000)
                .kmAtual(10001)
                .dataRegistro(LocalDate.of(2025, 6, 30))
                .build();

        VeiculoUpdateRequest request = VeiculoUpdateRequest.builder()
                .marca("Toyota")
                .modelo("Corolla")
                .cor("Preto")
                .placa("XYZ9999")
                .kmInicial(12000)
                .dataRegistro(LocalDate.of(2025, 7, 1))
                .condutorPrincipal("João Silva")
                .condutorResponsavel("Carlos Souza")
                .dataAtual(LocalDate.of(2025, 7, 2))
                .diarias(15)
                .franquiaKm(3000)
                .locadora("Localiza")
                .numeroContrato("CT123456")
                .osCliente("OS98765")
                .valorAluguel(new BigDecimal("1999.99"))
                .build();

        Veiculo atualizado = Veiculo.builder()
                .id(id)
                .marca(request.getMarca())
                .modelo(request.getModelo())
                .cor(request.getCor())
                .placa(request.getPlaca())
                .kmInicial(request.getKmInicial())
                .dataRegistro(request.getDataRegistro())
                .condutorPrincipal(request.getCondutorPrincipal())
                .condutorResponsavel(request.getCondutorResponsavel())
                .dataAtual(request.getDataAtual())
                .diarias(request.getDiarias())
                .franquiaKm(request.getFranquiaKm())
                .locadora(request.getLocadora())
                .numeroContrato(request.getNumeroContrato())
                .osCliente(request.getOsCliente())
                .valorAluguel(request.getValorAluguel())
                .build();

        when(veiculoRepository.findById(id)).thenReturn(Optional.of(existente));
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(atualizado);

        // Act
        VeiculoResponse response = veiculoUpdateService.atualizar(id, request);

        // Assert
        assertNotNull(response);
        assertEquals("Toyota", response.getMarca());
        assertEquals("Corolla", response.getModelo());
        assertEquals("João Silva", response.getCondutorPrincipal());
        assertEquals("Carlos Souza", response.getCondutorResponsavel());
        assertEquals(LocalDate.of(2025, 7, 2), response.getDataAtual());
        assertEquals(15, response.getDiarias());
        assertEquals(3000, response.getFranquiaKm());
        assertEquals("Localiza", response.getLocadora());
        assertEquals("CT123456", response.getNumeroContrato());
        assertEquals("OS98765", response.getOsCliente());
        assertEquals(new BigDecimal("1999.99"), response.getValorAluguel());

        verify(veiculoRepository).save(any(Veiculo.class));
    }

    @Test
    void deveLancarExcecaoQuandoVeiculoNaoExistir() {
        Long idInvalido = 999L;

        when(veiculoRepository.findById(idInvalido)).thenReturn(Optional.empty());

        VeiculoUpdateRequest request = VeiculoUpdateRequest.builder()
                .marca("Honda")
                .modelo("Civic")
                .cor("Cinza")
                .placa("HON1234")
                .kmInicial(15000)
                .dataRegistro(LocalDate.now())
                .build();

        assertThrows(VeiculoNotFoundException.class, () -> {
            veiculoUpdateService.atualizar(idInvalido, request);
        });

        verify(veiculoRepository, never()).save(any());
    }
}
