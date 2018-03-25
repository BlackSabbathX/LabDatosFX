package Ventana.Login;

import BaseDeDatos.Usuario;
import Ventana.Dialog;
import Ventana.PrincipalEvaluador.PrincipalEvaluador;
import Ventana.PrincipalUsuario.PrincipalUsuario;
import Ventana.Registro.Registro;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {

    public static Stage login;
    public static Login controlador;
    @FXML
    private JFXTextField usuario;
    @FXML
    private JFXPasswordField contrasena;
    @FXML
    private JFXCheckBox recordar;
    @FXML
    private StackPane content;

    public static void toogleVisible() {
        if (login.isShowing()) {
            login.hide();
        } else {
            login.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void cerrarClick() {
        toogleVisible();
    }

    @FXML
    public void registrarClick() {
        Registro.toogleVisible();
        toogleVisible();
    }

    @FXML
    public void iniciarSesion() {
        if (usuario.getText().trim().equals("") || contrasena.getText().trim().equals("")) {
            Dialog.showSimpleDialog(content, "Complete", "Formulario de inicio de sesión incompleto. Llene todos los campos", "Aceptar");
            return;
        }
        Usuario _usuario = Usuario.logear(usuario.getText(), contrasena.getText());
        if (_usuario == null) {
            Dialog.showSimpleDialog(content, "Error", "Usuario o contraseña incorrectos", "Aceptar");
        } else {
            switch (_usuario.getTipo()) {
                case Evaluador:
                    PrincipalEvaluador.toogleVisible();
                    break;
                case Usuario:
                    PrincipalUsuario.toogleVisible();
                    break;
            }
            if (!recordar.isSelected()) {
                usuario.setText("");
            }
            toogleVisible();
        }
        contrasena.setText("");
    }

    @FXML
    public void contrasenaEnter() {
        iniciarSesion();
    }

}
