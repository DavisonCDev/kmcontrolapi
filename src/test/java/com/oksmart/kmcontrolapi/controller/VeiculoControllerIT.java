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

import java.math.BigDecimal;
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

    // ✅ POST: Criar veículo
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

    // ✅ GET: Listar veículos
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

    // ✅ DELETE: Remover veículo
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

        mockMvc.perform(get("/veiculos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // ✅ PATCH: Atualizar condutorPrincipal
    @Test
    void deveAtualizarCondutorPrincipalComPATCH() throws Exception {
        Veiculo veiculo = veiculoRepository.save(Veiculo.builder()
                .marca("VW")
                .modelo("Gol")
                .condutorPrincipal("Carlos")
                .build());

        mockMvc.perform(patch("/veiculos/{id}/condutor-principal", veiculo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"condutorPrincipal\": \"Ana Beatriz\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.condutorPrincipal").value("Ana Beatriz"));
    }

    // ✅ PATCH: Atualizar condutorResponsavel
    @Test
    void deveAtualizarCondutorResponsavelComPATCH() throws Exception {
        Veiculo veiculo = veiculoRepository.save(Veiculo.builder()
                .marca("Renault")
                .modelo("Kwid")
                .condutorResponsavel("Bruno")
                .build());

        mockMvc.perform(patch("/veiculos/{id}/condutor-responsavel", veiculo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"condutorResponsavel\": \"Carlos Eduardo\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.condutorResponsavel").value("Carlos Eduardo"));
    }

    // ✅ PATCH: Atualizar osCliente
    @Test
    void deveAtualizarOsClienteComPATCH() throws Exception {
        Veiculo veiculo = veiculoRepository.save(Veiculo.builder()
                .marca("Chevrolet")
                .modelo("Tracker")
                .osCliente("OS001")
                .build());

        mockMvc.perform(patch("/veiculos/{id}/os-cliente", veiculo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"osCliente\": \"OS78910\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.osCliente").value("OS78910"));
    }
}
