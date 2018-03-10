package Ventana.Registro;

import BaseDeDatos.Usuario;
import Ventana.Dialog;
import Ventana.Login.Login;
import Ventana.PrincipalEvaluador.PrincipalEvaluador;
import Ventana.PrincipalUsuario.PrincipalUsuario;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class Registro implements Initializable {

    public static Stage registro;
    public static Registro controlador;

    public static void toogleVisible() {
        if (registro.isShowing()) {
            registro.hide();
        } else {
            registro.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pass = false;
        user = false;
        type = false;
        tipo.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> p) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            setTextFill(Color.BLACK);
                        }
                    }
                };
            }
        });
        tipo.getItems().add("Evaluador");
        tipo.getItems().add("Usuario");
        verificar();
    }

    private void habilitarRegistro() {
        if (pass && user && type) {
            registrar.setDisable(false);
        } else {
            registrar.setDisable(true);
        }
    }

    private void verificar() {
        usuario.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                String u = usuario.getText().trim();
                if (Usuario.usuarioExists(u)) {
                    Dialog.showSimpleDialog(content, "Error", "El nombre de usuario escogido ya existe.", "Aceptar");
                    usuario.setUnFocusColor(Color.RED);
                    user = false;
                    return;
                } else {
                    usuario.setUnFocusColor(Color.WHITE);
                }
                if (u.equals("")) {
                    user = false;
                    usuario.setUnFocusColor(Color.RED);
                    return;
                } else {
                    usuario.setUnFocusColor(Color.WHITE);
                }
                user = true;
                habilitarRegistro();
            }
        });
        contrasena.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                String c = contrasena.getText().trim();
                String c1 = contrasena1.getText().trim();
                if (c.equals("")) {
                    pass = false;
                    contrasena.setUnFocusColor(Color.RED);
                    return;
                } else {
                    contrasena.setUnFocusColor(Color.WHITE);
                }
                if (c1.equals("")) {
                    pass = false;
                    return;
                }
                if (c.equals(c1)) {
                    contrasena.setUnFocusColor(Color.WHITE);
                    contrasena1.setUnFocusColor(Color.WHITE);
                } else {
                    contrasena.setUnFocusColor(Color.RED);
                    contrasena1.setUnFocusColor(Color.RED);
                    pass = false;
                    return;
                }
                pass = true;
                habilitarRegistro();
            }
        });
        contrasena1.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                String c = contrasena.getText().trim();
                String c1 = contrasena1.getText().trim();
                if (c1.equals("")) {
                    pass = false;
                    contrasena1.setUnFocusColor(Paint.valueOf("red"));
                    return;
                } else {
                    contrasena1.setUnFocusColor(Paint.valueOf("white"));
                }
                if (c.equals("")) {
                    pass = false;
                    return;
                }
                if (c.equals(c1)) {
                    contrasena1.setUnFocusColor(Paint.valueOf("white"));
                    contrasena.setUnFocusColor(Paint.valueOf("white"));
                } else {
                    contrasena1.setUnFocusColor(Paint.valueOf("red"));
                    contrasena.setUnFocusColor(Paint.valueOf("red"));
                    pass = false;
                    return;
                }
                pass = true;
                habilitarRegistro();
            }
        });
    }

    public void onCambioTipo() {
        type = true;
    }

    public void onCerrarAction() {
        toogleVisible();
    }

    public void onVolverAction() {
        Login.toogleVisible();
        toogleVisible();
    }

    public void onRegistrarAction() {
        if (!registrar.isDisable()) {
            String c = contrasena.getText().trim();
            String c1 = contrasena1.getText().trim();
            String t = tipo.getValue().toString();

        }
    }

    @FXML
    private JFXButton registrar;
    @FXML
    private JFXTextField usuario;
    @FXML
    private JFXPasswordField contrasena;
    @FXML
    private JFXPasswordField contrasena1;
    @FXML
    private JFXComboBox tipo;
    @FXML
    private StackPane content;
    private boolean pass, user, type;

}
