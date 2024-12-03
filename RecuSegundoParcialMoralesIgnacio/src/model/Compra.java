/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nacho utn
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Compra implements Serializable {
    private static int idCounter = 1;
    private int idCompra;
    private String cliente;
    private List<Producto> productos;
    private double total;

    public Compra(String cliente) {
        this.idCompra = idCounter++;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.total = 0;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        calcularTotal();
    }

    public void calcularTotal() {
        total = productos.stream().mapToDouble(Producto::calcularPrecioFinal).sum();
    }

    public String mostrarDetalle() {
        StringBuilder detalle = new StringBuilder("Compra ID: " + idCompra + ", Cliente: " + cliente + "\n");
        productos.forEach(producto -> detalle.append(producto.mostrarResumen()).append("\n"));
        detalle.append("Total: ").append(total);
        return detalle.toString();
    }
}
