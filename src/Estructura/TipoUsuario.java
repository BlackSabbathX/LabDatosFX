package Estructura;

public enum TipoUsuario {

    Usuario("Usuario") {
        @Override
        public String toString() {
            return "Usuario";
        }
    },
    Evaluador("Evaluador") {
        @Override
        public String toString() {
            return "Evaluador";
        }
    };

    private final String text;

    TipoUsuario(String _text) {
        text = _text;
    }

    public static TipoUsuario fromString(String _text) {
        for (TipoUsuario tu : TipoUsuario.values()) {
            if (tu.text.equalsIgnoreCase(_text)) {
                return tu;
            }
        }
        return null;
    }

}
