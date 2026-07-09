package com.example.Anteiku.service;

import com.example.Anteiku.dto.productodto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class cafeteriaservicio {

    private final List<productodto> baseDatos = new ArrayList<>();
    private Long siguienteId = 1L;

    public cafeteriaservicio() {
        inicializarDatos();
    }

    public String registrarProducto(String nombre, Double precio, Integer stock, String categoria, Boolean activo) {
        if (existeProductoPorNombre(nombre)) {
            return "Error: Ya existe un producto con el nombre '" + nombre + "'";
        }

        if (precio == null || precio <= 0) {
            return "Error: El precio debe ser mayor a 0";
        }

        if (stock == null || stock < 0) {
            return "Error: El stock no puede ser negativo";
        }

        if (activo == null) {
            return "Error: El campo 'activo' no puede ser nulo";
        }

        productodto producto = new productodto();
        producto.setId(siguienteId++);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setCategoria(categoria);
        producto.setActivo(activo);
        baseDatos.add(producto);

        return "Producto registrado correctamente: " + nombre;
    }

    public String actualizarProducto(Long idProducto, String nombre, Double precio, Integer stock, String categoria, Boolean activo) {
        Optional<productodto> productoEncontrado = buscarPorIdInterno(idProducto);

        if (productoEncontrado.isEmpty()) {
            return "Error: No existe un producto con ID: " + idProducto;
        }

        if (precio == null || precio <= 0) {
            return "Error: El precio debe ser mayor a 0";
        }

        if (stock == null || stock < 0) {
            return "Error: El stock no puede ser negativo";
        }

        if (activo == null) {
            return "Error: El campo 'activo' no puede ser nulo";
        }

        productodto producto = productoEncontrado.get();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setCategoria(categoria);
        producto.setActivo(activo);

        return "Producto actualizado correctamente: ID " + idProducto;
    }

    public String eliminarProducto(Long idProducto) {
        Optional<productodto> productoEncontrado = buscarPorIdInterno(idProducto);

        if (productoEncontrado.isEmpty()) {
            return "Error: No existe un producto con ID: " + idProducto;
        }

        baseDatos.remove(productoEncontrado.get());
        return "Producto eliminado: ID " + idProducto;
    }

    public String listarProductos() {
        if (baseDatos.isEmpty()) {
            return "No hay productos registrados";
        }

        StringBuilder reporte = new StringBuilder("Menú registrado:<br>");
        for (productodto producto : baseDatos) {
            reporte.append(formatearProducto(producto)).append("<br>");
        }

        return reporte.toString();
    }

    public String buscarProducto(String criterio) {
        try {
            Long id = Long.parseLong(criterio);
            Optional<productodto> productoEncontrado = buscarPorIdInterno(id);
            if (productoEncontrado.isPresent()) {
                return formatearProducto(productoEncontrado.get());
            }
        } catch (NumberFormatException e) {
            List<productodto> productos = buscarPorNombre(criterio);
            if (!productos.isEmpty()) {
                StringBuilder reporte = new StringBuilder("Resultados:\n");
                for (productodto p : productos) {
                    reporte.append(formatearProducto(p)).append("\n");
                }
                return reporte.toString();
            }
        }
        return "No se encontró ningún producto con criterio: " + criterio;
    }

    public productodto obtenerProductoPorId(Long idProducto) {
        return buscarPorIdInterno(idProducto).orElse(null);
    }

    private Optional<productodto> buscarPorIdInterno(Long idProducto) {
        return baseDatos.stream()
                .filter(producto -> producto.getId() != null && producto.getId().equals(idProducto))
                .findFirst();
    }

    private boolean existeProductoPorNombre(String nombre) {
        return baseDatos.stream()
                .anyMatch(producto -> producto.getNombre() != null && producto.getNombre().equalsIgnoreCase(nombre));
    }

    private List<productodto> buscarPorNombre(String criterio) {
        return baseDatos.stream()
                .filter(producto -> producto.getNombre() != null
                        && producto.getNombre().toLowerCase().contains(criterio.toLowerCase()))
                .toList();
    }

    private String formatearProducto(productodto producto) {
    String estado = Boolean.TRUE.equals(producto.getActivo()) && producto.getStock() != null && producto.getStock() > 0
            ? "Disponible"
            : "No disponible";

    return "- ID: " + producto.getId()
            + " | " + producto.getNombre()
            + " | Categoría: " + producto.getCategoria()
            + " | Precio: S/ " + producto.getPrecio()
            + " | Stock: " + producto.getStock()
            + " | Estado: " + estado;
    }


    private void inicializarDatos() {
        if (baseDatos.isEmpty()) {
            baseDatos.add(new productodto(siguienteId++, "Capuccino", 12.5, 15, "Bebida", true));
            baseDatos.add(new productodto(siguienteId++, "Sandwich de pollo", 15.0, 10, "Comida", true));
        }
    }

    public CommandLineRunner cargarDatosIniciales() {
        return args -> inicializarDatos();
    }
}