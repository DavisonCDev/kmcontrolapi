// Caminho: src/test/java/com/oksmart/kmcontrolapi/controller/integration/VeiculoAtualizarCondutorPrincipalIntegrationTest.java

package com.oksmart.kmcontrolapi.controller.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oksmart.kmcontrolapi.dto.AtualizarCondutorPrincipalRequest;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VeiculoAtualizarCondutorPrincipalIntegrationTest {

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
                .marca("Volkswagen")
                .modelo("Polo")
                .cor("Branco")
                .placa("VWX1234")
                .kmInicial(8000)
                .dataRegistro(LocalDate.now())
                .condutorPrincipal("Lucas")
                .build();

        veiculoId = veiculoRepository.save(veiculo).getId();
    }

    @Test
    void deveAtualizarCondutorPrincipalComSucesso() throws Exception {
        AtualizarCondutorPrincipalRequest request = AtualizarCondutorPrincipalRequest.builder()
                .condutorPrincipal("João da Silva")
                .build();

        mockMvc.perform(patch("/veiculos/{id}/condutor-principal", veiculoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.condutorPrincipal").value("João da Silva"));
    }
}
