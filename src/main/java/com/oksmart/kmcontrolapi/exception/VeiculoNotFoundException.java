// Caminho: src/main/java/com/oksmart/kmcontrolapi/exception/VeiculoNotFoundException.java

package com.oksmart.kmcontrolapi.exception;

public class VeiculoNotFoundException extends RuntimeException {
    public VeiculoNotFoundException(Long id) {
        super("Veículo não encontrado com ID: " + id);
    }
}
