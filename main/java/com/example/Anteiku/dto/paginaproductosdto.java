package com.example.Anteiku.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Respuesta paginada del listado de productos")
public class paginaproductosdto {

    @Schema(description = "Productos de la página actual")
    private List<productodto> contenido;

    @Schema(description = "Número de página (empieza en 0)", example = "0")
    private int pagina;

    @Schema(description = "Cantidad de productos por página", example = "5")
    private int tamano;

    @Schema(description = "Total de productos en la base de datos", example = "12")
    private long totalElementos;

    @Schema(description = "Total de páginas disponibles", example = "3")
    private int totalPaginas;

    @Schema(description = "Indica si existe una página siguiente")
    private boolean tieneSiguiente;

    @Schema(description = "Indica si existe una página anterior")
    private boolean tieneAnterior;

    public paginaproductosdto() {
    }

    public paginaproductosdto(
            List<productodto> contenido,
            int pagina,
            int tamano,
            long totalElementos,
            int totalPaginas,
            boolean tieneSiguiente,
            boolean tieneAnterior) {
        this.contenido = contenido;
        this.pagina = pagina;
        this.tamano = tamano;
        this.totalElementos = totalElementos;
        this.totalPaginas = totalPaginas;
        this.tieneSiguiente = tieneSiguiente;
        this.tieneAnterior = tieneAnterior;
    }

    public List<productodto> getContenido() {
        return contenido;
    }

    public void setContenido(List<productodto> contenido) {
        this.contenido = contenido;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public boolean isTieneSiguiente() {
        return tieneSiguiente;
    }

    public void setTieneSiguiente(boolean tieneSiguiente) {
        this.tieneSiguiente = tieneSiguiente;
    }

    public boolean isTieneAnterior() {
        return tieneAnterior;
    }

    public void setTieneAnterior(boolean tieneAnterior) {
        this.tieneAnterior = tieneAnterior;
    }
}
