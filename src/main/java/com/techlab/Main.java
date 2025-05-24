package com.techlab;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Producto> productos = new ArrayList<>();
    static ArrayList<Pedido> pedidos = new ArrayList<>();
    static Producto prod = new Producto();

    public static void  main (String[] args){

        int option=0;
        String menu;
        do{
            System.out.println("Desea ver nuestro menu principal? S/N");
            menu = sc.nextLine();
            if (menu.equalsIgnoreCase("s")) {
                System.out.println(" ");
                System.out.println("\\n=========== SISTEMA DE E-COMMERCE - TECHLAB ===========");
                System.out.println("1- Agregar Productos");
                System.out.println("2- Mostrar Productos");
                System.out.println("3- Actualizar Productos");
                System.out.println("4- Eliminar Productos");
                System.out.println("5- Hacer un Pedido");
                System.out.println("6- Mostrar Pedido");
                System.out.println("7- Salir");
                System.out.println("Elija una opcion");
                option = Integer.parseInt(sc.nextLine());

                switch (option) {
                    case 1 -> prod.agregarProductos();
                    case 2 -> prod.mostrarProductos();
                    case 3 -> prod.actualizarProductos();
                    case 4 -> prod.eliminarProductos();
                    case 5 -> prod.hacerPedido();
                    case 6 -> prod.mostrarPedido();
                    case 7 -> System.out.println("Gracias por su visita...");
                    default -> System.out.println("Opcion invalida");
                }
            }else {
                System.out.println("Gracias por su visita...");
                break;
            }
        } while (option != 7);
    }
}
