package com.bookpoint.proveedores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookpoint.proveedores.model.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    
}
