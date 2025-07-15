// Caminho: src/test/java/com/oksmart/kmcontrolapi/service/update/VeiculoAtualizarCondutorPrincipalServiceTest.java

package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.AtualizarCondutorPrincipalRequest;
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

class VeiculoAtualizarCondutorPrincipalServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoAtualizarCondutorPrincipalService service;

    @BeforeEach
    void setup() {
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
                .condutorPrincipal("Ana Beatriz")
                .build();

        VeiculoResponse response = service.atualizar(id, request);

        assertEquals("Ana Beatriz", response.getCondutorPrincipal());
        verify(veiculoRepository).save(veiculo);
    }

    @Test
    void deveLancarExcecaoSeNaoEncontrarVeiculo() {
        when(veiculoRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(VeiculoNotFoundException.class, () ->
                service.atualizar(999L, new AtualizarCondutorPrincipalRequest("Teste")));
    }
}
