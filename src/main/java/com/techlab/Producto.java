package com.techlab;

import com.techlab.extensions.StockInsuficienteExcepcion;

import java.util.ArrayList;
import java.util.Scanner;

public class Producto {

    private static int contador = 1;
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    static ArrayList<Producto> productos = new ArrayList<>();
    static ArrayList<Pedido> pedidos = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public Producto(String nombre, double precio, int stock){
        this.id = contador++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto() {

    }

    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public double getPrecio(){

        return precio;
    }
    public int getStock(){

        return stock;
    }

    public static int getContador() {
        return contador;
    }

    public void setNombre(String nombre){
        if (nombre!=null){
           this.nombre = nombre;
        }
    }
    public void setPrecio(double precio){
        if (precio > 0) {
            this.precio = precio;
        }
    }
    public void setStock(int stock){
        if (stock >= 0) {
            this.stock = stock;
        }
    }

    public static void agregarProductos() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(sc.nextLine());
        System.out.print("Stock: ");
        int stock = Integer.parseInt(sc.nextLine());
        productos.add(new Producto(nombre, precio, stock));
        System.out.println("Producto agregado correctamente");
    }

    public static void mostrarProductos(){
        for (Producto p : productos){
            System.out.println(p);
        }
    }

    public static void actualizarProductos(){
        System.out.println("Ingrese nombre del producto: ");
        String nombre = sc.nextLine();
        for (Producto p : productos){
            if (p.getNombre().equalsIgnoreCase(nombre)){
            System.out.println(p);
            System.out.print("Actualizar nombre? (S/N): ");
            if (sc.nextLine().equalsIgnoreCase("s")) {
                System.out.print("Nuevo nombre: ");
                p.setNombre(sc.nextLine());
            }
            System.out.print("Actualizar precio? (S/N): ");
            if (sc.nextLine().equalsIgnoreCase("s")) {
                System.out.print("Nuevo precio: ");
                p.setPrecio(sc.nextDouble());
            }
            System.out.print("Actualizar stock? (S/N): ");
            if (sc.nextLine().equalsIgnoreCase("s")) {
                System.out.print("Nuevo stock: ");
                p.setStock(sc.nextInt());
            }
            return;
        }
    }
        System.out.println("Producto no encontrado.");
    }

    public static void eliminarProductos() {
        System.out.println("Ingrese el nombre del producto a eliminar: ");
        String nombre = sc.nextLine();
        Producto eliminar = null;
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                eliminar = p;
                break;
            }
        }
            if (eliminar != null) {
                System.out.println(eliminar);
                System.out.println("Desea eliminar  S/N");
                if (sc.nextLine().equalsIgnoreCase("s")) {
                    productos.remove(eliminar);
                    System.out.println("Producto eliminado");
                } else {
                    System.out.println("Producto NO eliminado");
                }
            } else {
                System.out.println("Producto no encontrado.");
            }
    }

    public static void hacerPedido(){
        Pedido pedido = new Pedido();
        while(true){
            mostrarProductos();
            System.out.println("Nombre del producto para agregar (X para terminar): ");
            String nombre = sc.nextLine();
            if (nombre.equalsIgnoreCase("x")) break;
            Producto encontrado = null;
            for (Producto p : productos){
                if (p.getNombre().equalsIgnoreCase(nombre)){
                   encontrado = p;
                   break;
                }
            }
            if (encontrado == null){
                System.out.println("Producto NO encontrado");
                continue;
            }
            System.out.println("Cantidad: ");
            int cantidad = Integer.parseInt(sc.nextLine());

            try{
                if (cantidad > encontrado.getStock()){
                    throw new StockInsuficienteExcepcion("Stock insuficiente "+encontrado.getNombre());
                }
                pedido.agregarCarrito(new Carrito(encontrado, cantidad));
                encontrado.setStock(encontrado.getStock() - cantidad);
            }catch (StockInsuficienteExcepcion e){
                System.out.println(e.getMessage());
            }
        }
        pedidos.add(pedido);
        System.out.println("Pedido creado con exito: ");
        System.out.println("Su pedido es: "+pedido);
    }

    public static void mostrarPedido(){
        for (Pedido p : pedidos){
            System.out.println(p);
        }
    }

    @Override
    public String toString(){
        return  " Id: "+id+"\n Nombre: "+nombre+"\n Precio: $"+precio+"\n Stock: "+stock;
    }
}
