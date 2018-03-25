package Estructura;

public class HabilidadNivel implements Comparable<HabilidadNivel> {
    public Habilidad habilidad;
    public int nivel;

    public HabilidadNivel(Habilidad _habilidad, int _nivel) {
        habilidad = _habilidad;
        nivel = _nivel;
    }

    public HabilidadNivel(Habilidad _habilidad) {
        habilidad = _habilidad;
        nivel = 1;
    }

    public static HabilidadNivel fromString(String _text_) {
        String[] _text = _text_.split(" ");
        for (Habilidad habilidad : Habilidad.values()) {
            if (habilidad.getText().equalsIgnoreCase(_text[0])) {
                return new HabilidadNivel(habilidad, Integer.parseInt(_text[1]));
            }
        }
        return null;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int _nivel) {
        nivel = _nivel;
    }

    public String getText() {
        return habilidad.getText();
    }

    @Override
    public int compareTo(HabilidadNivel o) {
        return habilidad.getText().toUpperCase().compareTo(o.habilidad.getText().toUpperCase());
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
