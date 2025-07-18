// src/main/java/com/oksmart/kmcontrolapi/controller/VeiculoController.java
package com.oksmart.kmcontrolapi.controller;

import com.oksmart.kmcontrolapi.model.Veiculo;
import com.oksmart.kmcontrolapi.service.create.VeiculoCreateService;
import com.oksmart.kmcontrolapi.service.delete.VeiculoDeleteService;
import com.oksmart.kmcontrolapi.service.list.VeiculoListService;
import com.oksmart.kmcontrolapi.service.update.VeiculoUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoCreateService createService;
    private final VeiculoListService listService;
    private final VeiculoUpdateService updateService;
    private final VeiculoDeleteService deleteService;

    @PostMapping
    public ResponseEntity<Veiculo> createVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo criado = createService.createVeiculo(veiculo);
        return ResponseEntity.ok(criado);
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listAll() {
        List<Veiculo> lista = listService.listAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> findById(@PathVariable Long id) {
        Veiculo veiculo = listService.findById(id);
        return ResponseEntity.ok(veiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> updateVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        Veiculo atualizado = updateService.updateVeiculo(id, veiculo);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id) {
        deleteService.deleteVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}
