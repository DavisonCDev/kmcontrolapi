// Caminho: src/test/java/com/oksmart/kmcontrolapi/service/update/VeiculoAtualizarValorAluguelServiceTest.java

package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.AtualizarValorAluguelRequest;
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

class VeiculoAtualizarValorAluguelServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoAtualizarValorAluguelService veiculoAtualizarValorAluguelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarValorAluguelComSucesso() {
        Long id = 1L;
        BigDecimal novoValor = new BigDecimal("1999.99");

        Veiculo veiculoExistente = Veiculo.builder()
                .id(id)
                .marca("Fiat")
                .modelo("Argo")
                .valorAluguel(new BigDecimal("1500.00"))
                .dataRegistro(LocalDate.of(2025, 6, 1))
                .build();

        when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculoExistente));
        when(veiculoRepository.save(any(Veiculo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AtualizarValorAluguelRequest request = AtualizarValorAluguelRequest.builder()
                .valorAluguel(novoValor)
                .build();

        VeiculoResponse response = veiculoAtualizarValorAluguelService.atualizarValor(id, request);

        assertNotNull(response);
        assertEquals(novoValor, response.getValorAluguel());
        verify(veiculoRepository).save(veiculoExistente);
    }

    @Test
    void deveLancarExcecaoSeVeiculoNaoExistir() {
        Long idInvalido = 999L;
        when(veiculoRepository.findById(idInvalido)).thenReturn(Optional.empty());

        AtualizarValorAluguelRequest request = AtualizarValorAluguelRequest.builder()
                .valorAluguel(new BigDecimal("1800.00"))
                .build();

        assertThrows(VeiculoNotFoundException.class,
                () -> veiculoAtualizarValorAluguelService.atualizarValor(idInvalido, request));

        verify(veiculoRepository, never()).save(any());
    }
}
