package Estructura;

public class Lista<T extends Comparable<T>> {

    private Nodo<T> ptr;
    private Nodo<T> actual;
    private int count;

    public Lista() {
        ptr = null;
        actual = null;
        count = 0;
    }

    public int getItemCount() {
        return count;
    }

    public boolean hasNext() {
        if (actual == null) {
            return false;
        } else if (actual.link == null) {
            return false;
        } else {
            actual = actual.link;
            return true;
        }
    }

    public T getActual() {
        return actual.dato;
    }

    public void reset() {
        actual = ptr;
    }

    public void insertarOrdenado(T dato) {
        if (ptr == null) {
            ptr = new Nodo<>(dato);
        } else if (ptr.link == null) {
            if (dato.compareTo(ptr.dato) >= 0) {
                ptr.link = new Nodo<>(dato);
            } else {
                ptr = new Nodo<>(dato, ptr);
            }
        } else {
            Nodo<T> actual = ptr;
            do {
                if (dato.compareTo(actual.dato) >= -1 && dato.compareTo(actual.link.dato) <= 1) {
                    actual.link = new Nodo<>(dato, actual.link);
                    return;
                }
                actual = actual.link;
            } while (actual.link != null);
            actual.link = new Nodo<>(dato);
        }
        count++;
        actual = ptr;
    }

    public void insertar(T dato) {
        if (ptr == null) {
            ptr = new Nodo<>(dato);
        } else {
            ptr = new Nodo<>(dato, ptr);
        }
        count++;
        actual = ptr;
    }

    public T get(int i) {
        Nodo<T> actual = ptr;
        int pos = 0;
        while (actual != null) {
            if (pos == i) {
                return actual.dato;
            }
            pos++;
            actual = actual.link;
        }
        return null;
    }

    private class Nodo<G> {
        private G dato;
        private Nodo<G> link;

        private Nodo(G _dato, Nodo<G> _link) {
            dato = _dato;
            link = _link;
        }

        private Nodo(G _dato) {
            dato = _dato;
            link = null;
        }
    }

}
