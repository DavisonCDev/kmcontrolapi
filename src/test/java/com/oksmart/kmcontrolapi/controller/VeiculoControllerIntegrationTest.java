// src/test/java/com/oksmart/kmcontrolapi/controller/VeiculoControllerIntegrationTest.java
package com.oksmart.kmcontrolapi.controller;

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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class VeiculoControllerIntegrationTest {

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
    void testCreateAndGetVeiculo() throws Exception {
        Veiculo veiculo = Veiculo.builder()
                .marca("Ford")
                .modelo("Fiesta")
                .placa("XYZ1234")
                .cor("Azul")
                .locadora("Locadora A")
                .km(5000)
                .build();

        // Create
        mockMvc.perform(post("/api/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(veiculo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca", is("Ford")))
                .andExpect(jsonPath("$.id", notNullValue()));

        // List all
        mockMvc.perform(get("/api/veiculos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].placa", is("XYZ1234")));
    }

    @Test
    void testUpdateVeiculo() throws Exception {
        Veiculo veiculo = Veiculo.builder()
                .marca("Chevrolet")
                .modelo("Onix")
                .placa("ABC9876")
                .cor("Preto")
                .locadora("Locadora B")
                .km(10000)
                .build();

        Veiculo saved = veiculoRepository.save(veiculo);
        saved.setModelo("Onix Plus");
        saved.setCor("Branco");
        saved.setKm(12000);

        mockMvc.perform(put("/api/veiculos/{id}", saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saved)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelo", is("Onix Plus")))
                .andExpect(jsonPath("$.cor", is("Branco")))
                .andExpect(jsonPath("$.km", is(12000)));
    }

    @Test
    void testDeleteVeiculo() throws Exception {
        Veiculo veiculo = Veiculo.builder()
                .marca("Volkswagen")
                .modelo("Golf")
                .placa("DEF4567")
                .cor("Cinza")
                .locadora("Locadora C")
                .km(8000)
                .build();

        Veiculo saved = veiculoRepository.save(veiculo);

        // Delete
        mockMvc.perform(delete("/api/veiculos/{id}", saved.getId()))
                .andExpect(status().isNoContent());

        // Try get deleted -> expect 404
        mockMvc.perform(get("/api/veiculos/{id}", saved.getId()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", containsString("Veículo não encontrado")));
    }

    @Test
    void testGetVeiculo_NotFound() throws Exception {
        mockMvc.perform(get("/api/veiculos/{id}", 999L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", containsString("Veículo não encontrado")));
    }
}
