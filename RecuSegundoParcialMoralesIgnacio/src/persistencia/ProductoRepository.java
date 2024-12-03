package persistencia;

import model.Producto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoRepository implements Repository<Producto> { // clase que implementa Repository para manejar productos
    private List<Producto> productos; // lista para almacenar productos en memoria
    private GestorPersistencia<Producto> gestorPersistencia; // gestor para manejar la persistencia en un archivo

    public ProductoRepository(String archivo) throws IOException, ClassNotFoundException { // constructor que inicializa el repositorio con un archivo
        this.gestorPersistencia = new GestorPersistencia<>(archivo); // inicializa el gestor de persistencia con el archivo
        this.productos = gestorPersistencia.cargar(); // carga los productos desde el archivo
    }

    @Override
    public void add(Producto producto) { // metodo para agregar un producto al repositorio
        productos.add(producto); // agrega el producto a la lista
        guardarCambios(); // guarda los cambios en el archivo
    }

    @Override
    public Optional<Producto> findById(int id) { // metodo para encontrar un producto por su ID
        return productos.stream() // inicia un stream sobre la lista de productos
                .filter(p -> p.getId() == id) // filtra por el ID del producto
                .findFirst(); // retorna el primer producto encontrado
    }

    @Override
    public List<Producto> findAll() { // metodo para obtener todos los productos
        return new ArrayList<>(productos); // retorna una copia de la lista de productos
    }

    public void guardarCambios() { // metodo para guardar los cambios realizados en los productos
        try {
            gestorPersistencia.guardar(productos); // intenta guardar los productos en el archivo
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar productos.", e); // lanza una excepción si ocurre un error al guardar
        }
    }
}
