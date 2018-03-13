package BaseDeDatos;

import Estructura.Lista;
import Estructura.Separator;
import Ventana.Dialog;

import java.io.*;
import java.util.Random;

public class Empresa implements Comparable<Empresa> {

    private static Lista<Empresa> empresas;
    private static int _pos;
    private static final String DBPATH = "Empresa.txt";
    private static final File DBFILE = new File(DBPATH);

    public static Lista<Empresa> getEmpresas() {
        return empresas;
    }

    public static void init() {
        empresas = new Lista<>();
        _pos = 0;
        if (!DBFILE.exists()) {
            try {
                DBFILE.createNewFile();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(null, "Error", "Error al crear el archivo de empresas.", "Aceptar");
            }
        }
    }

    public static void load() {
        try {
            empresas.clear();
            BufferedReader lector = new BufferedReader(new FileReader(DBFILE));
            String linea = lector.readLine().trim();
            _pos = 0;
            while (linea != null && !linea.equals("")) {
                String[] registro = linea.split(Separator.A);
                int _id = Integer.parseInt(registro[0]);
                String _nombre = registro[1];
                String _telefono = registro[2];
                add(_id, _nombre, _telefono);
                linea = lector.readLine();
            }
            lector.close();
        } catch (FileNotFoundException error) {
            Dialog.showSimpleDialog(null, "Error", "Error al cargar el archivo de empresas.", "Aceptar");
        } catch (IOException error) {
            Dialog.showSimpleDialog(null, "Error", "Error al cargar el archivo de empresas.", "Aceptar");
        } catch (NullPointerException error) {
            PrintWriter esc = null;
            try {
                esc = new PrintWriter(new FileWriter(DBFILE));
                esc.write(" ");
                esc.close();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(null, "Error", "Error al cargar el archivo de empresas.", "Aceptar");
            }
        }
    }

    public static void save() {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(DBFILE));
            empresas.reset();
            do {
                escritor.write(empresas.getActual().getId() + Separator.A
                        + empresas.getActual().getNombre() + Separator.A
                        + empresas.getActual().getTelefono() + "\n");
            } while (empresas.hasNext());
            escritor.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(null, "Error", "Error al cargar el archivo de empresas.", "Aceptar");
        }
    }

    public static void add(int _id, String _nombre, String _telefono) {
        empresas.insertarOrdenado(new Empresa(_pos, _id, _nombre, _telefono));
        _pos++;
    }

    public static int generateId() {
        Random random = new Random();
        int _id;
        boolean isIn;
        do {
            _id = random.nextInt(1000);
            isIn = false;
            empresas.reset();
            do {
                if (empresas.getActual().getId() == _id) {
                    isIn = true;
                    break;
                }
            } while (empresas.hasNext());
        } while (isIn);
        return _id;
    }

    static void removeAt(int i) {
        if (i >= 0 && i < empresas.getItemCount()) {
            empresas.remove(i);
        }
    }

    static void editNombre(int i, String _nombre) {
        if (i >= 0 && i < empresas.getItemCount()) {
            empresas.get(i).setNombre(_nombre);
        }
    }

    static void editTelefono(int i, String _telefono) {
        if (i >= 0 && i < empresas.getItemCount()) {
            empresas.get(i).setTelefono(_telefono);
        }
    }

    public static int getItemCount() {
        return empresas.getItemCount();
    }

    public static Empresa getEmpresaAt(int i) {
        if (i >= 0 && i < empresas.getItemCount()) {
            return empresas.get(i);
        }
        return null;
    }

    public static int indexOf(String _nombre) {
        int i = 0;
        empresas.reset();
        do {
            if (empresas.getActual().getNombre().equals(_nombre)) {
                return i;
            }
            i++;
        } while (empresas.hasNext());
        return -1;
    }

    public static int indexOf(int _id) {
        int i = 0;
        empresas.reset();
        do {
            if (empresas.getActual().getId() == _id) {
                return i;
            }
            i++;
        } while (empresas.hasNext());
        return -1;
    }

    public Empresa(int _pos, int _id, String _nombre, String _telefono) {
        pos = _pos;
        id = _id;
        nombre = _nombre;
        telefono = _telefono;
    }

    public int getPos() {
        return pos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getId() {
        return id;
    }

    public void setNombre(String _nombre) {
        nombre = _nombre;
    }

    public void setTelefono(String _telefono) {
        telefono = _telefono;
    }

    private final int pos;
    private String nombre;
    private String telefono;
    private final int id;

    @Override
    public int compareTo(Empresa emp) {
        return nombre.compareTo(emp.getNombre());
    }

}
