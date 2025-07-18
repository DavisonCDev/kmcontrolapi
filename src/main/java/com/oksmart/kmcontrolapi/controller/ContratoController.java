// src/main/java/com/oksmart/kmcontrolapi/controller/ContratoController.java
package com.oksmart.kmcontrolapi.controller;

import com.oksmart.kmcontrolapi.model.Contrato;
import com.oksmart.kmcontrolapi.service.create.ContratoCreateService;
import com.oksmart.kmcontrolapi.service.delete.ContratoDeleteService;
import com.oksmart.kmcontrolapi.service.list.ContratoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
@RequiredArgsConstructor
public class ContratoController {

    private final ContratoCreateService contratoCreateService;
    private final ContratoListService contratoListService;
    private final ContratoDeleteService contratoDeleteService;

    @PostMapping
    public ResponseEntity<Contrato> criarContrato(@RequestBody Contrato contrato) {
        Contrato novo = contratoCreateService.salvarContrato(contrato);
        return ResponseEntity.ok(novo);
    }

    @GetMapping
    public ResponseEntity<List<Contrato>> listarContratos() {
        return ResponseEntity.ok(contratoListService.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContrato(@PathVariable Long id) {
        contratoDeleteService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
