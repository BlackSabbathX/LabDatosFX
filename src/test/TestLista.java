package test;

import Estructura.Lista;

public class TestLista {

    public static void main(String[] args) {
        Lista<Persona> lista = new Lista<>();
        System.out.println(lista.getItemCount());
        lista.insertarOrdenado(new Persona("Jose David Padilla", 19));
        System.out.println(lista.getItemCount());
        lista.insertarOrdenado(new Persona("Alvaro Baraque", 12));
        System.out.println(lista.getItemCount());
        lista.insertarOrdenado(new Persona("Vladimir Enrique", 27));
        System.out.println(lista.getItemCount());
        lista.insertarOrdenado(new Persona("Bayron Alveiro", 22));
        System.out.println(lista.getItemCount());
        lista.insertarOrdenado(new Persona("Jose David Padilla", 19));
        System.out.println(lista.getItemCount());
        lista.insertarOrdenado(new Persona("Alvaro Baraque", 12));
        System.out.println(lista.getItemCount());
        lista.insertarOrdenado(new Persona("Vladimir Enrique", 27));
        System.out.println(lista.getItemCount());
        lista.insertarOrdenado(new Persona("Bayron Alveiro", 22));
        for (int i = 0; i < lista.getItemCount(); i++) {
            System.out.println(lista.get(i).nombre);
        }
        System.out.println(lista.getItemCount());
        System.out.println();
        lista.reset();
        do {
            System.out.println(lista.getActual().nombre);
        } while (lista.hasNext());
        System.out.println(lista.getItemCount());
    }

    private static class Persona implements Comparable<Persona> {

        String nombre;
        int edad;

        public Persona(String _nombre, int _edad) {
            nombre = _nombre;
            edad = _edad;
        }

        @Override
        public int compareTo(Persona o) {
            return nombre.compareTo(o.nombre);
        }
    }

}
