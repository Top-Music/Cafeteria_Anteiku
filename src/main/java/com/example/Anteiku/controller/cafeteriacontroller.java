package com.example.Anteiku.controller;

import com.example.Anteiku.dto.productodto;
import com.example.Anteiku.service.cafeteriaservicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class cafeteriacontroller {

    private final cafeteriaservicio cafeteriaServicio;

    public cafeteriacontroller(cafeteriaservicio cafeteriaServicio) {
        this.cafeteriaServicio = cafeteriaServicio;
    }

    // --- Endpoints informativos ---
    @GetMapping("/presentacion")
    public String bienvenida() {
        return "Bienvenido a la Cafetería Anteiku ☕ — Hoy tenemos promociones especiales";
    }

    @GetMapping("/despedida")
    public String despedida() {
        return "Gracias por visitar la Cafetería Anteiku ☕ — ¡Vuelve pronto!";
    }

    @GetMapping("/menu")
    public ResponseEntity<String> consultarProducto(@RequestParam String nombre) {
        return ResponseEntity.ok(cafeteriaServicio.buscarProducto(nombre));
    }

    // --- CRUD de productos ---
    @GetMapping("/productos")
    public ResponseEntity<String> listarProductos() {
        return ResponseEntity.ok(cafeteriaServicio.listarProductos());
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<String> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cafeteriaServicio.buscarProducto(String.valueOf(id)));
    }

    @GetMapping("/productos/buscar")
    public ResponseEntity<String> buscarProducto(@RequestParam String criterio) {
        return ResponseEntity.ok(cafeteriaServicio.buscarProducto(criterio));
    }

    @PostMapping("/productos")
    public ResponseEntity<String> crear(@RequestBody productodto dto) {
        return ResponseEntity.ok(
            cafeteriaServicio.registrarProducto(
                dto.getNombre(),
                dto.getPrecio(),
                dto.getStock(),
                dto.getCategoria(),
                dto.getActivo()
            )
        );   
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody productodto dto) {
        return ResponseEntity.ok(
            cafeteriaServicio.actualizarProducto(
                id,
                dto.getNombre(),
                dto.getPrecio(),
                dto.getStock(),
                dto.getCategoria(),
                dto.getActivo()
            )
        );
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(cafeteriaServicio.eliminarProducto(id));
    }
}
