package com.bookpoint.proveedores.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="proveedores")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min=2,max=50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Column(nullable=false)
    private String nombre;
    
    @NotBlank(message = "El numero de telefono es obligatorio")
    @Column(nullable=false)
    private String telefono;
    
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    @Column(nullable=false)
    private String correo;

    @NotBlank(message = "La direccion es obligatoria")
    @Column(nullable=false)
    private String direccion;


}
