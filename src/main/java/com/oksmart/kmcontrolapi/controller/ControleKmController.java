// src/main/java/com/oksmart/kmcontrolapi/controller/ControleKmController.java
package com.oksmart.kmcontrolapi.controller;

import com.oksmart.kmcontrolapi.model.ControleKm;
import com.oksmart.kmcontrolapi.service.create.ControleKmCreateService;
import com.oksmart.kmcontrolapi.service.delete.ControleKmDeleteService;
import com.oksmart.kmcontrolapi.service.list.ControleKmListService;
import com.oksmart.kmcontrolapi.service.update.ControleKmUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/controlekm")
@RequiredArgsConstructor
public class ControleKmController {

    private final ControleKmCreateService createService;
    private final ControleKmListService listService;
    private final ControleKmDeleteService deleteService;
    private final ControleKmUpdateService updateService;

    @PostMapping
    public ResponseEntity<ControleKm> criar(@RequestBody ControleKm controleKm) {
        ControleKm salvo = createService.salvar(controleKm);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<ControleKm>> listar() {
        return ResponseEntity.ok(listService.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deleteService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    // PATCH para atualizar só o kmAtual
    @PatchMapping("/{id}/km")
    public ResponseEntity<ControleKm> atualizarKm(@PathVariable Long id, @RequestBody Map<String, Integer> kmAtualPayload) {
        if (!kmAtualPayload.containsKey("kmAtual")) {
            return ResponseEntity.badRequest().build();
        }
        Integer novoKm = kmAtualPayload.get("kmAtual");
        ControleKm atualizado = updateService.atualizarKmAtual(id, novoKm);
        return ResponseEntity.ok(atualizado);
    }
}
