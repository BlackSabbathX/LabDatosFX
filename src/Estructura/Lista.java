package Estructura;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Lista<T extends Comparable<T>> implements Iterable<T>, Iterator<T> {

    private Nodo<T> ptr;
    private Nodo<T> actual;
    private int count;

    public Lista() {
        ptr = null;
        actual = null;
        count = 0;
    }

    public Lista(T[] array) {
        ptr = null;
        actual = null;
        count = 0;
        for (T t : array) {
            insertarOrdenado(t);
        }
    }

    public int getItemCount() {
        return count;
    }

    public void clear() {
        ptr = null;
        count = 0;
        reset();
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
            if (dato.compareTo(actual.dato) <= 0) {
                ptr = new Nodo<>(dato, ptr);
                count++;
                reset();
                return;
            }
            do {
                if (dato.compareTo(actual.dato) >= 0 && dato.compareTo(actual.link.dato) <= 0) {
                    actual.link = new Nodo<>(dato, actual.link);
                    count++;
                    reset();
                    return;
                }
                actual = actual.link;
            } while (actual.link != null);
            actual.link = new Nodo<>(dato);
        }
        count++;
        reset();
    }

    public void insertar(T dato) {
        if (ptr == null) {
            ptr = new Nodo<>(dato);
        } else {
            ptr = new Nodo<>(dato, ptr);
        }
        count++;
        reset();
    }

    public int indexOf(T dato) {
        Nodo<T> _actual = ptr;
        int pos = 0;
        while (_actual != null) {
            if (_actual.dato.equals(dato)) {
                return pos;
            }
            pos++;
            _actual = _actual.link;
        }
        return -1;
    }

    public T get(int i) {
        Nodo<T> _actual = ptr;
        int pos = 0;
        while (_actual != null) {
            if (pos == i) {
                return _actual.dato;
            }
            pos++;
            _actual = _actual.link;
        }
        return null;
    }

    public void set(int i, T dato) {
        Nodo<T> actual = ptr;
        int pos = 0;
        while (actual != null) {
            if (pos == i) {
                actual.dato = dato;
            }
            pos++;
            actual = actual.link;
        }
    }

//    public void remove(T dato) {
//        Nodo<T> actual = ptr;
//        if (ptr == null) {
//            return;
//        }
//        while (actual != null) {
//            if (actual.link == null) return;
//            if (actual.link.dato.equals(dato)) {
//                actual.link = actual.link.link;
//                count--;
//                return;
//            }
//            actual = actual.link;
//        }
//    }

    public void remove(int i) {
        Nodo<T> actual = ptr;
        int pos = 0;
        if (ptr == null) {
            return;
        }
        if (i == 0) {
            ptr = ptr.link;
            count--;
            return;
        }
        while (actual != null) {
            if (pos == i - 1) {
                actual.link = actual.link.link;
                count--;
                return;
            }
            pos++;
            actual = actual.link;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        if (actual == null) {
            reset();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public T next() {
        if (this.hasNext()) {
            T dato = actual.dato;
            actual = actual.link;
            return dato;
        }
        reset();
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
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
