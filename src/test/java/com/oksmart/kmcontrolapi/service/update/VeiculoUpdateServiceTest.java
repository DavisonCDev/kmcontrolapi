// Caminho: src/test/java/com/oksmart/kmcontrolapi/service/update/VeiculoUpdateServiceTest.java

package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.dto.VeiculoUpdateRequest;
import com.oksmart.kmcontrolapi.dto.VeiculoResponse;
import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VeiculoUpdateServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoUpdateService veiculoUpdateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarVeiculoQuandoIdExistir() {
        Long id = 1L;

        Veiculo existente = Veiculo.builder()
                .id(id)
                .marca("Fiat")
                .modelo("Uno")
                .cor("Vermelho")
                .placa("ABC1234")
                .kmInicial(10000)
                .dataRegistro(LocalDate.now().minusDays(1))
                .build();

        VeiculoUpdateRequest request = VeiculoUpdateRequest.builder()
                .marca("Toyota")
                .modelo("Corolla")
                .cor("Preto")
                .placa("XYZ9999")
                .kmInicial(12000)
                .dataRegistro(LocalDate.now())
                .build();

        Veiculo atualizado = Veiculo.builder()
                .id(id)
                .marca("Toyota")
                .modelo("Corolla")
                .cor("Preto")
                .placa("XYZ9999")
                .kmInicial(12000)
                .dataRegistro(request.getDataRegistro())
                .build();

        when(veiculoRepository.findById(id)).thenReturn(Optional.of(existente));
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(atualizado);

        VeiculoResponse response = veiculoUpdateService.atualizar(id, request);

        assertNotNull(response);
        assertEquals("Toyota", response.getMarca());
        assertEquals("Corolla", response.getModelo());
        verify(veiculoRepository).save(any(Veiculo.class));
    }

    @Test
    void deveLancarExcecaoQuandoVeiculoNaoExistir() {
        Long idInvalido = 999L;

        when(veiculoRepository.findById(idInvalido)).thenReturn(Optional.empty());

        VeiculoUpdateRequest request = VeiculoUpdateRequest.builder()
                .marca("Honda")
                .modelo("Civic")
                .cor("Cinza")
                .placa("HON1234")
                .kmInicial(15000)
                .dataRegistro(LocalDate.now())
                .build();

        assertThrows(VeiculoNotFoundException.class, () -> {
            veiculoUpdateService.atualizar(idInvalido, request);
        });

        verify(veiculoRepository, never()).save(any());
    }
}
