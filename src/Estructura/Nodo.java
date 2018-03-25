package Estructura;


public class Nodo<G> {
    G dato;
    Nodo<G> link;

    Nodo(G _dato, Nodo<G> _link) {
        dato = _dato;
        link = _link;
    }

    Nodo(G _dato) {
        dato = _dato;
        link = null;
    }
}