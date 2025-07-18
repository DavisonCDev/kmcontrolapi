// Caminho: src/test/java/com/oksmart/kmcontrolapi/controller/integration/VeiculoAtualizarOsClienteIntegrationTest.java

package com.oksmart.kmcontrolapi.controller.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oksmart.kmcontrolapi.dto.AtualizarOsClienteRequest;
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
class VeiculoAtualizarOsClienteIntegrationTest {

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
                .marca("Fiat")
                .modelo("Toro")
                .cor("Branco")
                .placa("XYZ9090")
                .kmInicial(4000)
                .dataRegistro(LocalDate.now())
                .osCliente("OS0001")
                .build();

        veiculoId = veiculoRepository.save(veiculo).getId();
    }

    @Test
    void deveAtualizarOsClienteComSucesso() throws Exception {
        AtualizarOsClienteRequest request = AtualizarOsClienteRequest.builder()
                .osCliente("OS9999")
                .build();

        mockMvc.perform(patch("/veiculos/{id}/os-cliente", veiculoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.osCliente").value("OS9999"));
    }
}
