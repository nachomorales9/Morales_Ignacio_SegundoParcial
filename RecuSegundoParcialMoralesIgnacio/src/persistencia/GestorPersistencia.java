/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Clase GestorPersistencia que maneja la carga y almacenamiento de objetos
 * serializados en un archivo.
 * 
 * @param <T> Tipo genérico que debe ser serializable.
 * @author Nacho utn
 */
package persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorPersistencia<T extends Serializable> { // clase genérica que maneja la persistencia de objetos serializables
    private String archivo; // ruta del archivo donde se almacenan los datos

    public GestorPersistencia(String archivo) { // constructor que inicializa la ruta del archivo
        this.archivo = archivo; // asigna la ruta del archivo
    }

    public void guardar(List<T> lista) throws IOException { // metodo para guardar una lista de objetos en el archivo
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) { // crea un flujo de salida para escribir objetos en el archivo
            oos.writeObject(lista); // escribe la lista de objetos en el archivo
        }
    }

    public List<T> cargar() throws IOException, ClassNotFoundException { // metodo para cargar una lista de objetos desde el archivo
        File file = new File(archivo); // crea un objeto File con la ruta del archivo
        if (!file.exists()) return new ArrayList<>(); // si el archivo no existe, retorna una lista vacía
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) { // crea un flujo de entrada para leer objetos desde el archivo
            return (List<T>) ois.readObject(); // lee el objeto del archivo y lo convierte a una lista de tipo T
        }
    }
}
