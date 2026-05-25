package com.bookpoint.proveedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookpoint.proveedores.model.Proveedor;
import com.bookpoint.proveedores.service.ProveedorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/proveedores")

public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping
    public ResponseEntity<?> postProveedor(@Valid @RequestBody Proveedor proveedor) {

        try {
            Proveedor nuevoProveedor = proveedorService.crear(proveedor);

            return ResponseEntity.ok(nuevoProveedor);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body("Error al crear proveedor: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getProveedores() {

        List<Proveedor> lista = proveedorService.listar();

        if (lista.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .body("No hay proveedores registrados");
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProveedorById(@PathVariable Long id) {

        try {

            Proveedor proveedor = proveedorService.buscarPorId(id);

            if (proveedor == null) {
                return ResponseEntity
                        .status(404)
                        .body("Proveedor no encontrado");
            }

            return ResponseEntity.ok(proveedor);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body("Error al buscar proveedor: " + e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> putProveedor(
            @PathVariable Long id,
            @Valid @RequestBody Proveedor proveedor) {

        try {

            Proveedor proveedorActualizado = proveedorService.modificar(id, proveedor);

            return ResponseEntity.ok(proveedorActualizado);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body("Error al actualizar proveedor: " + e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProveedor(@PathVariable Long id) {

        try {

            proveedorService.eliminar(id);

            return ResponseEntity.ok("Proveedor eliminado correctamente");

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body("Error al eliminar proveedor: " + e.getMessage());
        }
    }
}