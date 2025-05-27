package com.cursos.cl.cursos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity // Marca esta clase como entidad JPA
@Data // Lombok: genera getters, setters, toString, etc.
@AllArgsConstructor
@NoArgsConstructor

public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    private Long id;

    @NotEmpty (message = "Body incompleto o inválido")
    private String nombre;
    @NotEmpty (message = "Body incompleto o inválido")
    private String descripcion;
    @NotNull (message = "Body incompleto o inválido")
    private Integer duracion; //en horas
}
