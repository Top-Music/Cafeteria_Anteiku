package com.example.Anteiku.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta con mensaje de la operación")
public class mensajerespuestadto {

    @Schema(description = "Indica si la operación fue exitosa", example = "true")
    private boolean exito;

    @Schema(description = "Mensaje descriptivo del resultado", example = "Producto eliminado correctamente")
    private String mensaje;

    @Schema(description = "ID del producto afectado (si aplica)", example = "3")
    private Long idProducto;

    public mensajerespuestadto() {
    }

    public mensajerespuestadto(boolean exito, String mensaje) {
        this.exito = exito;
        this.mensaje = mensaje;
    }

    public mensajerespuestadto(boolean exito, String mensaje, Long idProducto) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.idProducto = idProducto;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
}
