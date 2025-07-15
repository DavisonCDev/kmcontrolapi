// Caminho: src/main/java/com/oksmart/kmcontrolapi/controller/VeiculoController.java

package com.oksmart.kmcontrolapi.controller;

import com.oksmart.kmcontrolapi.dto.*;
import com.oksmart.kmcontrolapi.service.create.VeiculoCreateService;
import com.oksmart.kmcontrolapi.service.delete.VeiculoDeleteService;
import com.oksmart.kmcontrolapi.service.list.VeiculoListService;
import com.oksmart.kmcontrolapi.service.update.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
@RequiredArgsConstructor
@Tag(name = "Veículos", description = "Endpoints para gerenciamento de veículos")
public class VeiculoController {

    @Autowired
    private final VeiculoCreateService veiculoCreateService;
    @Autowired
    private final VeiculoListService veiculoListService;
    @Autowired
    private final VeiculoDeleteService veiculoDeleteService;
    @Autowired
    private final VeiculoUpdateService veiculoUpdateService;
    @Autowired
    private VeiculoAtualizarValorAluguelService veiculoAtualizarValorAluguelService;
    @Autowired
    private VeiculoAtualizarCondutorPrincipalService veiculoAtualizarCondutorPrincipalService;
    @Autowired
    private VeiculoAtualizarCondutorResponsavelService veiculoAtualizarCondutorResponsavelService;
    @Autowired
    private VeiculoAtualizarOsClienteService veiculoAtualizarOsClienteService;


    @PostMapping
    @Operation(summary = "Cadastrar um novo veículo")
    public ResponseEntity<VeiculoResponse> criarVeiculo(@RequestBody VeiculoCreateRequest request) {
        VeiculoResponse response = veiculoCreateService.criar(request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todos os veículos cadastrados")
    public ResponseEntity<List<VeiculoResponse>> listarVeiculos() {
        return ResponseEntity.ok(veiculoListService.listarTodos());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um veículo por ID")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Long id) {
        veiculoDeleteService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um veículo existente por ID")
    public ResponseEntity<VeiculoResponse> atualizarVeiculo(@PathVariable Long id, @RequestBody VeiculoUpdateRequest request) {
        VeiculoResponse response = veiculoUpdateService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/valor-aluguel")
    public ResponseEntity<VeiculoResponse> atualizarValorAluguel(
            @PathVariable Long id,
            @RequestBody AtualizarValorAluguelRequest request) {

        VeiculoResponse atualizado = veiculoAtualizarValorAluguelService.atualizarValor(id, request);
        return ResponseEntity.ok(atualizado);
    }

    @PatchMapping("/{id}/condutor-principal")
    public ResponseEntity<VeiculoResponse> atualizarCondutorPrincipal(
            @PathVariable Long id,
            @RequestBody AtualizarCondutorPrincipalRequest request) {
        return ResponseEntity.ok(veiculoAtualizarCondutorPrincipalService.atualizar(id, request));
    }

    @PatchMapping("/{id}/condutor-responsavel")
    public ResponseEntity<VeiculoResponse> atualizarCondutorResponsavel(
            @PathVariable Long id,
            @RequestBody AtualizarCondutorResponsavelRequest request) {
        return ResponseEntity.ok(veiculoAtualizarCondutorResponsavelService.atualizar(id, request));
    }

    @PatchMapping("/{id}/os-cliente")
    public ResponseEntity<VeiculoResponse> atualizarOsCliente(
            @PathVariable Long id,
            @RequestBody AtualizarOsClienteRequest request) {
        return ResponseEntity.ok(veiculoAtualizarOsClienteService.atualizar(id, request));
    }
}
