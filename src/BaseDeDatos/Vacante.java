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
import javax.swing.JOptionPane;

public class Vacante {

    private static ArrayList<Vacante> vacantes;
    private static int itemCount;
    private static final String dbpath = "Vacante.txt";
    private static final File dbfile = new File(dbpath);

    public static void init() {
        vacantes = new ArrayList<>();
        itemCount = 0;
        if (!dbfile.exists()) {
            try {
                dbfile.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al crear el archivo de vacantes.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void load() {
        try {
            vacantes.clear();
            BufferedReader lector = new BufferedReader(new FileReader(dbfile));
            String linea = lector.readLine().trim();
            while (linea != null && !linea.equals("")) {
                String[] registro = linea.split(Separator.a);
                int _id = Integer.parseInt(registro[0]);
                String _nombre = registro[1];
                String _descripcion = registro[2];
                float _min = Float.parseFloat(registro[3]);
                float _max = Float.parseFloat(registro[4]);
                String _jornada = registro[5];
                String[] _titulos = registro[6].split(Separator.b);
                String[] _habilidades = registro[7].split(Separator.b);
                int _empresa = Integer.parseInt(registro[8]);
                add(_id, _nombre, _descripcion, _min, _max, _jornada, _titulos, _habilidades, _empresa);
                linea = lector.readLine();
            }
            lector.close();
        } catch (FileNotFoundException error) {
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos de las vacantes. " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos de las vacantes. " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            for (Vacante vacante : vacantes) {
                String titulosVacante = "";
                String habilidadesVacante = "";
                for (Titulo titulo : vacante.getTitulos()) {
                    titulosVacante = titulosVacante + titulo.toString() + Separator.b;
                }
                for (Habilidad habilidad : vacante.getHabilidades()) {
                    habilidadesVacante = habilidadesVacante + habilidad.toString() + Separator.b;
                }
                titulosVacante = titulosVacante.substring(0, titulosVacante.length() - 1);
                habilidadesVacante = habilidadesVacante.substring(0, habilidadesVacante.length() - 1);
                escritor.write(vacante.getId() + Separator.a
                        + vacante.getNombre() + Separator.a
                        + vacante.getDescripcion() + Separator.a
                        + vacante.getMin() + Separator.a
                        + vacante.getMax() + Separator.a
                        + vacante.getJornada().toString() + Separator.a
                        + titulosVacante + Separator.a
                        + habilidadesVacante + Separator.a
                        + vacante.getEmpresa().getId() + Separator.a
                        + "\n");
            }
            escritor.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos de las empresas. " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void add(int _id, String _nombre, String _descripcion, float _min, float _max, String _jornada, String[] _titulos, String[] _habilidades, int _idEmpresa) {
        Empresa _empresa = Empresa.getEmpresaAt(Empresa.indexOf(_idEmpresa));
        Vacante _vacante = new Vacante(_id, _nombre, _descripcion, _min, _max, _jornada, _empresa);
        for (String _titulo : _titulos) {
            _vacante.addTitulo(Titulo.fromString(_titulo));
        }
        for (String _habilidad : _habilidades) {
            _vacante.addHabilidad(Habilidad.fromString(_habilidad));
        }
        vacantes.add(_vacante);
        itemCount++;
    }

    public static ArrayList<Vacante> getVacantes() {
        return vacantes;
    }

    static void removeAt(int i) {
        if (i >= 0 && i < itemCount) {
            vacantes.remove(i);
            itemCount--;
        }
    }

    public static int getItemCount() {
        return itemCount;
    }

    static Vacante getVacanteAt(int i) {
        if (i >= 0 && i < itemCount) {
            return vacantes.get(i);
        }
        return null;
    }

    static int indexOf(String _nombre) {
        int i = 0;
        for (Vacante vacante : vacantes) {
            if (vacante.getNombre().equals(_nombre)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    static int indexOf(int _id) {
        int i = 0;
        for (Vacante vacante : vacantes) {
            if (vacante.getId() == _id) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public Vacante(int _id, String _nombre, String _descripcion, float _min, float _max, String _jornada, Empresa _empresa) {
        id = _id;
        nombre = _nombre;
        descripcion = _descripcion;
        salariomin = _min;
        salariomax = _max;
        jornada = Jornada.fromString(_jornada);
        titulos = new ArrayList<>();
        habilidades = new ArrayList<>();
        empresa = _empresa;
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

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getMin() {
        return salariomin;
    }

    public float getMax() {
        return salariomax;

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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setNombre(String _nombre) {
        nombre = _nombre;
    }

    public void setDescripción(String _descripcion) {
        descripcion = _descripcion;
    }

    public void setMin(float _salariomin) {
        salariomin = _salariomin;
    }

    public void setMax(float _salariomax) {
        salariomax = _salariomax;
    }

    public void setJornada(Jornada _jornada) {
        jornada = _jornada;
    }

    public void setJornada(String _jornada) {
        jornada = Jornada.fromString(_jornada);
    }

    private final int id;
    private String nombre;
    private String descripcion;
    private float salariomin;
    private float salariomax;
    private Jornada jornada;
    private ArrayList<Titulo> titulos;
    private ArrayList<Habilidad> habilidades;
    private final Empresa empresa;

}
