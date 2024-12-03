/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Nacho utn
 */
package model;

public class Electronico extends Producto {
    private int garantia;

    public Electronico(String nombre, double precioBase, int garantia) {
        super(nombre, precioBase);
        if (garantia < 0) {
            throw new IllegalArgumentException("La garantía debe ser mayor o igual a 0.");
        }
        this.garantia = garantia;
    }

    @Override
    public double calcularPrecioFinal() {
        double incremento = garantia > 12 ? getPrecioBase() * 0.2 : 0;
        return getPrecioBase() + incremento;
    }
}
