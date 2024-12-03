/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Nacho utn
 */
package persistence;

import model.Compra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompraRepository implements Repository<Compra> {
    private List<Compra> compras;
    private GestorPersistencia<Compra> gestorPersistencia; 

    
    public CompraRepository(String archivo) throws IOException, ClassNotFoundException {
        this.gestorPersistencia = new GestorPersistencia<>(archivo); 
        this.compras = gestorPersistencia.cargar(); 
    }

    @Override
    public void add(Compra compra) {
        compras.add(compra);
        guardarCambios();  
    }


    @Override
    public Optional<Compra> findById(int id) {
        return compras.stream().filter(c -> c.getIdCompra() == id).findFirst();
    }

   
    @Override
    public List<Compra> findAll() {
        return new ArrayList<>(compras);
    }


    private void guardarCambios() {
        try {
            gestorPersistencia.guardar(compras);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar compras en el archivo.", e);
        }
    }
}

