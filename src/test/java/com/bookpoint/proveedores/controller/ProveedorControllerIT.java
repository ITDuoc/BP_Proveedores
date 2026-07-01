package com.bookpoint.proveedores.controller;

import com.bookpoint.proveedores.model.Proveedor;
import com.bookpoint.proveedores.repository.ProveedorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProveedorControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProveedorRepository repository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void testCrearYListar() throws Exception {

        Proveedor proveedor = new Proveedor(
                null,
                "ACME",
                "123",
                "acme@mail.com",
                "Santiago"
        );

        // CREATE
        mockMvc.perform(post("/api/v1/proveedores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proveedor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("ACME"));

        // LIST
        mockMvc.perform(get("/api/v1/proveedores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("ACME"));
    }

    @Test
    void testEliminar() throws Exception {

        Proveedor proveedor = repository.save(
                new Proveedor(null, "ACME", "123", "a@mail.com", "Santiago")
        );

        mockMvc.perform(delete("/api/v1/proveedores/" + proveedor.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/proveedores"))
                .andExpect(status().isNotFound());
    }
}