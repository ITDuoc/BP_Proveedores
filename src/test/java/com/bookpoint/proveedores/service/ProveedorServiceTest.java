package com.bookpoint.proveedores.service;

import com.bookpoint.proveedores.model.Proveedor;
import com.bookpoint.proveedores.repository.ProveedorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @Test
    void testCrearProveedor() {
        Proveedor proveedor = new Proveedor(null, "ACME", "123", "acme@mail.com", "Santiago");
        Proveedor guardado = new Proveedor(1L, "ACME", "123", "acme@mail.com", "Santiago");

        when(proveedorRepository.save(proveedor)).thenReturn(guardado);

        Proveedor resultado = proveedorService.crear(proveedor);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("ACME", resultado.getNombre());

        verify(proveedorRepository, times(1)).save(proveedor);
    }

    @Test
    void testListar() {
        List<Proveedor> lista = Arrays.asList(
                new Proveedor(1L, "A", "111", "a@mail.com", "Dir1"),
                new Proveedor(2L, "B", "222", "b@mail.com", "Dir2")
        );

        when(proveedorRepository.findAll()).thenReturn(lista);

        List<Proveedor> resultado = proveedorService.listar();

        assertEquals(2, resultado.size());
        verify(proveedorRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId() {
        Proveedor proveedor = new Proveedor(1L, "ACME", "123", "acme@mail.com", "Santiago");

        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(proveedor));

        Proveedor resultado = proveedorService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("ACME", resultado.getNombre());
    }

    @Test
    void testModificarExistente() {
        Proveedor existente = new Proveedor(1L, "ACME", "123", "a@mail.com", "Santiago");
        Proveedor nuevo = new Proveedor(null, "NEW", "999", "new@mail.com", "Valpo");

        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(proveedorRepository.save(any())).thenReturn(existente);

        Proveedor resultado = proveedorService.modificar(1L, nuevo);

        assertNotNull(resultado);
        verify(proveedorRepository).save(existente);
    }

    @Test
    void testEliminar() {
        doNothing().when(proveedorRepository).deleteById(1L);

        proveedorService.eliminar(1L);

        verify(proveedorRepository, times(1)).deleteById(1L);
    }
}