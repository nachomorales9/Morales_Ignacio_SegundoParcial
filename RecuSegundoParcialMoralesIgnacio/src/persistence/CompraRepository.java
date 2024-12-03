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

public class CompraRepository implements Repository<Compra> { // clase que iimplementa Repository para manejar objetos compra
    private List<Compra> compras; //lista que almacena las compras en memoria
    private GestorPersistencia<Compra> gestorPersistencia; // gestor para manejar la persistencia de compras de un archivo 

    
    public CompraRepository(String archivo) throws IOException, ClassNotFoundException { //constructor que inicializa el repositorio de compras con un archivio
        this.gestorPersistencia = new GestorPersistencia<>(archivo);  // inicializa el gestor de persistencia con el archivo
        this.compras = gestorPersistencia.cargar(); // carga las compras desde el archivo
    }

    @Override
    public void add(Compra compra) {// metodo para agregar una compra
        compras.add(compra); // agrega la compra a la lista dfe compras
        guardarCambios();  // guarda los cambios en el archivo de persistencia
    }


    @Override
    public Optional<Compra> findById(int id) {
        return compras.stream().filter(c -> c.getIdCompra() == id).findFirst(); // filtras las compras por id y retorna la primera q coincide
    }

   
    @Override
    public List<Compra> findAll() { // metodo para obtetener todas las compras
        return new ArrayList<>(compras); // retorna una copia
    }


    private void guardarCambios() { // metodo privado para guardar los cambios en la lista de compras
        try {
            gestorPersistencia.guardar(compras);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar compras en el archivo.", e); // lanza excepcion si ocurre un error guardando
        }
    }
}

