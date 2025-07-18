// src/main/java/com/oksmart/kmcontrolapi/service/list/ClienteListService.java
package com.oksmart.kmcontrolapi.service.list;

import com.oksmart.kmcontrolapi.exception.ResourceNotFoundException;
import com.oksmart.kmcontrolapi.model.Cliente;
import com.oksmart.kmcontrolapi.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteListService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> listAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id: " + id));
    }
}
