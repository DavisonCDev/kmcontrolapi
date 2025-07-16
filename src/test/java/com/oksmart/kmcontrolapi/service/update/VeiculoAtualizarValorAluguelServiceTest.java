// Caminho: src/test/java/com/oksmart/kmcontrolapi/service/update/VeiculoAtualizarValorAluguelServiceTest.java

package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.AtualizarValorAluguelRequest;
import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import com.oksmart.kmcontrolapi.service.historico.RegistroHistoricoService;
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

    @Mock
    private RegistroHistoricoService registroHistoricoService;

    @InjectMocks
    private VeiculoAtualizarValorAluguelService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarValorAluguelComSucesso() {
        Long id = 1L;
        Veiculo veiculo = Veiculo.builder()
                .id(id)
                .valorAluguel(new BigDecimal("100.00"))
                .build();

        when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculo));

        AtualizarValorAluguelRequest request = AtualizarValorAluguelRequest.builder()
                .valorAluguel(new BigDecimal("150.75"))
                .build();

        VeiculoResponse response = service.atualizarValor(id, request);

        assertEquals(new BigDecimal("150.75"), response.getValorAluguel());
        assertEquals(LocalDate.now(), veiculo.getDataAtual());

        verify(veiculoRepository).save(veiculo);

        verify(registroHistoricoService).registrar(
                eq("ATUALIZADO"),
                eq("Veiculo"),
                eq(id),
                eq("Valor do aluguel alterado para: 150.75")
        );
    }

    @Test
    void deveLancarExcecaoSeVeiculoNaoExistir() {
        when(veiculoRepository.findById(999L)).thenReturn(Optional.empty());

        AtualizarValorAluguelRequest request = AtualizarValorAluguelRequest.builder()
                .valorAluguel(new BigDecimal("123.45"))
                .build();

        assertThrows(VeiculoNotFoundException.class, () ->
                service.atualizarValor(999L, request));

        verify(registroHistoricoService, never()).registrar(any(), any(), any(), any());
    }
}