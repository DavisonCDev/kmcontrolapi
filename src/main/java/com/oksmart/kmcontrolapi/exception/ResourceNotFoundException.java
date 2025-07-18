// src/main/java/com/oksmart/kmcontrolapi/exception/ResourceNotFoundException.java
package com.oksmart.kmcontrolapi.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
