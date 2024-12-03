/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nacho utn
 */
package model;

public class Ropa extends Producto {
    private String talla;

    public Ropa(String nombre, double precioBase, String talla) {
        super(nombre, precioBase);
        if (talla == null || talla.isEmpty()) {
            throw new IllegalArgumentException("La talla no puede ser nula ni vacía.");
        }
        this.talla = talla;
    }

    @Override
    public double calcularPrecioFinal() {
        double incremento = "XL".equalsIgnoreCase(talla) ? getPrecioBase() * 0.1 : 0;
        return getPrecioBase() + incremento;
    }
}
