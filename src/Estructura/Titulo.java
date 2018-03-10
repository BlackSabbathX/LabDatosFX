package Estructura;

public enum Titulo {

    Ninguno("Ninguno") {
        @Override
        public String toString() {
            return "Ninguno";
        }
    },
    AdministracionDeEmpresas("Administración de Empresas") {
        @Override
        public String toString() {
            return "Administración de Empresas";
        }
    },
    Arquitectura("Arquitectura") {
        @Override
        public String toString() {
            return "Arquitectura";
        }
    },
    CienciasPoliticas("Ciencias Políticas") {
        @Override
        public String toString() {
            return "Ciencias Políticas";
        }
    },
    ComercioInternacional("Comercio Internacional") {
        @Override
        public String toString() {
            return "Comercio Internacional";
        }
    },
    ComunicacionSocial("Comunicación Social") {
        @Override
        public String toString() {
            return "Comunicación Social";
        }
    },
    ContaduriaPublica("Contaduría Pública") {
        @Override
        public String toString() {
            return "Contaduría Pública";
        }
    },
    Derecho("Derecho") {
        @Override
        public String toString() {
            return "Derecho";
        }
    },
    DisenoGrafico("Diseño Gráfico") {
        @Override
        public String toString() {
            return "Diseño Gráfico";
        }
    },
    Economia("Economía") {
        @Override
        public String toString() {
            return "Economía";
        }
    },
    Enfermeria("Enfermería") {
        @Override
        public String toString() {
            return "Enfermería";
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
    IngenieriaAgroindustrial("Ingeniería Agroindustrial") {
        @Override
        public String toString() {
            return "Ingeniería Agroindustrial";
        }
    },
    IngenieriaCivil("Ingeniería Civil") {
        @Override
        public String toString() {
            return "Ingeniería Civil";
        }
    },
    IngenieriaDeMinas("Ingeniería de Minas") {
        @Override
        public String toString() {
            return "Ingeniería de Minas";
        }
    },
    IngenieriaDeSistemas("Ingeniería de Sistemas") {
        @Override
        public String toString() {
            return "Ingeniería de Sistemas";
        }
    },
    IngenieriaDeTelecomunicaciones("Ingeniería de Telecomunicaciones") {
        @Override
        public String toString() {
            return "Ingeniería de Telecomunicaciones";
        }
    },
    IngenieriaElectrica("Ingeniería Electrica") {
        @Override
        public String toString() {
            return "Ingeniería Electrica";
        }
    },
    IngenieriaElectronica("Ingeniería Electrónica") {
        @Override
        public String toString() {
            return "Ingeniería Electrónica";
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
    Matematicas("Matemáticas") {
        @Override
        public String toString() {
            return "Matemáticas";
        }
    },
    Medicina("Medicina") {
        @Override
        public String toString() {
            return "Medicina";
        }
    },
    Musica("Música") {
        @Override
        public String toString() {
            return "Música";
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
    Psicologia("Psicología") {
        @Override
        public String toString() {
            return "Psicología";
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
