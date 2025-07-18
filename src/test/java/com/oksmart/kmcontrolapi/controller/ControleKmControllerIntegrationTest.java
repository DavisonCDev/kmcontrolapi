// src/test/java/com/oksmart/kmcontrolapi/integration/ControleKmControllerIntegrationTest.java
package com.oksmart.kmcontrolapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oksmart.kmcontrolapi.model.ControleKm;
import com.oksmart.kmcontrolapi.model.Funcionario;
import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.repository.ControleKmRepository;
import com.oksmart.kmcontrolapi.repository.FuncionarioRepository;
import com.oksmart.kmcontrolapi.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControleKmControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ControleKmRepository controleKmRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private ControleKm controleKm;

    @BeforeEach
    void setup() {
        controleKmRepository.deleteAll();
        funcionarioRepository.deleteAll();
        veiculoRepository.deleteAll();

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Test");
        funcionario.setSobrenome("User");
        funcionario.setDataNascimento(java.time.LocalDate.of(1990,1,1));
        funcionario.setRg("12345");
        funcionario.setCpf("12345678900");
        funcionario.setHabilitacao("H12345");
        funcionario.setMatricula("M123");
        funcionario.setDataRegistro(java.time.LocalDateTime.now());
        funcionarioRepository.save(funcionario);

        Veiculo veiculo = new Veiculo();
        veiculo.setMarca("MarcaTest");
        veiculo.setModelo("ModeloTest");
        veiculo.setPlaca("AAA-1234");
        veiculo.setCor("Preto");
        veiculo.setLocadora("LocadoraTest");
        veiculo.setKm(10000);
        veiculoRepository.save(veiculo);

        controleKm = new ControleKm();
        controleKm.setCondutor(funcionario);
        controleKm.setVeiculo(veiculo);
        controleKm.setKmAtual(15000);
        controleKmRepository.save(controleKm);
    }

    @Test
    void deveAtualizarKmAtualViaPatch() throws Exception {
        String url = "/api/controlekm/" + controleKm.getId() + "/km";

        Map<String, Integer> kmUpdate = new HashMap<>();
        kmUpdate.put("kmAtual", 16000);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(kmUpdate), headers);

        ResponseEntity<ControleKm> response = restTemplate.exchange(url, HttpMethod.PATCH, request, ControleKm.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(16000, response.getBody().getKmAtual());
        assertTrue(response.getBody().getDataAtual().isAfter(controleKm.getDataAtual()));
    }
}
