/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nacho utn
 */
package services;

import model.Compra;
import model.Producto;
import persistence.CompraRepository;
import persistence.ProductoRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class GestorTienda {
    private ProductoRepository productoRepository;
    private CompraRepository compraRepository;

    public GestorTienda(String productosFile, String comprasFile) throws IOException, ClassNotFoundException {
        this.productoRepository = new ProductoRepository(productosFile);
        this.compraRepository = new CompraRepository(comprasFile);
    }

    // Método para agregar un producto al repositorio
    public void agregarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        productoRepository.add(producto);
    }

    // Método para buscar un producto por ID
    public Optional<Producto> buscarProductoPorId(int id) {
        return productoRepository.findById(id);
    }

    // Método para listar todos los productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Método para registrar una compra
    public void realizarCompra(Compra compra) {
        if (compra == null || compra.getProductos().isEmpty()) {
            throw new IllegalArgumentException("La compra no puede estar vacía.");
        }

        // Validar que todos los productos existan en el repositorio
        for (Producto producto : compra.getProductos()) {
            if (!productoRepository.findById(producto.getId()).isPresent()) {
                throw new IllegalArgumentException("Producto con ID " + producto.getId() + " no encontrado.");
            }
        }

        // Calcular el total y registrar la compra
        compra.calcularTotal();
        compraRepository.add(compra);
    }

    // Método para calcular los ingresos totales
    public double calcularIngresos() {
        return compraRepository.findAll().stream()
                .mapToDouble(Compra::getTotal)
                .sum();
    }

    // Método para filtrar productos según un criterio
    public List<Producto> filtrarProductos(Predicate<Producto> criterio) {
        return productoRepository.findAll().stream()
                .filter(criterio)
                .toList();
    }

    // Método para aplicar descuentos a los productos
    public void aplicarDescuento(Function<Producto, Double> descuento) {
        List<Producto> productos = productoRepository.findAll();
        productos.forEach(producto -> {
            double nuevoPrecio = descuento.apply(producto);
            if (nuevoPrecio <= 0) {
                throw new IllegalArgumentException("El descuento no puede generar un precio negativo o cero.");
            }
            producto.setPrecioBase(nuevoPrecio); // Método setPrecioBase deberá agregarse en Producto.
        });
        productoRepository.guardarCambios(); // Asegurarse de guardar cambios.
    }

    // Método para listar todas las compras
    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }
}


