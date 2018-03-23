package BaseDeDatos;

import Estructura.*;
import Ventana.Dialog;
import Ventana.PrincipalUsuario.Eliminar.Vacante.EliminarV;
import Ventana.PrincipalUsuario.PrincipalUsuario;
import javafx.scene.layout.StackPane;

import java.io.*;
import java.util.Random;

public class Vacante implements Comparable<Vacante> {

    private static Lista<Vacante> vacantes;
    private static int _pos;
    private static final String BDPATH = "Vacante.txt";
    private static final File DBFILE = new File(BDPATH);

    public static void init(StackPane content) {
        vacantes = new Lista<>();
        _pos = 0;
        if (!DBFILE.exists()) {
            try {
                DBFILE.createNewFile();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error al crear el archivo de vacantes.", "Error", "Aceptar");
            }
        }
    }

    public static void load(StackPane content) {
        try {
            vacantes.clear();
            BufferedReader lector = new BufferedReader(new FileReader(DBFILE));
            String linea = lector.readLine().trim();
            _pos = 0;
            while (linea != null && !linea.equals("")) {
                String[] registro = linea.split(Separator.A);
                int _id = Integer.parseInt(registro[0]);
                String _nombre = registro[1];
                String _descripcion = registro[2];
                float _min = Float.parseFloat(registro[3]);
                float _max = Float.parseFloat(registro[4]);
                String _jornada = registro[5];
                Lista<String> _titulos = new Lista<>(registro[6].split(Separator.B));
                Lista<String> _habilidades = new Lista<>(registro[7].split(Separator.B));
                int _empresa = Integer.parseInt(registro[8]);
                add(_id, _nombre, _descripcion, _min, _max, _jornada, _titulos, _habilidades, _empresa);
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de las vacantes.", "Aceptar");
        } catch (NullPointerException error) {
            try {
                PrintWriter esc = new PrintWriter(new FileWriter(DBFILE));
                esc.write(" ");
                esc.close();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de las vacantes.", "Aceptar");
            }
        }
        PrincipalUsuario.controlador.loadVacantes();
        EliminarV.controlador.loadVacantes();
    }

    public static void save(StackPane content) {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(DBFILE));
            vacantes.forEach(vacante -> {
                StringBuilder titulosVacante = new StringBuilder();
                StringBuilder habilidadesVacante = new StringBuilder();
                for (Titulo titulo : vacante.getTitulos()) {
                    titulosVacante.append(titulo.toString()).append(Separator.B);
                }
                for (Habilidad habilidad : vacante.getHabilidades()) {
                    habilidadesVacante.append(habilidad.toString()).append(" ").append(habilidad.getNivel()).append(Separator.B);
                }
                titulosVacante = new StringBuilder(titulosVacante.substring(0, titulosVacante.length() - 1));
                habilidadesVacante = new StringBuilder(habilidadesVacante.substring(0, habilidadesVacante.length() - 1));
                escritor.write(vacante.getId() + Separator.A
                        + vacante.getNombre() + Separator.A
                        + vacante.getDescripcion() + Separator.A
                        + vacante.getMin() + Separator.A
                        + vacante.getMax() + Separator.A
                        + vacante.getJornada().toString() + Separator.A
                        + titulosVacante + Separator.A
                        + habilidadesVacante + Separator.A
                        + vacante.getEmpresa().getId() + Separator.A
                        + "\n");
            });
            escritor.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de las vacantes.", "Aceptar");
        }
    }

    public static void add(int _id, String _nombre, String _descripcion, float _min, float _max, String _jornada, Lista<String> _titulos, Lista<String> _habilidades, int _idEmpresa) {
        Empresa _empresa = Empresa.getEmpresaAt(Empresa.indexOf(_idEmpresa));
        Vacante _vacante = new Vacante(_pos, _id, _nombre, _descripcion, _min, _max, _jornada, _empresa);
        for (String _titulo : _titulos) {
            if (!_titulo.trim().equals("")) {
                _vacante.addTitulo(Titulo.fromString(_titulo));
            }
        }
        for (String _habilidad : _habilidades) {
            if (!_habilidad.trim().equals("")) {
                Habilidad _h = Habilidad.fromString(_habilidad);
                _vacante.addHabilidad(_h);
            }
        }
        vacantes.insertarOrdenado(_vacante);
        _pos++;
    }

    public static int generateId() {
        Random random = new Random();
        int _id;
        boolean isIn;
        do {
            _id = random.nextInt(1000);
            isIn = false;
            for (Vacante vacante : vacantes) {
                if (vacante.getId() == _id) {
                    isIn = true;
                    vacantes.reset();
                    break;
                }
            }
        } while (isIn);
        return _id;
    }

    public static Lista<Vacante> getVacantes() {
        return vacantes;
    }

    public static void removeAt(int i) {
        if (i >= 0 && i < vacantes.getItemCount()) {
            vacantes.remove(i);
        }
    }

    public static void removeByEmpresa(int _id) {
        int i = 0;
        for (Vacante vacante : vacantes) {
            if (vacante.getEmpresa().getId() == _id) {
                vacantes.remove(vacante.getPos() - i);
                i++;
            }
        }
    }

    public static int getItemCount() {
        return vacantes.getItemCount();
    }

    public static Vacante getVacanteAt(int i) {
        if (i >= 0 && i < vacantes.getItemCount()) {
            return vacantes.get(i);
        }
        return null;
    }

    static int indexOf(String _nombre) {
        int i = 0;
        for (Vacante vacante : vacantes) {
            if (vacante.getNombre().equals(_nombre)) {
                vacantes.reset();
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
                vacantes.reset();
                return i;
            }
            i++;
        }
        return -1;
    }

    public Vacante(int _pos, int _id, String _nombre, String _descripcion, float _min, float _max, String _jornada, Empresa _empresa) {
        pos = _pos;
        id = _id;
        nombre = _nombre;
        descripcion = _descripcion;
        salariomin = _min;
        salariomax = _max;
        jornada = Jornada.fromString(_jornada);
        titulos = new Lista<>();
        habilidades = new Lista<>();
        empresa = _empresa;
    }

    public void addTitulo(Titulo _titulo) {
        titulos.insertar(_titulo);
    }

    public void addHabilidad(Habilidad _habilidad) {
        habilidades.insertar(_habilidad);
    }

    public int getPos() {
        return pos;
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

    public Lista<Titulo> getTitulos() {
        return titulos;
    }

    public Lista<Habilidad> getHabilidades() {
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

    @Override
    public int compareTo(Vacante vac) {
        return nombre.toUpperCase().compareTo(vac.getNombre().toUpperCase());
    }

    private final int pos;
    private final int id;
    private String nombre;
    private String descripcion;
    private float salariomin;
    private float salariomax;
    private Jornada jornada;
    private Lista<Titulo> titulos;
    private Lista<Habilidad> habilidades;
    private final Empresa empresa;

}
