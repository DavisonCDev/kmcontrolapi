// src/main/java/com/oksmart/kmcontrolapi/controller/ClienteController.java
package com.oksmart.kmcontrolapi.controller;

import com.oksmart.kmcontrolapi.model.Cliente;
import com.oksmart.kmcontrolapi.service.create.ClienteCreateService;
import com.oksmart.kmcontrolapi.service.delete.ClienteDeleteService;
import com.oksmart.kmcontrolapi.service.list.ClienteListService;
import com.oksmart.kmcontrolapi.service.update.ClienteUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteCreateService createService;
    private final ClienteListService listService;
    private final ClienteUpdateService updateService;
    private final ClienteDeleteService deleteService;

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente criado = createService.createCliente(cliente);
        return ResponseEntity.ok(criado);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listAll() {
        List<Cliente> clientes = listService.listAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente cliente = listService.findById(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente atualizado = updateService.updateCliente(id, cliente);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        deleteService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
