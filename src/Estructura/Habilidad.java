package Estructura;

public enum Habilidad {

    Ninguna("Ninguna") {
        @Override
        public String toString() {
            return "Ninguna";
        }
    },
    Aleman("Alemán") {
        @Override
        public String toString() {
            return "Alemán";
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
    Chino("Chino") {
        @Override
        public String toString() {
            return "Chino";
        }
    },
    Excel("Excel") {
        @Override
        public String toString() {
            return "Excel";
        }
    },
    Frances("Francés") {
        @Override
        public String toString() {
            return "Francés";
        }
    },
    Ingles("Inglés") {
        @Override
        public String toString() {
            return "Inglés";
        }
    },
    Japones("Japonés") {
        @Override
        public String toString() {
            return "Japonés";
        }
    },
    Portugues("Portugues") {
        @Override
        public String toString() {
            return "Portugues";
        }
    },
    Powerpoint("Powerpoint") {
        @Override
        public String toString() {
            return "Powerpoint";
        }
    },
    Ruso("Ruso") {
        @Override
        public String toString() {
            return "Ruso";
        }
    },
    Word("Word") {
        @Override
        public String toString() {
            return "Word";
        }
    };

    private final String text;
    private int nivel;

    Habilidad(String _text) {
        text = _text;
        nivel = 1;
    }

    public void setNivel(int _nivel) {
        nivel = _nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public static Habilidad fromString(String _text_) {
        String[] _text = _text_.split(" ");
        for (Habilidad habilidad : Habilidad.values()) {
            if (habilidad.text.equalsIgnoreCase(_text[0])) {
                habilidad.setNivel(Integer.parseInt(_text[1]));
                return habilidad;
            }
        }
        return null;
    }

}
