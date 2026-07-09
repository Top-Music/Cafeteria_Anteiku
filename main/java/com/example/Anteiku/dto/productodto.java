package com.example.Anteiku.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos de un producto de la cafetería")
public class productodto {

    @Schema(description = "Identificador único (solo lectura)", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre del producto", example = "Capuccino")
    private String nombre;

    @Schema(description = "Precio en soles", example = "12.5")
    private Double precio;

    @Schema(description = "Cantidad en inventario", example = "15")
    private Integer stock;

    @Schema(description = "Categoría del producto", example = "Bebida")
    private String categoria;

    @Schema(description = "Si el producto está activo en el menú", example = "true")
    private Boolean activo;

    public productodto() {
    }

    public productodto(Long id, String nombre, Double precio, Integer stock, String categoria, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "productodto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", categoria='" + categoria + '\'' +
                ", activo=" + activo +
                '}';
    }
}
