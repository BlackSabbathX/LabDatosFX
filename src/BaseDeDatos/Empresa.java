package BaseDeDatos;

import Estructura.Separator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import java.util.Random;

public class Empresa implements Comparable<Empresa> {

    private static ArrayList<Empresa> empresas;
    private static int itemCount;
    private static int _pos;
    private static final String DBPATH = "Empresa.txt";
    private static final File DBFILE = new File(DBPATH);

    public static ArrayList<Empresa> getEmpresas() {
        return empresas;
    }

    public static void init() {
        empresas = new ArrayList<>();
        itemCount = 0;
        _pos = 0;
        if (!DBFILE.exists()) {
            try {
                DBFILE.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al crear el archivo de empresas.", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos de las empresas. " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos de las empresas. " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException error) {
            PrintWriter esc = null;
            try {
                esc = new PrintWriter(new FileWriter(DBFILE));
                esc.write(" ");
                esc.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar la base de datos de las empresas. " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void save() {
        try {
            Collections.sort(empresas);
            PrintWriter escritor = new PrintWriter(new FileWriter(DBFILE));
            for (Empresa empresa : empresas) {
                escritor.write(empresa.getId() + Separator.A
                        + empresa.getNombre() + Separator.A
                        + empresa.getTelefono() + "\n");
            }
            escritor.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos de las empresas. " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void add(int _id, String _nombre, String _telefono) {
        empresas.add(new Empresa(_pos, _id, _nombre, _telefono));
        itemCount++;
        _pos++;
    }

    public static int generateId() {
        Random random = new Random();
        int _id;
        boolean isIn;
        do {
            _id = random.nextInt(1000);
            isIn = false;
            for (Empresa empresa : empresas) {
                if (empresa.getId() == _id) {
                    isIn = true;
                    break;
                }
            }
        } while (isIn);
        return _id;
    }

    static void removeAt(int i) {
        if (i >= 0 && i < itemCount) {
            empresas.remove(i);
            itemCount--;
        }
    }

    public static void remove(Empresa _empresa) {
        empresas.remove(_empresa);
        itemCount--;
    }

    static void editNombre(int i, String _nombre) {
        if (i >= 0 && i < itemCount) {
            empresas.get(i).setNombre(_nombre);
        }
    }

    static void editTelefono(int i, String _telefono) {
        if (i >= 0 && i < itemCount) {
            empresas.get(i).setTelefono(_telefono);
        }
    }

    public static int getItemCount() {
        return itemCount;
    }

    public static Empresa getEmpresaAt(int i) {
        if (i >= 0 && i < itemCount) {
            return empresas.get(i);
        }
        return null;
    }

    public static int indexOf(String _nombre) {
        int i = 0;
        for (Empresa empresa : empresas) {
            if (empresa.getNombre().equals(_nombre)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int indexOf(int _id) {
        int i = 0;
        for (Empresa empresa : empresas) {
            if (empresa.getId() == _id) {
                return i;
            }
            i++;
        }
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
