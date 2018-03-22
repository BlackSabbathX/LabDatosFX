package Ventana.PrincipalUsuario.Agregar.Vacante;

import BaseDeDatos.Empresa;
import Ventana.PrincipalUsuario.PrincipalUsuario;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarV implements Initializable {

    public static Stage agregar;
    public static AgregarV controlador;

    public static void toogleVisible() {
        if (agregar.isShowing()) {
            agregar.hide();
        } else {
            agregar.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void clear() {
        nombre.setText("");
        telefono.setText("");
    }

    @FXML
    public void cancelar() {
        clear();
        toogleVisible();
    }

    @FXML
    public void agregar() {
        Empresa.add(Empresa.generateId(), nombre.getText().trim(), telefono.getText().trim(), true);
        Empresa.save();
        Empresa.load();
        PrincipalUsuario.controlador.loadEmpresas();
        new Thread(Empresa::save).start();
        clear();
        toogleVisible();
    }

    @FXML
    private JFXTextField telefono;
    @FXML
    private JFXTextField nombre;
}
