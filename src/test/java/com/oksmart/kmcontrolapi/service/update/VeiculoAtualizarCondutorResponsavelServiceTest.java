// Caminho: src/test/java/com/oksmart/kmcontrolapi/service/update/VeiculoAtualizarCondutorResponsavelServiceTest.java

package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.AtualizarCondutorResponsavelRequest;
import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VeiculoAtualizarCondutorResponsavelServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

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
                .condutorResponsavel("Maria")
                .build();

        when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculo));

        AtualizarCondutorResponsavelRequest request = AtualizarCondutorResponsavelRequest.builder()
                .condutorResponsavel("Carlos Eduardo")
                .build();

        VeiculoResponse response = service.atualizar(id, request);

        assertEquals("Carlos Eduardo", response.getCondutorResponsavel());
        verify(veiculoRepository).save(veiculo);
    }

    @Test
    void deveLancarExcecaoSeNaoEncontrarVeiculo() {
        when(veiculoRepository.findById(999L)).thenReturn(Optional.empty());

        AtualizarCondutorResponsavelRequest request = AtualizarCondutorResponsavelRequest.builder()
                .condutorResponsavel("Inexistente")
                .build();

        assertThrows(VeiculoNotFoundException.class, () ->
                service.atualizar(999L, request));
    }
}
