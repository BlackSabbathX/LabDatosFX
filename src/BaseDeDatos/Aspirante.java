package BaseDeDatos;

import Estructura.Titulo;
import Estructura.Habilidad;
import Estructura.Separator;
import Estructura.Jornada;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Aspirante {

    private static ArrayList<Aspirante> aspirantes;
    private static int itemCount;
    private static final String dbpath = "Aspirante.txt";
    private static final File dbfile = new File(dbpath);

    public static void init() {
        aspirantes = new ArrayList<>();
        itemCount = 0;
        if (!dbfile.exists()) {
            try {
                dbfile.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al crear el archivo de aspirantes.", "Error", JOptionPane.ERROR_MESSAGE);
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
        } catch (FileNotFoundException error) {
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos de los aspirantes. " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos de los aspirantes. " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException error) {
            PrintWriter esc = null;
            try {
                esc = new PrintWriter(new FileWriter(dbfile));
                esc.write(" ");
                esc.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar la base de datos de las empresas. " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    static void save() {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(dbfile));
            for (Aspirante aspirante : aspirantes) {
                String titulosAspirante = "";
                String habilidadesAspirante = "";
                for (Titulo titulo : aspirante.getTitulos()) {
                    titulosAspirante = titulosAspirante + titulo.toString() + Separator.B;
                }
                for (Habilidad habilidad : aspirante.getHabilidades()) {
                    habilidadesAspirante = habilidadesAspirante + habilidad.toString() + Separator.B;
                }
                titulosAspirante = titulosAspirante.substring(0, titulosAspirante.length() - 1);
                habilidadesAspirante = habilidadesAspirante.substring(0, habilidadesAspirante.length() - 1);
                escritor.write(aspirante.getId() + Separator.A
                        + aspirante.getNombre() + Separator.A
                        + aspirante.getEmail() + Separator.A
                        + aspirante.getTelefono() + Separator.A
                        + aspirante.getSalariomin() + Separator.A
                        + aspirante.getFotoPath() + Separator.A
                        + aspirante.getJornada().toString() + Separator.A
                        + titulosAspirante + Separator.A
                        + habilidadesAspirante + Separator.A
                        + "\n");
            }
            escritor.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos de las empresas. " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        aspirantes.add(_aspirante);
        itemCount++;
    }

    static void removeAt(int i) {
        if (i >= 0 && i < itemCount) {
            aspirantes.remove(i);
            itemCount--;
        }
    }

    static int getItemCount() {
        return itemCount;
    }

    static Aspirante getAspiranteAt(int i) {
        if (i >= 0 && i < itemCount) {
            return aspirantes.get(i);
        }
        return null;
    }

    static int indexOf(String _nombre) {
        int i = 0;
        for (Aspirante aspirante : aspirantes) {
            if (aspirante.getNombre().equals(_nombre)) {
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
                return i;
            }
            i++;
        }
        return -1;
    }

    public Aspirante(int _pos, int _id, String _nombre, String _email, String _telefono, float _min, String _foto, String _jornada) {
        pos = _pos;
        id = _id;
        nombre = _nombre;
        email = _email;
        telefono = _telefono;
        salariomin = _min;
        foto = new ImageIcon(_foto);
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

    public ImageIcon getFoto() {
        return foto;
    }

    public String getFotoPath() {
        return foto.getDescription();
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
        foto = new ImageIcon(_foto);
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
    private ImageIcon foto;
    private Jornada jornada;
    private ArrayList<Titulo> titulos;
    private ArrayList<Habilidad> habilidades;

}
