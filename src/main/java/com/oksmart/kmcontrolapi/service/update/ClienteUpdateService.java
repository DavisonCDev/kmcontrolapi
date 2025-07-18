// src/main/java/com/oksmart/kmcontrolapi/service/update/ClienteUpdateService.java
package com.oksmart.kmcontrolapi.service.update;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.model.Cliente;
import com.oksmart.kmcontrolapi.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteUpdateService {

    private final ClienteRepository clienteRepository;

    public Cliente updateCliente(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id: " + id));

        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setOs(clienteAtualizado.getOs());
        clienteExistente.setEndereco(clienteAtualizado.getEndereco());

        return clienteRepository.save(clienteExistente);
    }
}
