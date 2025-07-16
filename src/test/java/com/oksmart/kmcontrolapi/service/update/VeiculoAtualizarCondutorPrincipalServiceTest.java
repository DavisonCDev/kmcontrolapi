// Caminho: src/test/java/com/oksmart/kmcontrolapi/service/update/VeiculoAtualizarCondutorPrincipalServiceTest.java

package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.AtualizarCondutorPrincipalRequest;
import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import com.oksmart.kmcontrolapi.service.historico.RegistroHistoricoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VeiculoAtualizarCondutorPrincipalServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @Mock
    private RegistroHistoricoService registroHistoricoService;

    @InjectMocks
    private VeiculoAtualizarCondutorPrincipalService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarCondutorPrincipalComSucesso() {
        Long id = 1L;
        Veiculo veiculo = Veiculo.builder()
                .id(id)
                .condutorPrincipal("João")
                .build();

        when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculo));

        AtualizarCondutorPrincipalRequest request = AtualizarCondutorPrincipalRequest.builder()
                .condutorPrincipal("Maria")
                .build();

        VeiculoResponse response = service.atualizar(id, request);

        assertEquals("Maria", response.getCondutorPrincipal());
        assertEquals(LocalDate.now(), veiculo.getDataAtual());
        verify(veiculoRepository).save(veiculo);

        verify(registroHistoricoService).registrar(
                eq("ATUALIZADO"),
                eq("Veiculo"),
                eq(id),
                eq("Condutor Principal alterado para: Maria")
        );
    }

    @Test
    void deveLancarExcecaoSeVeiculoNaoExistir() {
        when(veiculoRepository.findById(999L)).thenReturn(Optional.empty());

        AtualizarCondutorPrincipalRequest request = AtualizarCondutorPrincipalRequest.builder()
                .condutorPrincipal("Outro")
                .build();

        assertThrows(VeiculoNotFoundException.class, () ->
                service.atualizar(999L, request));

        verify(registroHistoricoService, never()).registrar(any(), any(), any(), any());
    }
}
