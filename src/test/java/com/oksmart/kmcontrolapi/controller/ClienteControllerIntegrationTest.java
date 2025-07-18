// src/test/java/com/oksmart/kmcontrolapi/controller/ClienteControllerIntegrationTest.java
package com.oksmart.kmcontrolapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oksmart.kmcontrolapi.model.Cliente;
import com.oksmart.kmcontrolapi.model.Endereco;
import com.oksmart.kmcontrolapi.repository.ClienteRepository;
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

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        clienteRepository.deleteAll();
    }

    @Test
    void testCreateAndGetCliente() throws Exception {
        Cliente cliente = Cliente.builder()
                .nome("João Silva")
                .os("OS12345")
                .endereco(Endereco.builder()
                        .cep("12345-678")
                        .rua("Rua A")
                        .numero("100")
                        .bairro("Bairro B")
                        .cidade("Cidade C")
                        .estado("Estado D")
                        .pais("Brasil")
                        .build())
                .build();

        // Create
        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("João Silva")))
                .andExpect(jsonPath("$.id", notNullValue()));

        // List all
        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].os", is("OS12345")));
    }

    @Test
    void testUpdateCliente() throws Exception {
        Cliente cliente = Cliente.builder()
                .nome("Maria Souza")
                .os("OS54321")
                .endereco(Endereco.builder()
                        .cep("98765-432")
                        .rua("Rua X")
                        .numero("200")
                        .bairro("Bairro Y")
                        .cidade("Cidade Z")
                        .estado("Estado W")
                        .pais("Brasil")
                        .build())
                .build();

        Cliente saved = clienteRepository.save(cliente);
        saved.setNome("Maria S. Oliveira");
        saved.setOs("OS54321-UPDATED");
        saved.setEndereco(Endereco.builder()
                .cep("98765-432")
                .rua("Rua X")
                .numero("201")
                .bairro("Bairro Y")
                .cidade("Cidade Z")
                .estado("Estado W")
                .pais("Brasil")
                .build());

        mockMvc.perform(put("/api/clientes/{id}", saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saved)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Maria S. Oliveira")))
                .andExpect(jsonPath("$.os", is("OS54321-UPDATED")))
                .andExpect(jsonPath("$.endereco.numero", is("201")));
    }

    @Test
    void testDeleteCliente() throws Exception {
        Cliente cliente = Cliente.builder()
                .nome("Carlos Pereira")
                .os("OS99999")
                .endereco(Endereco.builder()
                        .cep("11111-111")
                        .rua("Rua Y")
                        .numero("300")
                        .bairro("Bairro Z")
                        .cidade("Cidade W")
                        .estado("Estado V")
                        .pais("Brasil")
                        .build())
                .build();

        Cliente saved = clienteRepository.save(cliente);

        // Delete
        mockMvc.perform(delete("/api/clientes/{id}", saved.getId()))
                .andExpect(status().isNoContent());

        // Try to get deleted -> 404
        mockMvc.perform(get("/api/clientes/{id}", saved.getId()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", containsString("Cliente não encontrado")));
    }

    @Test
    void testGetCliente_NotFound() throws Exception {
        mockMvc.perform(get("/api/clientes/{id}", 999L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", containsString("Cliente não encontrado")));
    }
}
