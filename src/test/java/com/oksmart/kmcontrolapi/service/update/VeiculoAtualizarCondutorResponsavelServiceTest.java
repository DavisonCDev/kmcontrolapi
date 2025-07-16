// Caminho: src/test/java/com/oksmart/kmcontrolapi/service/update/VeiculoAtualizarCondutorResponsavelServiceTest.java

package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.AtualizarCondutorResponsavelRequest;
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

class VeiculoAtualizarCondutorResponsavelServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @Mock
    private RegistroHistoricoService registroHistoricoService;

    @InjectMocks
    private VeiculoAtualizarCondutorResponsavelService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarCondutorResponsavelComSucesso() {
        Long id = 1L;
        Veiculo veiculo = Veiculo.builder()
                .id(id)
                .condutorResponsavel("Carlos")
                .build();

        when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculo));

        AtualizarCondutorResponsavelRequest request = AtualizarCondutorResponsavelRequest.builder()
                .condutorResponsavel("Ana Paula")
                .build();

        VeiculoResponse response = service.atualizar(id, request);

        assertEquals("Ana Paula", response.getCondutorResponsavel());
        assertEquals(LocalDate.now(), veiculo.getDataAtual());

        verify(veiculoRepository).save(veiculo);

        verify(registroHistoricoService).registrar(
                eq("ATUALIZADO"),
                eq("Veiculo"),
                eq(id),
                eq("Condutor Responsável alterado para: Ana Paula")
        );
    }

    @Test
    void deveLancarExcecaoSeVeiculoNaoForEncontrado() {
        when(veiculoRepository.findById(999L)).thenReturn(Optional.empty());

        AtualizarCondutorResponsavelRequest request = AtualizarCondutorResponsavelRequest.builder()
                .condutorResponsavel("Outro")
                .build();

        assertThrows(VeiculoNotFoundException.class, () ->
                service.atualizar(999L, request));

        verify(registroHistoricoService, never()).registrar(any(), any(), any(), any());
    }
}