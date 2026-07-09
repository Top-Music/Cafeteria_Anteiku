package com.example.Anteiku.service;

import com.example.Anteiku.dto.mensajerespuestadto;
import com.example.Anteiku.dto.paginaproductosdto;
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

    public mensajerespuestadto registrarProducto(String nombre, Double precio, Integer stock, String categoria, Boolean activo) {
        if (existeProductoPorNombre(nombre)) {
            return new mensajerespuestadto(false, "Error: Ya existe un producto con el nombre '" + nombre + "'");
        }

        if (precio == null || precio <= 0) {
            return new mensajerespuestadto(false, "Error: El precio debe ser mayor a 0");
        }

        if (stock == null || stock < 0) {
            return new mensajerespuestadto(false, "Error: El stock no puede ser negativo");
        }

        if (activo == null) {
            return new mensajerespuestadto(false, "Error: El campo 'activo' no puede ser nulo");
        }

        productodto producto = new productodto();
        producto.setId(siguienteId++);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setCategoria(categoria);
        producto.setActivo(activo);
        baseDatos.add(producto);

        return new mensajerespuestadto(true, "Producto registrado correctamente: " + nombre, producto.getId());
    }

    public mensajerespuestadto actualizarProducto(Long idProducto, String nombre, Double precio, Integer stock, String categoria, Boolean activo) {
        Optional<productodto> productoEncontrado = buscarPorIdInterno(idProducto);

        if (productoEncontrado.isEmpty()) {
            return new mensajerespuestadto(false, "Error: No existe un producto con ID: " + idProducto);
        }

        if (precio == null || precio <= 0) {
            return new mensajerespuestadto(false, "Error: El precio debe ser mayor a 0");
        }

        if (stock == null || stock < 0) {
            return new mensajerespuestadto(false, "Error: El stock no puede ser negativo");
        }

        if (activo == null) {
            return new mensajerespuestadto(false, "Error: El campo 'activo' no puede ser nulo");
        }

        productodto producto = productoEncontrado.get();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setCategoria(categoria);
        producto.setActivo(activo);

        return new mensajerespuestadto(true, "Producto actualizado correctamente: ID " + idProducto, idProducto);
    }

    public mensajerespuestadto eliminarProducto(Long idProducto) {
        Optional<productodto> productoEncontrado = buscarPorIdInterno(idProducto);

        if (productoEncontrado.isEmpty()) {
            return new mensajerespuestadto(false, "Error: No existe un producto con ID: " + idProducto, idProducto);
        }

        baseDatos.remove(productoEncontrado.get());
        return new mensajerespuestadto(true, "Producto eliminado correctamente", idProducto);
    }

    public paginaproductosdto listarProductosPaginados(int pagina, int tamano) {
        if (pagina < 0) {
            pagina = 0;
        }
        if (tamano <= 0) {
            tamano = 5;
        }

        int totalElementos = baseDatos.size();
        int totalPaginas = totalElementos == 0 ? 0 : (int) Math.ceil((double) totalElementos / tamano);
        int inicio = pagina * tamano;

        List<productodto> contenido;
        if (inicio >= totalElementos) {
            contenido = List.of();
        } else {
            int fin = Math.min(inicio + tamano, totalElementos);
            contenido = new ArrayList<>(baseDatos.subList(inicio, fin));
        }

        return new paginaproductosdto(
                contenido,
                pagina,
                tamano,
                totalElementos,
                totalPaginas,
                pagina < totalPaginas - 1,
                pagina > 0 && totalPaginas > 0
        );
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
            baseDatos.add(new productodto(siguienteId++, "Latte", 11.0, 20, "Bebida", true));
            baseDatos.add(new productodto(siguienteId++, "Cheesecake", 9.5, 8, "Postre", true));
            baseDatos.add(new productodto(siguienteId++, "Té verde", 8.0, 25, "Bebida", true));
            baseDatos.add(new productodto(siguienteId++, "Brownie", 7.5, 12, "Postre", true));
            baseDatos.add(new productodto(siguienteId++, "Ensalada César", 14.0, 6, "Comida", true));
            baseDatos.add(new productodto(siguienteId++, "Mocha", 13.0, 18, "Bebida", true));
        }
    }

    public CommandLineRunner cargarDatosIniciales() {
        return args -> inicializarDatos();
    }
}
