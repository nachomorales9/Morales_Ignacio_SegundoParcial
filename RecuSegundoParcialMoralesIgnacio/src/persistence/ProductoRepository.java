/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

/**
 *
 * @author Nacho utn
 */
import model.Producto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoRepository implements Repository<Producto> {
    private List<Producto> productos;
    private GestorPersistencia<Producto> gestorPersistencia;

    public ProductoRepository(String archivo) throws IOException, ClassNotFoundException {
        this.gestorPersistencia = new GestorPersistencia<>(archivo);
        this.productos = gestorPersistencia.cargar();
    }

    @Override
    public void add(Producto producto) {
        productos.add(producto);
        guardarCambios();
    }

    @Override
    public Optional<Producto> findById(int id) {
        return productos.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public List<Producto> findAll() {
        return new ArrayList<>(productos);
    }

    private void guardarCambios() {
        try {
            gestorPersistencia.guardar(productos);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar productos.", e);
        }
    }
}

