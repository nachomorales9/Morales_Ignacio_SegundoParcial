/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Nacho utn
 */
//Obviamente cambia las importaciones de las clases según tu proyecto.
import model.Compra;
import model.Electronico;
import model.Producto;
import model.Ropa;
import services.GestorTienda;


import java.util.List;
import java.util.Optional;

public class Main {
    public static void Main(String[] args) {
        System.out.println("=== Pruebas de la tienda ===");
        try {
            //Nombres de los archivos de persistencia
            String productosFile = "productos.dat";
            String comprasFile = "compras.dat";
            GestorTienda gestor = new GestorTienda(productosFile, comprasFile);

            //----------------------------------------------------------------------------------------------------------
            System.out.println("\nPrueba 1: Agregar productos...");
            try {
                Producto electronico = new Electronico("Laptop", 1500.0, 24);
                Producto ropa = new Ropa("Camiseta", 20.0, "XL");
                gestor.agregarProducto(electronico);
                gestor.agregarProducto(ropa);
                System.out.println("Prueba 1 pasada ✅");
            } catch (Exception e) {
                System.out.println("Prueba 1 fallida ❌: " + e.getMessage());
            }

            //----------------------------------------------------------------------------------------------------------
            System.out.println("\nPrueba 2: Buscar producto por ID...");
            Optional<Producto> productoEncontrado = gestor.buscarProductoPorId(1);
            if (productoEncontrado.isPresent()) {
                System.out.println("Prueba 2 pasada ✅");
            } else {
                System.out.println("Prueba 2 fallida ❌");
            }

            //----------------------------------------------------------------------------------------------------------
            System.out.println("\nPrueba 3: Realizar compra...");
            try {
                Compra compra = new Compra("Cliente 1");
                Producto producto = gestor.buscarProductoPorId(1).orElseThrow();
                compra.agregarProducto(producto);
                gestor.realizarCompra(compra);
                System.out.println("Prueba 3 pasada ✅");
            } catch (Exception e) {
                System.out.println("Prueba 3 fallida ❌: " + e.getMessage());
            }

            //----------------------------------------------------------------------------------------------------------
            System.out.println("\nPrueba 4: Calcular ingresos...");
            double ingresos = gestor.calcularIngresos();
            if (ingresos > 0) {
                System.out.println("Prueba 4 pasada ✅");
            } else {
                System.out.println("Prueba 4 fallida ❌");
            }

            //----------------------------------------------------------------------------------------------------------
            System.out.println("\nPrueba 5: Filtrar productos...");
            List<Producto> productosFiltrados = gestor.filtrarProductos(p -> p instanceof Electronico);
            if (!productosFiltrados.isEmpty()) {
                System.out.println("Prueba 5 pasada ✅");
            } else {
                System.out.println("Prueba 5 fallida ❌");
            }

            //----------------------------------------------------------------------------------------------------------
            System.out.println("\nPrueba 6: Aplicar descuento...");
            try {
                gestor.aplicarDescuento(producto -> producto.getPrecioBase() * 0.9); // 10% de descuento
                System.out.println("Prueba 6 pasada ✅");
            } catch (Exception e) {
                System.out.println("Prueba 6 fallida ❌: " + e.getMessage());
            }

            //----------------------------------------------------------------------------------------------------------
            System.out.println("\nPrueba 7: Ver persistencia de productos...");
            try {
                List<Producto> productos = gestor.listarProductos();
                if (productos.isEmpty()) {
                    System.out.println("Prueba 7 fallida ❌: La lista de productos está vacía.");
                } else {
                    System.out.println("Prueba 7 pasada ✅: Lista de productos cargada.");
                    int lastId = -1;
                    boolean idsCorrectos = true;
                    for (Producto producto : productos) {
                        System.out.println(producto.mostrarResumen());
                        if (producto.getId() <= lastId) {
                            idsCorrectos = false;
                            break;
                        }
                        lastId = producto.getId();
                    }
                    if (idsCorrectos) {
                        System.out.println("Prueba 7 pasada ✅: IDs autoincrementales confirmados.");
                    } else {
                        System.out.println("Prueba 7 fallida ❌: Error en los IDs autoincrementales.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Prueba 7 fallida ❌: " + e.getMessage());
            }

            //----------------------------------------------------------------------------------------------------------
            System.out.println("\nPrueba 8: Ver persistencia de compras...");
            try {
                List<Compra> compras = gestor.listarCompras();
                if (compras.isEmpty()) {
                    System.out.println("Prueba 8 fallida ❌: La lista de compras está vacía.");
                } else {
                    System.out.println("Prueba 8 pasada ✅: Lista de compras cargada.");
                    int lastId = -1;
                    boolean idsCorrectos = true;
                    for (Compra compra : compras) {
                        System.out.println(compra.mostrarDetalle());
                        if (compra.getIdCompra() <= lastId) {
                            idsCorrectos = false;
                            break;
                        }
                        lastId = compra.getIdCompra();
                    }
                    if (idsCorrectos) {
                        System.out.println("Prueba 8 pasada ✅: IDs autoincrementales confirmados.");
                    } else {
                        System.out.println("Prueba 8 fallida ❌: Error en los IDs autoincrementales.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Prueba 8 fallida ❌: " + e.getMessage());
            }

            //----------------------------------------------------------------------------------------------------------
            System.out.println("\nPrueba 9: Buscar producto inexistente...");
            try {
                Optional<Producto> productoInexistente = gestor.buscarProductoPorId(99);
                if (productoInexistente.isEmpty()) {
                    System.out.println("Prueba 9 pasada ✅");
                } else {
                    System.out.println("Prueba 9 fallida ❌: El producto no debería existir.");
                }
            } catch (Exception e) {
                System.out.println("Prueba 9 fallida ❌: " + e.getMessage());
            }

            //----------------------------------------------------------------------------------------------------------
            System.out.println("\nPrueba 10: Agregar producto inválido...");
            try {
                Producto productoInvalido = new Electronico("", -100.0, -1);
                gestor.agregarProducto(productoInvalido);
                System.out.println("Prueba 10 fallida ❌: Se permitió agregar un producto inválido.");
            } catch (Exception e) {
                System.out.println("Prueba 10 pasada ✅: " + e.getMessage());
            }

            //----------------------------------------------------------------------------------------------------------
            // Aquí puedes agregar otras pruebas adicionales:


        } catch (Exception ex) {
            System.out.println("Error en las pruebas generales ❌: " + ex.getMessage());
        }


    }
}
