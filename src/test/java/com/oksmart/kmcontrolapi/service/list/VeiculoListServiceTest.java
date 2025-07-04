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
    void deveRetornarListaDeVeiculos() {
        Veiculo veiculo = Veiculo.builder()
                .id(1L)
                .marca("Ford")
                .modelo("Ka")
                .cor("Preto")
                .placa("XYZ1234")
                .kmInicial(1000)
                .dataRegistro(LocalDate.now())
                .build();

        when(veiculoRepository.findAll()).thenReturn(List.of(veiculo));

        List<VeiculoResponse> resposta = veiculoListService.listarTodos();

        assertEquals(1, resposta.size());
        assertEquals("Ford", resposta.get(0).getMarca());
        verify(veiculoRepository, times(1)).findAll();
    }
}
