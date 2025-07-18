// Caminho: src/test/java/com/oksmart/kmcontrolapi/controller/integration/VeiculoListIntegrationTest.java

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VeiculoListIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        veiculoRepository.deleteAll();

        Veiculo veiculo1 = Veiculo.builder()
                .marca("Chevrolet")
                .modelo("Onix")
                .cor("Preto")
                .placa("XYZ1234")
                .kmInicial(20000)
                .dataRegistro(LocalDate.now())
                .build();

        Veiculo veiculo2 = Veiculo.builder()
                .marca("Toyota")
                .modelo("Corolla")
                .cor("Branco")
                .placa("DEF5678")
                .kmInicial(50000)
                .dataRegistro(LocalDate.now())
                .build();

        veiculoRepository.save(veiculo1);
        veiculoRepository.save(veiculo2);
    }

    @Test
    void deveListarTodosVeiculosComGET() throws Exception {
        mockMvc.perform(get("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].placa").value("XYZ1234"))
                .andExpect(jsonPath("$[1].placa").value("DEF5678"));
    }
}
