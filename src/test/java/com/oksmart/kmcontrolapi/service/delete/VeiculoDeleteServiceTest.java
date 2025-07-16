// Caminho: src/test/java/com/oksmart/kmcontrolapi/service/delete/VeiculoDeleteServiceTest.java

package com.oksmart.kmcontrolapi.service.delete;

import com.oksmart.kmcontrolapi.exception.VeiculoNotFoundException;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import com.oksmart.kmcontrolapi.service.historico.RegistroHistoricoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class VeiculoDeleteServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @Mock
    private RegistroHistoricoService registroHistoricoService;

    @InjectMocks
    private VeiculoDeleteService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveDeletarVeiculoComRegistroDeHistorico() {
        Long id = 1L;
        Veiculo veiculo = Veiculo.builder()
                .id(id)
                .placa("XYZ-1234")
                .build();

        when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculo));

        service.deletar(id);

        verify(veiculoRepository).deleteById(id);
        verify(registroHistoricoService).registrar(
                eq("DELETADO"),
                eq("Veiculo"),
                eq(id),
                eq("Veículo com placa XYZ-1234 foi deletado do sistema")
        );
    }

    @Test
    void deveLancarExcecaoSeNaoEncontrarVeiculo() {
        Long id = 999L;
        when(veiculoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(VeiculoNotFoundException.class, () -> service.deletar(id));

        verify(veiculoRepository, never()).deleteById(any());
        verify(registroHistoricoService, never()).registrar(any(), any(), any(), any());
    }
}
