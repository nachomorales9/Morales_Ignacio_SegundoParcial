/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nacho utn
 */
package persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorPersistencia<T extends Serializable> {
    private String archivo;

    public GestorPersistencia(String archivo) {
        this.archivo = archivo;
    }

    public void guardar(List<T> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        }
    }

    public List<T> cargar() throws IOException, ClassNotFoundException {
        File file = new File(archivo);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<T>) ois.readObject();
        }
    }
}
