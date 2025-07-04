// Caminho: src/test/java/com/oksmart/kmcontrolapi/controller/VeiculoControllerIT.java

package com.oksmart.kmcontrolapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oksmart.kmcontrolapi.dto.VeiculoCreateRequest;
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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VeiculoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        veiculoRepository.deleteAll(); // Limpa antes de cada teste
    }

    @Test
    void deveCriarVeiculoComPOST() throws Exception {
        VeiculoCreateRequest request = VeiculoCreateRequest.builder()
                .marca("Fiat")
                .modelo("Uno")
                .cor("Vermelho")
                .placa("FIU1234")
                .kmInicial(10000)
                .dataRegistro(LocalDate.now())
                .build();

        mockMvc.perform(post("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.placa").value("FIU1234"));
    }

    @Test
    void deveListarVeiculosComGET() throws Exception {
        Veiculo v1 = Veiculo.builder()
                .marca("Ford")
                .modelo("Focus")
                .cor("Preto")
                .placa("FOR1234")
                .kmInicial(8000)
                .dataRegistro(LocalDate.now())
                .build();

        veiculoRepository.save(v1);

        mockMvc.perform(get("/veiculos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].placa").value("FOR1234"));
    }

    @Test
    void deveDeletarVeiculoComDELETE() throws Exception {
        Veiculo v1 = Veiculo.builder()
                .marca("VW")
                .modelo("Gol")
                .cor("Branco")
                .placa("VWG1234")
                .kmInicial(9000)
                .dataRegistro(LocalDate.now())
                .build();

        Veiculo salvo = veiculoRepository.save(v1);

        mockMvc.perform(delete("/veiculos/{id}", salvo.getId()))
                .andExpect(status().isNoContent());

        // Verifica que foi excluído
        mockMvc.perform(get("/veiculos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void deveAtualizarVeiculoComPUT() throws Exception {
        // Cria e salva um veículo original
        Veiculo original = Veiculo.builder()
                .marca("Ford")
                .modelo("Fiesta")
                .cor("Prata")
                .placa("OLD1234")
                .kmInicial(15000)
                .dataRegistro(LocalDate.of(2023, 1, 10))
                .build();

        Veiculo salvo = veiculoRepository.save(original);

        // Novo conteúdo para atualizar
        VeiculoCreateRequest atualizado = VeiculoCreateRequest.builder()
                .marca("Hyundai")
                .modelo("HB20")
                .cor("Azul")
                .placa("NEW5678")
                .kmInicial(20000)
                .dataRegistro(LocalDate.of(2025, 7, 1))
                .build();

        mockMvc.perform(put("/veiculos/{id}", salvo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(atualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(salvo.getId()))
                .andExpect(jsonPath("$.marca").value("Hyundai"))
                .andExpect(jsonPath("$.modelo").value("HB20"))
                .andExpect(jsonPath("$.placa").value("NEW5678"));
    }

    @Test
    void deveRetornar404AoAtualizarVeiculoInexistente() throws Exception {
        Long idInexistente = 999L;

        VeiculoCreateRequest atualizado = VeiculoCreateRequest.builder()
                .marca("Honda")
                .modelo("Civic")
                .cor("Preto")
                .placa("HND9999")
                .kmInicial(25000)
                .dataRegistro(LocalDate.now())
                .build();

        mockMvc.perform(put("/veiculos/{id}", idInexistente)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(atualizado)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Veículo não encontrado com ID: " + idInexistente));
    }
}
