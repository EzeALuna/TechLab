package com.techlab;

import java.util.ArrayList;

public class Pedido {
    private static int contador = 1;
    private int id;
    private ArrayList<Carrito> carritos;

    public Pedido() {
        this.id = contador++;
        this.carritos = new ArrayList<>();
    }

    public ArrayList<Carrito> getCarritos() {
        return carritos;
    }

    public void setCarritos(ArrayList<Carrito> carritos) {
        this.carritos = carritos;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Pedido.contador = contador;
    }

    public void agregarCarrito(Carrito c) {
        carritos.add(c);
    }

    public double calcularTotal() {
        double total = 0;
        for (Carrito c : carritos) {
            total += c.calcularSubtotal();
        }
        return total;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido N°: ").append(id).append("\n");
        for (Carrito car : carritos){
            sb.append(car.getCantidad()).append((" x "))
                    .append(car.getProducto().getNombre())
                    .append(" ($").append(car.getProducto().getPrecio()).append(")\n");
        }
        sb.append("Total: $").append(calcularTotal());
        return sb.toString();
    }
}

