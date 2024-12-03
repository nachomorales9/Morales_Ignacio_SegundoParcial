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

public abstract class Producto implements Serializable {
    private static int idCounter = 1;
    private int id;
    private String nombre;
    private double precioBase;

    public Producto(String nombre, double precioBase) {
        if (nombre == null || nombre.length() < 3) {
            throw new IllegalArgumentException("El nombre debe tener al menos 3 caracteres.");
        }
        if (precioBase <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }
        this.id = idCounter++;
        this.nombre = nombre;
        this.precioBase = precioBase;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public abstract double calcularPrecioFinal();

    public String mostrarResumen() {
        return "ID: " + id + ", Nombre: " + nombre + ", Precio Final: " + calcularPrecioFinal();
    }
}
