// Caminho: src/test/java/com/oksmart/kmcontrolapi/controller/integration/VeiculoDeleteIntegrationTest.java

package com.oksmart.kmcontrolapi.controller.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VeiculoDeleteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Long veiculoId;

    @BeforeEach
    void setup() {
        veiculoRepository.deleteAll();

        Veiculo veiculo = Veiculo.builder()
                .marca("Renault")
                .modelo("Kwid")
                .cor("Vermelho")
                .placa("JKL9101")
                .kmInicial(15000)
                .dataRegistro(LocalDate.now())
                .build();

        veiculoId = veiculoRepository.save(veiculo).getId();
    }

    @Test
    void deveDeletarVeiculoComSucesso() throws Exception {
        mockMvc.perform(delete("/veiculos/{id}", veiculoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/veiculos/{id}", veiculoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
