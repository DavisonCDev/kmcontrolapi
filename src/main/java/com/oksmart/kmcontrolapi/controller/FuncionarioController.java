// src/main/java/com/oksmart/kmcontrolapi/controller/FuncionarioController.java
package com.oksmart.kmcontrolapi.controller;

import com.oksmart.kmcontrolapi.model.Funcionario;
import com.oksmart.kmcontrolapi.service.create.FuncionarioCreateService;
import com.oksmart.kmcontrolapi.service.delete.FuncionarioDeleteService;
import com.oksmart.kmcontrolapi.service.list.FuncionarioListService;
import com.oksmart.kmcontrolapi.service.update.FuncionarioUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioCreateService createService;
    private final FuncionarioListService listService;
    private final FuncionarioUpdateService updateService;
    private final FuncionarioDeleteService deleteService;

    @PostMapping
    public ResponseEntity<Funcionario> createFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario criado = createService.createFuncionario(funcionario);
        return ResponseEntity.ok(criado);
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> listFuncionarios() {
        List<Funcionario> funcionarios = listService.listAll();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable Long id) {
        Funcionario funcionario = listService.findById(id);
        return ResponseEntity.ok(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        Funcionario atualizado = updateService.updateFuncionario(id, funcionario);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        deleteService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
