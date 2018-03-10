package Estructura;

public enum Habilidad {

    Ninguna("Ninguna") {
        @Override
        public String toString() {
            return "Ninguna";
        }
    },
    Ingles("Inglés") {
        @Override
        public String toString() {
            return "Inglés";
        }
    },
    Aleman("Alemán") {
        @Override
        public String toString() {
            return "Alemán";
        }
    },
    Chino("Chino") {
        @Override
        public String toString() {
            return "Chino";
        }
    },
    Portugues("Portugues") {
        @Override
        public String toString() {
            return "Portugues";
        }
    },
    Frances("Francés") {
        @Override
        public String toString() {
            return "Francés";
        }
    },
    Arabe("Árabe") {
        @Override
        public String toString() {
            return "Árabe";
        }
    },
    Bengali("Bengalí") {
        @Override
        public String toString() {
            return "Bengalí";
        }
    },
    Ruso("Ruso") {
        @Override
        public String toString() {
            return "Ruso";
        }
    },
    Japones("Japonés") {
        @Override
        public String toString() {
            return "Japonés";
        }
    },
    Excel("Excel") {
        @Override
        public String toString() {
            return "Excel";
        }
    },
    Word("Word") {
        @Override
        public String toString() {
            return "Word";
        }
    },
    Powerpoint("Powerpoint") {
        @Override
        public String toString() {
            return "Powerpoint";
        }
    };

    private final String text;

    Habilidad(String _text) {
        text = _text;
    }

    public static Habilidad fromString(String _text) {
        for (Habilidad habilidad : Habilidad.values()) {
            if (habilidad.text.equalsIgnoreCase(_text)) {
                return habilidad;
            }
        }
        return null;
    }

}
