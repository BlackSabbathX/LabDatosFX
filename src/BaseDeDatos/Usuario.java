package BaseDeDatos;

import Estructura.TipoUsuario;
import Estructura.Separator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Usuario {

    private static ArrayList<Usuario> usuarios;
    private static int itemCount;
    private static final String dbpath = "Usuario.txt";
    private static final File dbfile = new File(dbpath);

    public static void init() {
        usuarios = new ArrayList<>();
        itemCount = 0;
        if (!dbfile.exists()) {
            try {
                dbfile.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al crear el archivo de aspirantes.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static int nEvaluadores() {
        int n = 0;
        for (Usuario usuario : usuarios) {
            if (usuario.getTipo() == TipoUsuario.Evaluador) {
                n++;
            }
        }
        return n;
    }

    public static int nUsuarios() {
        int n = 0;
        for (Usuario usuario : usuarios) {
            if (usuario.getTipo() == TipoUsuario.Usuario) {
                n++;
            }
        }
        return n;
    }

    public static void load() {
        try {
            usuarios.clear();
            BufferedReader lector = new BufferedReader(new FileReader(dbfile));
            String linea = lector.readLine().trim();
            while (linea != null && !linea.equals("")) {
                String[] registro = linea.split(Separator.a);
                String _usuario = registro[0];
                String _contrasena = registro[1];
                String _tipo = registro[2];
                add(_usuario, _contrasena, _tipo);
                linea = lector.readLine();
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

    public static void save() {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(dbfile));
            usuarios.forEach((usuario) -> {
                escritor.write(usuario.getUsuario() + Separator.a
                        + usuario.getContrasena() + Separator.a
                        + usuario.getTipo().toString() + Separator.a
                        + "\n");
            });
            escritor.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos de los usuarios. " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void add(String _usuarioStr, String _contrasena, String _tipo) {
        Usuario _usuario = new Usuario(_usuarioStr, _contrasena, _tipo);
        usuarios.add(_usuario);
        itemCount++;
    }

    public static boolean usuarioExists(String usuario) {
        for (Usuario actual : usuarios) {
            if (actual.getUsuario().equals(usuario)) {
                return true;
            }
        }
        return false;
    }

    public static Usuario logear(String _usuario, String _contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(_usuario)) {
                if (usuario.contrasenaCorrecta(_contrasena)) {
                    return usuario;
                } else {
                    break;
                }
            }
        }
        return null;
    }

    public static int getItemCount() {
        return itemCount;
    }

    private Usuario(String _usuario, String _contrasena, String _tipo) {
        usuario = _usuario;
        contrasena = _contrasena;
        tipo = TipoUsuario.fromString(_tipo);
    }

    private boolean contrasenaCorrecta(String _contrasena) {
        return (contrasena.equals(_contrasena.trim()));
    }

    public void setContrasena(String nueva, String actual) {
        if (contrasenaCorrecta(actual)) {
            contrasena = nueva;
        }
    }

    private String getUsuario() {
        return usuario;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    private String getContrasena() {
        return contrasena;
    }

    private final String usuario;
    private String contrasena;
    private final TipoUsuario tipo;

}
