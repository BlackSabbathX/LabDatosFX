package BaseDeDatos;

import Estructura.*;
import Ventana.Dialog;

import java.io.*;
import java.util.ArrayList;

public class Aspirante implements Comparable<Aspirante> {

    private static Lista<Aspirante> aspirantes;
    //private static int itemCount;
    private static final String dbpath = "Aspirante.txt";
    private static final File dbfile = new File(dbpath);

    public static void init() {
        aspirantes = new Lista<>();
        if (!dbfile.exists()) {
            try {
                dbfile.createNewFile();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(null, "Error", "Error al crear el archivo de aspirantes.", "Aceptar");
            }
        }
    }

    public static void load() {
        try {
            aspirantes.clear();
            BufferedReader lector = new BufferedReader(new FileReader(dbfile));
            String linea = lector.readLine().trim();
            int _pos = 0;
            while (linea != null && !linea.equals("")) {
                String[] registro = linea.split(Separator.A);
                int _id = Integer.parseInt(registro[0]);
                String _nombre = registro[1];
                String _email = registro[2];
                String _telefono = registro[3];
                float _min = Float.parseFloat(registro[4]);
                String _foto = registro[5];
                String _jornada = registro[6];
                String[] _titulos = registro[7].split(Separator.B);
                String[] _habilidades = registro[8].split(Separator.B);
                add(_pos, _id, _nombre, _email, _telefono, _min, _foto, _jornada, _titulos, _habilidades);
                linea = lector.readLine();
                _pos++;
            }
            lector.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(null, "Error", "Error al cargar la base de datos de los aspirantes.", "Aceptar");
        } catch (NullPointerException error) {
            PrintWriter esc = null;
            try {
                esc = new PrintWriter(new FileWriter(dbfile));
                esc.write(" ");
                esc.close();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(null, "Error", "Error al cargar la base de datos de los aspirantes.", "Aceptar");
            }
        }
    }

    static void save() {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(dbfile));
            aspirantes.reset();
            do {
                String titulosAspirante = "";
                String habilidadesAspirante = "";
                for (Titulo titulo : aspirantes.getActual().getTitulos()) {
                    titulosAspirante = titulosAspirante + titulo.toString() + Separator.B;
                }
                for (Habilidad habilidad : aspirantes.getActual().getHabilidades()) {
                    habilidadesAspirante = habilidadesAspirante + habilidad.toString() + Separator.B;
                }
                titulosAspirante = titulosAspirante.substring(0, titulosAspirante.length() - 1);
                habilidadesAspirante = habilidadesAspirante.substring(0, habilidadesAspirante.length() - 1);
                escritor.write(aspirantes.getActual().getId() + Separator.A
                        + aspirantes.getActual().getNombre() + Separator.A
                        + aspirantes.getActual().getEmail() + Separator.A
                        + aspirantes.getActual().getTelefono() + Separator.A
                        + aspirantes.getActual().getSalariomin() + Separator.A
                        + aspirantes.getActual().getFoto() + Separator.A
                        + aspirantes.getActual().getJornada().toString() + Separator.A
                        + titulosAspirante + Separator.A
                        + habilidadesAspirante + Separator.A
                        + "\n");
            } while (aspirantes.hasNext());
            escritor.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(null, "Error", "Error al cargar la base de datos de los aspirantes.", "Aceptar");
        }
    }

    static void add(int _pos, int _id, String _nombre, String _email, String _telefono, float _min, String _foto, String _jornada, String[] _titulos, String[] _habilidades) {
        Aspirante _aspirante = new Aspirante(_pos, _id, _nombre, _email, _telefono, _min, _foto, _jornada);
        for (String _titulo : _titulos) {
            _aspirante.addTitulo(Titulo.fromString(_titulo));
        }
        for (String _habilidad : _habilidades) {
            _aspirante.addHabilidad(Habilidad.fromString(_habilidad));
        }
        aspirantes.insertarOrdenado(_aspirante);
    }

    static void removeAt(int i) {
        if (i >= 0 && i < aspirantes.getItemCount()) {
            aspirantes.remove(i);
        }
    }

    static int getItemCount() {
        return aspirantes.getItemCount();
    }

    static Aspirante getAspiranteAt(int i) {
        if (i >= 0 && i < aspirantes.getItemCount()) {
            return aspirantes.get(i);
        }
        return null;
    }

    static int indexOf(String _nombre) {
        int i = 0;
        aspirantes.reset();
        do {
            if (aspirantes.getActual().getNombre().equals(_nombre)) {
                return i;
            }
            i++;
        } while (aspirantes.hasNext());
        return -1;
    }

    static int indexOf(int _id) {
        int i = 0;
        aspirantes.reset();
        do {
            if (aspirantes.getActual().getId() == _id) {
                return i;
            }
            i++;
        } while (aspirantes.hasNext());
        return -1;
    }

    public Aspirante(int _pos, int _id, String _nombre, String _email, String _telefono, float _min, String _foto, String _jornada) {
        pos = _pos;
        id = _id;
        nombre = _nombre;
        email = _email;
        telefono = _telefono;
        salariomin = _min;
        //foto = new ImageIcon(_foto);
        jornada = Jornada.fromString(_jornada);
        titulos = new ArrayList<>();
        habilidades = new ArrayList<>();
    }

    public void addTitulo(Titulo _titulo) {
        titulos.add(_titulo);
    }

    public void addHabilidad(Habilidad _habilidad) {
        habilidades.add(_habilidad);
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

    public ArrayList<Titulo> getTitulos() {
        return titulos;
    }

    public ArrayList<Habilidad> getHabilidades() {
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
    private ArrayList<Titulo> titulos;
    private ArrayList<Habilidad> habilidades;

    @Override
    public int compareTo(Aspirante o) {
        return nombre.compareTo(o.nombre);
    }
}
