// Caminho: src/test/java/com/oksmart/kmcontrolapi/service/list/VeiculoListServiceTest.java

package com.oksmart.kmcontrolapi.service.list;

import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VeiculoListServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoListService veiculoListService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarListaDeVeiculosComTodosOsCampos() {
        Veiculo veiculo = Veiculo.builder()
                .id(1L)
                .marca("Ford")
                .modelo("Ka")
                .cor("Preto")
                .placa("XYZ1234")
                .kmInicial(1000)
                .kmAtual(1001)
                .dataRegistro(LocalDate.of(2025, 7, 1))
                .condutorPrincipal("Carlos Silva")
                .condutorResponsavel("Paulo Lima")
                .dataAtual(LocalDate.of(2025, 7, 2))
                .diarias(7)
                .franquiaKm(2000)
                .locadora("Unidas")
                .numeroContrato("CT20250701")
                .osCliente("OS7777")
                .valorAluguel(new BigDecimal("1750.00"))
                .build();

        when(veiculoRepository.findAll()).thenReturn(List.of(veiculo));

        List<VeiculoResponse> resposta = veiculoListService.listarTodos();

        assertEquals(1, resposta.size());

        VeiculoResponse r = resposta.get(0);
        assertEquals("Ford", r.getMarca());
        assertEquals("Ka", r.getModelo());
        assertEquals("XYZ1234", r.getPlaca());
        assertEquals("Carlos Silva", r.getCondutorPrincipal());
        assertEquals("Paulo Lima", r.getCondutorResponsavel());
        assertEquals(LocalDate.of(2025, 7, 2), r.getDataAtual());
        assertEquals(7, r.getDiarias());
        assertEquals(2000, r.getFranquiaKm());
        assertEquals("Unidas", r.getLocadora());
        assertEquals("CT20250701", r.getNumeroContrato());
        assertEquals("OS7777", r.getOsCliente());
        assertEquals(new BigDecimal("1750.00"), r.getValorAluguel());

        verify(veiculoRepository, times(1)).findAll();
    }
}
