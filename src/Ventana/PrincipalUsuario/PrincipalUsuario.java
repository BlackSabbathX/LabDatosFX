package Ventana.PrincipalUsuario;

import BaseDeDatos.Empresa;
import Estructura.Lista;
import Ventana.Login.Login;
import Ventana.PrincipalUsuario.Agregar.Empresa.AgregarE;
import Ventana.PrincipalUsuario.Editar.Empresa.EditarE;
import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalUsuario implements Initializable {

    public static Stage usuario;
    public static PrincipalUsuario controlador;

    public static void toogleVisible() {
        if (usuario.isShowing()) {
            usuario.hide();
        } else {
            usuario.show();
        }
    }

    public static void loadAll() {
        controlador.loadEmpresas();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agregarMenu();
        posE = 0;
        posV = 0;
        posA = 0;
    }

    private void agregarMenu() {
        menuE = new ContextMenu();
        menuE.setStyle("" +
                "-fx-background-color: #34495e;" +
                "-fx-text-fill: white;" +
                "");
        MenuItem ag = new MenuItem("Agregar vacante"), ed = new MenuItem("Editar"), el = new MenuItem("Eliminar");
        String style = "" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-family: \"Segoe UI\"";
        ed.setStyle(style);
        el.setStyle(style);
        ag.setStyle(style);
        ed.setOnAction(e -> {
            editarClick();
            EditarE.controlador.setItem(posE);
        });
        el.setOnAction(e -> eliminarClick());
        ag.setOnAction(e -> {
        });
        menuE.getItems().addAll(ed, el, ag);
    }

    public void loadEmpresas() {
        numerosE.getChildren().clear();
        idesE.getChildren().clear();
        nombresE.getChildren().clear();
        telefonosE.getChildren().clear();
        boolean par = true;
        Color color1 = Color.WHITE;
        Color color2 = Color.rgb(190, 190, 255);
        Lista<Empresa> empresas = Empresa.getEmpresas();
        for (Empresa empresa : empresas) {
            Label numero = new Label(String.valueOf(empresa.getPos() + 1));
            Label id = new Label(String.valueOf(empresa.getId()));
            Label nombre = new Label(empresa.getNombre());
            Label telefono = new Label(empresa.getTelefono());
            final int pos = empresa.getPos();
            numero.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posE = pos;
                    menuE.show(numero, e.getScreenX(), e.getScreenY());
                }
            });
            id.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posE = pos;
                    menuE.show(id, e.getScreenX(), e.getScreenY());
                }
            });
            nombre.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posE = pos;
                    menuE.show(nombre, e.getScreenX(), e.getScreenY());
                }
            });
            telefono.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posE = pos;
                    menuE.show(telefono, e.getScreenX(), e.getScreenY());
                }
            });
            Font font = new Font("Segoe UI", 15);
            numero.setFont(font);
            id.setFont(font);
            nombre.setFont(font);
            telefono.setFont(font);
            if (par) {
                numero.setTextFill(color1);
                id.setTextFill(color1);
                nombre.setTextFill(color1);
                telefono.setTextFill(color1);
            } else {
                numero.setTextFill(color2);
                id.setTextFill(color2);
                nombre.setTextFill(color2);
                telefono.setTextFill(color2);
            }
            par = !par;
            numerosE.getChildren().add(numero);
            idesE.getChildren().add(id);
            nombresE.getChildren().add(nombre);
            telefonosE.getChildren().add(telefono);
        }
    }


    @FXML
    void cerrarSesion() {
        Login.toogleVisible();
        toogleVisible();
    }

    @FXML
    void cerrarAplicacion() {
        toogleVisible();
    }

    @FXML
    void agregarClick() {
        switch (pestanas.getSelectionModel().getSelectedIndex()) {
            case 0:
                AgregarE.toogleVisible();
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }

    @FXML
    void editarClick() {
        EditarE.toogleVisible();
    }

    @FXML
    void eliminarClick() {

    }

    @FXML
    private VBox numerosE;
    @FXML
    private JFXTabPane pestanas;
    @FXML
    private VBox nombresE;
    @FXML
    private VBox idesE;
    @FXML
    private StackPane content;
    @FXML
    private VBox telefonosE;
    private ContextMenu menuE;
    private int posE, posV, posA;

}
