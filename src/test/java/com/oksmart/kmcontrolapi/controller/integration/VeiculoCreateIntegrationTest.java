// Caminho: src/test/java/com/oksmart/kmcontrolapi/controller/integration/VeiculoCreateIntegrationTest.java

package com.oksmart.kmcontrolapi.controller.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oksmart.kmcontrolapi.dto.VeiculoCreateRequest;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VeiculoCreateIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        veiculoRepository.deleteAll();
    }

    @Test
    void deveCriarVeiculoComPOST() throws Exception {
        VeiculoCreateRequest request = VeiculoCreateRequest.builder()
                .marca("Fiat")
                .modelo("Uno")
                .cor("Vermelho")
                .placa("ABC1234")
                .kmInicial(10000)
                .dataRegistro(LocalDate.now())
                .build();

        mockMvc.perform(post("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.placa").value("ABC1234"))
                .andExpect(jsonPath("$.marca").value("Fiat"))
                .andExpect(jsonPath("$.modelo").value("Uno"))
                .andExpect(jsonPath("$.cor").value("Vermelho"))
                .andExpect(jsonPath("$.kmInicial").value(10000));
    }
}
