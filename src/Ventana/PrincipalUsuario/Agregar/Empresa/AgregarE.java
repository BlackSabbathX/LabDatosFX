package Ventana.PrincipalUsuario.Agregar.Empresa;

import BaseDeDatos.Empresa;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarE implements Initializable {

    public static Stage agregar;
    public static AgregarE controlador;

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
        clear();
        toogleVisible();
    }

    @FXML
    private JFXTextField telefono;
    @FXML
    private JFXTextField nombre;
}
