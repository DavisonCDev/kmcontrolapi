// src/test/java/com/oksmart/kmcontrolapi/controller/FuncionarioControllerIntegrationTest.java
package com.oksmart.kmcontrolapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oksmart.kmcontrolapi.model.Funcionario;
import com.oksmart.kmcontrolapi.repository.FuncionarioRepository;
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
class FuncionarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        funcionarioRepository.deleteAll();
    }

    @Test
    void testCreateAndGetFuncionario() throws Exception {
        Funcionario funcionario = Funcionario.builder()
                .nome("Carlos")
                .sobrenome("Santos")
                .dataNascimento(LocalDate.of(1990, 1, 20))
                .rg("1234567")
                .cpf("12345678900")
                .habilitacao("H123456")
                .matricula("MAT001")
                .build();

        // Create
        mockMvc.perform(post("/api/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Carlos")))
                .andExpect(jsonPath("$.id", notNullValue()));

        // List all and check created funcionario exists
        mockMvc.perform(get("/api/funcionarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome", is("Carlos")));
    }

    @Test
    void testUpdateFuncionario() throws Exception {
        Funcionario funcionario = Funcionario.builder()
                .nome("Ana")
                .sobrenome("Costa")
                .dataNascimento(LocalDate.of(1985, 5, 15))
                .rg("7654321")
                .cpf("09876543211")
                .habilitacao("H654321")
                .matricula("MAT002")
                .build();

        Funcionario saved = funcionarioRepository.save(funcionario);

        saved.setNome("Ana Maria");

        mockMvc.perform(put("/api/funcionarios/{id}", saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saved)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Ana Maria")));
    }

    @Test
    void testDeleteFuncionario() throws Exception {
        Funcionario funcionario = Funcionario.builder()
                .nome("Pedro")
                .sobrenome("Oliveira")
                .dataNascimento(LocalDate.of(1975, 3, 10))
                .rg("1122334")
                .cpf("22334455667")
                .habilitacao("H998877")
                .matricula("MAT003")
                .build();

        Funcionario saved = funcionarioRepository.save(funcionario);

        mockMvc.perform(delete("/api/funcionarios/{id}", saved.getId()))
                .andExpect(status().isNoContent());

        // Now, try to get the deleted funcionario - should return 404 with our custom message
        mockMvc.perform(get("/api/funcionarios/{id}", saved.getId()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", containsString("Funcionário não encontrado")));
    }

    @Test
    void testGetFuncionario_NotFound() throws Exception {
        mockMvc.perform(get("/api/funcionarios/{id}", 999L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", containsString("Funcionário não encontrado")));
    }
}
