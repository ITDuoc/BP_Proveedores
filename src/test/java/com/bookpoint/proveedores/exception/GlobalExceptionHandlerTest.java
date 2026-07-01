package com.bookpoint.proveedores.exception;

import com.bookpoint.proveedores.model.Proveedor;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void testManejoErroresValidacion() {

        Proveedor proveedor = new Proveedor();

        BeanPropertyBindingResult bindingResult =
                new BeanPropertyBindingResult(proveedor, "proveedor");

        bindingResult.addError(
                new FieldError("proveedor", "nombre", "El nombre es obligatorio")
        );

        bindingResult.addError(
                new FieldError("proveedor", "correo", "El correo es obligatorio")
        );

        MethodArgumentNotValidException ex =
                new MethodArgumentNotValidException(null, bindingResult);

        Map<String, String> response = handler.manejoErroresValidacion(ex);

        assertEquals(2, response.size());
        assertEquals("El nombre es obligatorio", response.get("nombre"));
        assertEquals("El correo es obligatorio", response.get("correo"));
    }
}