package com.example.Anteiku.controller;

import com.example.Anteiku.dto.mensajerespuestadto;
import com.example.Anteiku.dto.paginaproductosdto;
import com.example.Anteiku.dto.productodto;
import com.example.Anteiku.service.cafeteriaservicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Cafetería Anteiku", description = "API para gestionar el menú de la cafetería")
public class cafeteriacontroller {

    private final cafeteriaservicio cafeteriaServicio;

    public cafeteriacontroller(cafeteriaservicio cafeteriaServicio) {
        this.cafeteriaServicio = cafeteriaServicio;
    }

    @GetMapping("/presentacion")
    @Operation(summary = "Mensaje de bienvenida")
    public String bienvenida() {
        return "Bienvenido a la Cafetería Anteiku ☕ — Hoy tenemos promociones especiales";
    }

    @GetMapping("/despedida")
    @Operation(summary = "Mensaje de despedida")
    public String despedida() {
        return "Gracias por visitar la Cafetería Anteiku ☕ — ¡Vuelve pronto!";
    }

    @GetMapping("/menu")
    @Operation(summary = "Consultar producto por nombre")
    public ResponseEntity<String> consultarProducto(
            @Parameter(description = "Nombre del producto") @RequestParam String nombre) {
        return ResponseEntity.ok(cafeteriaServicio.buscarProducto(nombre));
    }

    @GetMapping("/productos")
    @Operation(summary = "Listar productos con paginación")
    public ResponseEntity<paginaproductosdto> listarProductos(
            @Parameter(description = "Número de página (empieza en 0)", example = "0")
            @RequestParam(defaultValue = "0") int pagina,
            @Parameter(description = "Cantidad de productos por página", example = "3")
            @RequestParam(defaultValue = "3") int tamano) {
        return ResponseEntity.ok(cafeteriaServicio.listarProductosPaginados(pagina, tamano));
    }

    @GetMapping("/productos/{id}")
    @Operation(summary = "Buscar producto por ID")
    public ResponseEntity<String> buscarPorId(
            @Parameter(description = "ID del producto") @PathVariable Long id) {
        return ResponseEntity.ok(cafeteriaServicio.buscarProducto(String.valueOf(id)));
    }

    @GetMapping("/productos/buscar")
    @Operation(summary = "Buscar producto por criterio (ID o nombre)")
    public ResponseEntity<String> buscarProducto(
            @Parameter(description = "ID o parte del nombre") @RequestParam String criterio) {
        return ResponseEntity.ok(cafeteriaServicio.buscarProducto(criterio));
    }

    @PostMapping("/productos")
    @Operation(summary = "Registrar un nuevo producto")
    public ResponseEntity<mensajerespuestadto> crear(@RequestBody productodto dto) {
        mensajerespuestadto respuesta = cafeteriaServicio.registrarProducto(
                dto.getNombre(),
                dto.getPrecio(),
                dto.getStock(),
                dto.getCategoria(),
                dto.getActivo()
        );
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/productos/{id}")
    @Operation(summary = "Actualizar un producto existente")
    public ResponseEntity<mensajerespuestadto> actualizar(
            @Parameter(description = "ID del producto a actualizar") @PathVariable Long id,
            @RequestBody productodto dto) {
        mensajerespuestadto respuesta = cafeteriaServicio.actualizarProducto(
                id,
                dto.getNombre(),
                dto.getPrecio(),
                dto.getStock(),
                dto.getCategoria(),
                dto.getActivo()
        );
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/productos/{id}")
    @Operation(summary = "Eliminar un producto por ID")
    public ResponseEntity<mensajerespuestadto> eliminar(
            @Parameter(description = "ID del producto a eliminar") @PathVariable Long id) {
        mensajerespuestadto respuesta = cafeteriaServicio.eliminarProducto(id);
        return ResponseEntity.ok(respuesta);
    }
}
