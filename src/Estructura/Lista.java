package Estructura;

public class Lista<T> {

    private Nodo<T> ptr;

    public Lista(){

    }

    private class Nodo<T> {

        private Nodo<T> link;
        private T dato;

        public Nodo(Nodo<T> _link, T _dato) {
            link = _link;
            dato = _dato;
        }

    }
}
