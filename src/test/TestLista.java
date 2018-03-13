package test;

import Estructura.Lista;

public class TestLista {

    public static void main(String[] args) {
        Lista<Persona> lista = new Lista<>();
        System.out.println(lista.getItemCount());
        long in = System.currentTimeMillis();

        lista.insertarOrdenado(new Persona("Jose David Padilla", 19));
        lista.insertarOrdenado(new Persona("Alvaro Baraque", 12));
        lista.insertarOrdenado(new Persona("Vladimir Enrique", 27));
        lista.insertarOrdenado(new Persona("Bayron Alveiro", 22));
        lista.insertarOrdenado(new Persona("Jose David Padilla", 19));
        lista.insertarOrdenado(new Persona("Vladimir Enrique", 27));
        lista.insertarOrdenado(new Persona("Bayron Alveiro", 22));

        lista.forEach(persona -> System.out.println(persona.nombre));

        System.out.println();

        System.out.println(lista.getItemCount());
        lista.remove(2);
        lista.forEach(persona -> System.out.println(persona.nombre));
        System.out.println(lista.getItemCount());
        long an = System.currentTimeMillis();
        System.out.println(an - in);
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
