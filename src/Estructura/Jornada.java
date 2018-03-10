package Estructura;

public enum Jornada {

    Completa("Completa"),
    Parcial("Parcial"),
    Ambas("Ambas");

    private final String text;

    Jornada(String _text) {
        text = _text;
    }

    @Override
    public String toString() {
        if (null == this) {
            return "Ambas";
        } else switch (this) {
            case Completa:
                return "Completa";
            case Parcial:
                return "Parcial";
            default:
                return "Ambas";
        }
    }

    public static Jornada fromString(String _text) {
        for (Jornada jornada : Jornada.values()) {
            if (jornada.text.equalsIgnoreCase(_text)) {
                return jornada;
            }
        }
        return null;
    }
}
