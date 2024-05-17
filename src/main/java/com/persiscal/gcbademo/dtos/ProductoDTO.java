package com.persiscal.gcbademo.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    @NotEmpty(message = "El nombre es obligatorio y no puede estar vacío")
    private String nombre;
    private String descripcion;
    @NotNull(message = "El precio es obligatorio")
    private Double precio;
    @NotNull(message = "La cantidad es obligatoria")
    private Integer cantidad;
}
