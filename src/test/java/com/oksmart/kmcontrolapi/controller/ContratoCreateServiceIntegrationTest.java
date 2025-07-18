// src/test/java/com/oksmart/kmcontrolapi/integration/ContratoCreateServiceIntegrationTest.java
package com.oksmart.kmcontrolapi.controller;

import com.oksmart.kmcontrolapi.model.*;
import com.oksmart.kmcontrolapi.repository.*;
import com.oksmart.kmcontrolapi.service.create.ContratoCreateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContratoCreateServiceIntegrationTest {

    @Autowired
    private ContratoCreateService contratoCreateService;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    private Funcionario funcionario;
    private Cliente cliente;
    private Veiculo veiculo;

    @BeforeEach
    void setUp() {
        contratoRepository.deleteAll();
        funcionarioRepository.deleteAll();
        clienteRepository.deleteAll();
        veiculoRepository.deleteAll();

        funcionario = new Funcionario();
        funcionario.setNome("João");
        funcionario.setSobrenome("Silva");
        funcionario.setDataNascimento(LocalDate.of(1990, 1, 1));
        funcionario.setRg("1234567");
        funcionario.setCpf("11122233344");
        funcionario.setHabilitacao("ABC1234");
        funcionario.setMatricula("MAT001");
        funcionario.setDataRegistro(LocalDateTime.now());
        funcionarioRepository.save(funcionario);

        cliente = new Cliente();
        cliente.setNome("Empresa XYZ");
        cliente.setOs("OS123");
        cliente.setEndereco(Endereco.builder().build());
        clienteRepository.save(cliente);

        veiculo = new Veiculo();
        veiculo.setMarca("Fiat");
        veiculo.setModelo("Uno");
        veiculo.setPlaca("ABC-1234");
        veiculo.setCor("Branco");
        veiculo.setLocadora("Localiza");
        veiculo.setKm(1000);
        veiculoRepository.save(veiculo);
    }

    @Test
    void deveCriarContratoComSucesso() {
        Contrato contrato = new Contrato();
        contrato.setFuncionarioTitular(funcionario);
        contrato.setOsCliente(cliente.toString());
        contrato.setVeiculo(veiculo);
        contrato.setNumeroContrato("CT2025");
        contrato.setDataContrato(LocalDate.now());
        contrato.setDiarias(10);
        contrato.setFranquiaKm(500);
        contrato.setValorAluguel(new BigDecimal("150.00"));

        Contrato contratoSalvo = contratoCreateService.salvarContrato(contrato);

        assertNotNull(contratoSalvo.getId());
        assertEquals("CT2025", contratoSalvo.getNumeroContrato());
        assertEquals("João", contratoSalvo.getFuncionarioTitular().getNome());
        assertEquals("Empresa XYZ", contratoSalvo.getOsCliente());
        assertEquals("Uno", contratoSalvo.getVeiculo().getModelo());
        assertNotNull(contratoSalvo.getDataAtual());
    }
}
