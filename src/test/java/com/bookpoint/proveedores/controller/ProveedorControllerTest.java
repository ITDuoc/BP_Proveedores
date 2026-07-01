package com.bookpoint.proveedores.controller;

import com.bookpoint.proveedores.model.Proveedor;
import com.bookpoint.proveedores.service.ProveedorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProveedorController.class)
class ProveedorControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private ProveedorService proveedorService;

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Test
        void testCrear_OK() throws Exception {
                Proveedor p = new Proveedor(null, "ACME", "123", "a@mail.com", "Santiago");
                Proveedor r = new Proveedor(1L, "ACME", "123", "a@mail.com", "Santiago");

                Mockito.when(proveedorService.crear(any())).thenReturn(r);

                mockMvc.perform(post("/api/v1/proveedores")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(p)))
                                .andExpect(status().isOk());
        }

        @Test
        void testCrear_Exception() throws Exception {
                Proveedor p = new Proveedor(null, "ACME", "123", "a@mail.com", "Santiago");

                Mockito.when(proveedorService.crear(any()))
                                .thenThrow(new RuntimeException("error"));

                mockMvc.perform(post("/api/v1/proveedores")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(p)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void testListar_OK() throws Exception {
                Mockito.when(proveedorService.listar())
                                .thenReturn(Arrays.asList(
                                                new Proveedor(1L, "A", "111", "a@mail.com", "Dir1")));

                mockMvc.perform(get("/api/v1/proveedores"))
                                .andExpect(status().isOk());
        }

        @Test
        void testListar_Vacio() throws Exception {
                Mockito.when(proveedorService.listar())
                                .thenReturn(Collections.emptyList());

                mockMvc.perform(get("/api/v1/proveedores"))
                                .andExpect(status().isNotFound());
        }

        @Test
        void testBuscar_OK() throws Exception {
                Mockito.when(proveedorService.buscarPorId(1L))
                                .thenReturn(new Proveedor(1L, "ACME", "123", "a@mail.com", "Santiago"));

                mockMvc.perform(get("/api/v1/proveedores/1"))
                                .andExpect(status().isOk());
        }

        @Test
        void testBuscar_NoExiste() throws Exception {
                Mockito.when(proveedorService.buscarPorId(1L))
                                .thenReturn(null);

                mockMvc.perform(get("/api/v1/proveedores/1"))
                                .andExpect(status().isNotFound());
        }

        @Test
        void testBuscar_Exception() throws Exception {
                Mockito.when(proveedorService.buscarPorId(1L))
                                .thenThrow(new RuntimeException());

                mockMvc.perform(get("/api/v1/proveedores/1"))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void testActualizar_OK() throws Exception {
                Proveedor p = new Proveedor(null, "ACME", "123", "a@mail.com", "Santiago");
                Proveedor r = new Proveedor(1L, "ACME", "123", "a@mail.com", "Santiago");

                Mockito.when(proveedorService.modificar(eq(1L), any())).thenReturn(r);

                mockMvc.perform(put("/api/v1/proveedores/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(p)))
                                .andExpect(status().isOk());
        }

        @Test
        void testActualizar_Exception() throws Exception {
                Proveedor p = new Proveedor(null, "ACME", "123", "a@mail.com", "Santiago");

                Mockito.when(proveedorService.modificar(eq(1L), any()))
                                .thenThrow(new RuntimeException());

                mockMvc.perform(put("/api/v1/proveedores/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(p)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void testEliminar_OK() throws Exception {
                Mockito.doNothing().when(proveedorService).eliminar(1L);

                mockMvc.perform(delete("/api/v1/proveedores/1"))
                                .andExpect(status().isOk());
        }

        @Test
        void testEliminar_Exception() throws Exception {
                Mockito.doThrow(new RuntimeException())
                                .when(proveedorService).eliminar(1L);

                mockMvc.perform(delete("/api/v1/proveedores/1"))
                                .andExpect(status().isBadRequest());
        }
}