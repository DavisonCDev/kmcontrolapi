// Caminho: src/test/java/com/oksmart/kmcontrolapi/service/update/VeiculoAtualizarOsClienteServiceTest.java

package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.AtualizarOsClienteRequest;
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

class VeiculoAtualizarOsClienteServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoAtualizarOsClienteService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarOsClienteComSucesso() {
        Long id = 1L;
        Veiculo veiculo = Veiculo.builder()
                .id(id)
                .osCliente("OS001")
                .build();

        when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculo));

        AtualizarOsClienteRequest request = AtualizarOsClienteRequest.builder()
                .osCliente("OS78910")
                .build();

        VeiculoResponse response = service.atualizar(id, request);

        assertEquals("OS78910", response.getOsCliente());
        verify(veiculoRepository).save(veiculo);
    }

    @Test
    void deveLancarExcecaoSeVeiculoNaoExistir() {
        when(veiculoRepository.findById(999L)).thenReturn(Optional.empty());

        AtualizarOsClienteRequest request = AtualizarOsClienteRequest.builder()
                .osCliente("Qualquer")
                .build();

        assertThrows(VeiculoNotFoundException.class, () ->
                service.atualizar(999L, request));
    }


}
