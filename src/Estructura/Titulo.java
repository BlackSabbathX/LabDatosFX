package Estructura;

public enum Titulo {

    Ninguno("Ninguno") {
        @Override
        public String toString() {
            return "Ninguno";
        }
    },
    IngenieriaDeSistemas("Ingeniería de Sistemas") {
        @Override
        public String toString() {
            return "Ingeniería de Sistemas";
        }
    },
    IngenieriaIndustrial("Ingeniería Industrial") {
        @Override
        public String toString() {
            return "Ingeniería Industrial";
        }
    },
    IngenieriaMecanica("Ingeniería Mecánica") {
        @Override
        public String toString() {
            return "Ingeniería Mecánica";
        }
    },
    IngenieriaQuimica("Ingeniería Quimica") {
        @Override
        public String toString() {
            return "Ingeniería Quimica";
        }
    },
    IngenieriaCivil("Ingeniería Civil") {
        @Override
        public String toString() {
            return "Ingeniería Civil";
        }
    },
    IngenieriaAgroindustrial("Ingeniería Agroindustrial") {
        @Override
        public String toString() {
            return "Ingeniería Agroindustrial";
        }
    },
    IngenieriaElectronica("Ingeniería Electrónica") {
        @Override
        public String toString() {
            return "Ingeniería Electrónica";
        }
    },
    IngenieriaElectrica("Ingeniería Electrica") {
        @Override
        public String toString() {
            return "Ingeniería Electrica";
        }
    },
    IngenieriaDeTelecomunicaciones("Ingeniería de Telecomunicaciones") {
        @Override
        public String toString() {
            return "Ingeniería de Telecomunicaciones";
        }
    },
    IngenieriaDeMinas("Ingeniería de Minas") {
        @Override
        public String toString() {
            return "Ingeniería de Minas";
        }
    },
    Derecho("Derecho") {
        @Override
        public String toString() {
            return "Derecho";
        }
    },
    CienciasPoliticas("Ciencias Políticas") {
        @Override
        public String toString() {
            return "Ciencias Políticas";
        }
    },
    Filosofia("Filosofía") {
        @Override
        public String toString() {
            return "Filosofía";
        }
    },
    Geologia("Geología") {
        @Override
        public String toString() {
            return "Geología";
        }
    },
    Medicina("Medicina") {
        @Override
        public String toString() {
            return "Medicina";
        }
    },
    Enfermeria("Enfermería") {
        @Override
        public String toString() {
            return "Enfermería";
        }
    },
    QuimicaYFarmacia("Química y Farmacia") {
        @Override
        public String toString() {
            return "Química y Farmacia";
        }
    },
    RelacionesInternacionales("Relaciones Internacionales") {
        @Override
        public String toString() {
            return "Relaciones Internacionales";
        }
    },
    ComercioInternacional("Comercio Internacional") {
        @Override
        public String toString() {
            return "Comercio Internacional";
        }
    },
    Arquitectura("Arquitectura") {
        @Override
        public String toString() {
            return "Arquitectura";
        }
    },
    Economia("Economía") {
        @Override
        public String toString() {
            return "Economía";
        }
    },
    ContaduriaPublica("Contaduría Pública") {
        @Override
        public String toString() {
            return "Contaduría Pública";
        }
    },
    AdministracionDeEmpresas("Administración de Empresas") {
        @Override
        public String toString() {
            return "Administración de Empresas";
        }
    },
    Psicologia("Psicología") {
        @Override
        public String toString() {
            return "Psicología";
        }
    },
    DisenoGrafico("Diseño Gráfico") {
        @Override
        public String toString() {
            return "Diseño Gráfico";
        }
    },
    Matematicas("Matemáticas") {
        @Override
        public String toString() {
            return "Matemáticas";
        }
    },
    Odontologia("Odontología") {
        @Override
        public String toString() {
            return "Odontología";
        }
    },
    PedagogiaInfantil("Pedagogía Infantil") {
        @Override
        public String toString() {
            return "Pedagogía Infantil";
        }
    },
    ComunicacionSocial("Comunicación Social") {
        @Override
        public String toString() {
            return "Comunicación Social";
        }
    },
    Musica("Música") {
        @Override
        public String toString() {
            return "Música";
        }
    };

    private final String text;

    Titulo(String _text) {
        text = _text;
    }

    public static Titulo fromString(String _text) {
        for (Titulo titulo : Titulo.values()) {
            if (titulo.text.equalsIgnoreCase(_text)) {
                return titulo;
            }
        }
        return null;
    }

}
