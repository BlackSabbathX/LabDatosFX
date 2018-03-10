package Ventana.Registro;

import BaseDeDatos.Usuario;
import Ventana.Dialog;
import Ventana.Login.Login;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
            }
        });
    }


    @FXML
    public void cambioTipo() {
        type = true;
    }

    @FXML
    public void cerrarClick() {
        toogleVisible();
    }

    @FXML
    public void volverClick() {
        Login.toogleVisible();
        toogleVisible();
    }

    @FXML
    public void registrarClick() {
        String c = contrasena.getText().trim();
        String c1 = contrasena1.getText().trim();
        if (pass && user && type) {
            String u = usuario.getText().trim();
            String t = tipo.getValue().toString();
            Usuario.add(u, c, t);
            Usuario.save();
            cleanForm();
            Dialog.showSimpleDialog(content, "Correcto", "Usuario creado exitosamente.", "Aceptar");
        } else {
            if (!c.equals(c1)) {
                Dialog.showSimpleDialog(content, "Corrija", "Las contraseñas digitadas no son iguales.", "Aceptar");
                return;
            }
            Dialog.showSimpleDialog(content, "Complete", "Formulario de registro incompleto.", "Aceptar");
        }
    }

    private void cleanForm() {
        contrasena.setText("");
        contrasena1.setText("");
    }

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
