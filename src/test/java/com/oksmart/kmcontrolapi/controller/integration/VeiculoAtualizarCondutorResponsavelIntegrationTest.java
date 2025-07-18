// Caminho: src/test/java/com/oksmart/kmcontrolapi/controller/integration/VeiculoAtualizarCondutorResponsavelIntegrationTest.java

package com.oksmart.kmcontrolapi.controller.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oksmart.kmcontrolapi.dto.AtualizarCondutorResponsavelRequest;
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
class VeiculoAtualizarCondutorResponsavelIntegrationTest {

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
                .modelo("Logan")
                .cor("Prata")
                .placa("RST5678")
                .kmInicial(7000)
                .dataRegistro(LocalDate.now())
                .condutorResponsavel("Antônio")
                .build();

        veiculoId = veiculoRepository.save(veiculo).getId();
    }

    @Test
    void deveAtualizarCondutorResponsavelComSucesso() throws Exception {
        AtualizarCondutorResponsavelRequest request = AtualizarCondutorResponsavelRequest.builder()
                .condutorResponsavel("Maria Clara")
                .build();

        mockMvc.perform(patch("/veiculos/{id}/condutor-responsavel", veiculoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.condutorResponsavel").value("Maria Clara"));
    }
}
