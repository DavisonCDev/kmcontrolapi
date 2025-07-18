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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
    void deveListarTodosOsVeiculosComSucesso() {
        Veiculo v1 = Veiculo.builder()
                .id(1L)
                .marca("Toyota")
                .modelo("Corolla")
                .cor("Prata")
                .placa("ABC1234")
                .kmInicial(10000)
                .kmAtual(12000)
                .dataRegistro(LocalDate.of(2024, 1, 10))
                .build();

        Veiculo v2 = Veiculo.builder()
                .id(2L)
                .marca("Ford")
                .modelo("Fiesta")
                .cor("Preto")
                .placa("XYZ5678")
                .kmInicial(5000)
                .kmAtual(7000)
                .dataRegistro(LocalDate.of(2024, 2, 5))
                .build();

        when(veiculoRepository.findAll()).thenReturn(Arrays.asList(v1, v2));

        List<VeiculoResponse> lista = veiculoListService.listarTodos();

        assertEquals(2, lista.size());
        assertEquals("Toyota", lista.get(0).getMarca());
        assertEquals("Ford", lista.get(1).getMarca());
        assertEquals("Fiesta", lista.get(1).getModelo());
    }
}
