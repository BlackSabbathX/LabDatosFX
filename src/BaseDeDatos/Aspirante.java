package BaseDeDatos;

import Estructura.*;
import Ventana.Dialog;
import Ventana.PrincipalUsuario.Eliminar.Aspirante.EliminarA;
import Ventana.PrincipalUsuario.PrincipalUsuario;
import javafx.scene.layout.StackPane;

import java.io.*;
import java.util.Random;

public class Aspirante implements Comparable<Aspirante> {

    private static Lista<Aspirante> aspirantes;
    private static int _pos;
    private static final String DBPATH = "Aspirante.txt";
    private static final File DBFILE = new File(DBPATH);

    public static Lista<Aspirante> getAspirantes() {
        return aspirantes;
    }

    public static void init(StackPane content) {
        aspirantes = new Lista<>();
        _pos = 0;
        if (!DBFILE.exists()) {
            try {
                DBFILE.createNewFile();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error", "Error al crear el archivo de aspirantes.", "Aceptar");
            }
        }
    }

    public static void load(StackPane content) {
        try {
            aspirantes.clear();
            BufferedReader lector = new BufferedReader(new FileReader(DBFILE));
            String linea = lector.readLine().trim();
            _pos = 0;
            while (linea != null && !linea.equals("")) {
                String[] registro = linea.split(Separator.A);
                int _id = Integer.parseInt(registro[0]);
                String _nombre = registro[1];
                String _email = registro[2];
                String _telefono = registro[3];
                float _min = Float.parseFloat(registro[4]);
                String _foto = registro[5];
                String _jornada = registro[6];
                Lista<String> _titulos = new Lista<>(registro[7].split(Separator.B));
                Lista<String> _habilidades = new Lista<>(registro[8].split(Separator.B));
                add(_id, _nombre, _email, _telefono, _min, _foto, _jornada, _titulos, _habilidades);
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de los aspirantes.", "Aceptar");
        } catch (NullPointerException error) {
            PrintWriter esc;
            try {
                esc = new PrintWriter(new FileWriter(DBFILE));
                esc.write(" ");
                esc.close();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de los aspirantes.", "Aceptar");
            }
        }
        PrincipalUsuario.controlador.loadAspirantes();
        EliminarA.controlador.loadAspirantes();
    }

    public static void save(StackPane content) {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(DBFILE));
            aspirantes.forEach(aspirante -> {
                StringBuilder titulosAspirante = new StringBuilder();
                StringBuilder habilidadesAspirante = new StringBuilder();
                for (Titulo titulo : aspirante.getTitulos()) {
                    titulosAspirante.append(titulo.toString()).append(Separator.B);
                }
                for (Habilidad habilidad : aspirante.getHabilidades()) {
                    habilidadesAspirante.append(habilidad.toString()).append(" ").append(habilidad.getNivel()).append(Separator.B);
                }
                titulosAspirante = new StringBuilder(titulosAspirante.substring(0, titulosAspirante.length() - 1));
                habilidadesAspirante = new StringBuilder(habilidadesAspirante.substring(0, habilidadesAspirante.length() - 1));
                escritor.write(aspirante.getId() + Separator.A
                        + aspirante.getNombre() + Separator.A
                        + aspirante.getEmail() + Separator.A
                        + aspirante.getTelefono() + Separator.A
                        + aspirante.getSalariomin() + Separator.A
                        + aspirante.getFoto() + Separator.A
                        + aspirante.getJornada().toString() + Separator.A
                        + titulosAspirante + Separator.A
                        + habilidadesAspirante + Separator.A
                        + "\n");
            });
            escritor.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de los aspirantes.", "Aceptar");
        }
    }

    public static void add(int _id, String _nombre, String _email, String _telefono, float _min, String _foto, String _jornada, Lista<String> _titulos, Lista<String> _habilidades) {
        Aspirante _aspirante = new Aspirante(_pos, _id, _nombre, _email, _telefono, _min, _foto, _jornada);
        for (String _titulo : _titulos) {
            _aspirante.addTitulo(Titulo.fromString(_titulo));
        }
        for (String _habilidad : _habilidades) {
            _aspirante.addHabilidad(Habilidad.fromString(_habilidad));
        }
        aspirantes.insertarOrdenado(_aspirante);
        _pos++;
    }

    public static int generateId() {
        Random random = new Random();
        int _id;
        boolean isIn;
        do {
            _id = random.nextInt(1000);
            isIn = false;
            for (Aspirante aspirante : aspirantes) {
                if (aspirante.getId() == _id) {
                    isIn = true;
                    aspirantes.reset();
                    break;
                }
            }
        } while (isIn);
        return _id;
    }

    public static void removeAt(int i) {
        if (i >= 0 && i < aspirantes.getItemCount()) {
            aspirantes.remove(i);
        }
    }

    public static int getItemCount() {
        return aspirantes.getItemCount();
    }

    public static Aspirante getAspiranteAt(int i) {
        if (i >= 0 && i < aspirantes.getItemCount()) {
            return aspirantes.get(i);
        }
        return null;
    }

    static int indexOf(String _nombre) {
        int i = 0;
        for (Aspirante aspirante : aspirantes) {
            if (aspirante.getNombre().equals(_nombre)) {
                aspirantes.reset();
                return i;
            }
            i++;
        }
        return -1;
    }

    static int indexOf(int _id) {
        int i = 0;
        for (Aspirante aspirante : aspirantes) {
            if (aspirante.getId() == _id) {
                aspirantes.reset();
                return i;
            }
            i++;
        }
        return -1;
    }

    public Aspirante(int _pos, int _id, String _nombre, String _email, String _telefono, float _min, String _foto, String _jornada) {
        pos = _pos;
        jornada = Jornada.fromString(_jornada);
        id = _id;
        nombre = _nombre;
        email = _email;
        telefono = _telefono;
        salariomin = _min;
        foto = _foto;
        titulos = new Lista<>();
        habilidades = new Lista<>();
    }

    public void addTitulo(Titulo _titulo) {
        titulos.insertar(_titulo);
    }

    public void addHabilidad(Habilidad _habilidad) {
        habilidades.insertar(_habilidad);
    }

    public int getId() {
        return id;
    }

    public int getPos() {
        return pos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public float getSalariomin() {
        return salariomin;
    }

    public String getFoto() {
        return foto;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public Lista<Titulo> getTitulos() {
        return titulos;
    }

    public Lista<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setNombre(String _nombre) {
        nombre = _nombre;
    }

    public void setEmail(String _email) {
        email = _email;
    }

    public void setTelefono(String _telefono) {
        telefono = _telefono;
    }

    public void setSalariomin(float _salariomin) {
        salariomin = _salariomin;
    }

    public void setFoto(String _foto) {
        foto = _foto;
    }

    public void setJornada(Jornada _jornada) {
        jornada = _jornada;
    }

    private final int pos;
    private final int id;
    private String nombre;
    private String email;
    private String telefono;
    private float salariomin;
    private String foto;
    private Jornada jornada;
    private Lista<Titulo> titulos;
    private Lista<Habilidad> habilidades;

    @Override
    public int compareTo(Aspirante o) {
        return nombre.compareTo(o.nombre);
    }
}
