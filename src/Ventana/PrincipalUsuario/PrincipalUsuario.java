package Ventana.PrincipalUsuario;

import BaseDeDatos.Empresa;
import Ventana.Login.Login;
import Ventana.PrincipalUsuario.Agregar.Empresa.AgregarE;
import com.jfoenix.controls.JFXTabPane;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agregarMenu();
        posE = 0;
        posV = 0;
        posA = 0;
        loadEmpresas();
    }

    private void agregarMenu() {
        menuE = new ContextMenu();
        menuE.setStyle("" +
                "-fx-background-color: #34495e;" +
                "-fx-text-fill: white;" +
                "");
        MenuItem ag = new MenuItem("Agregar vacante"), ed = new MenuItem("Editar"), el = new MenuItem("Eliminar");
        ed.setStyle("" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;");
        el.setStyle("" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;");
        ag.setStyle("" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;");
        ed.setOnAction(e -> {
            editarClick();
        });
        el.setOnAction(e -> {
            eliminarClick();
        });
        ag.setOnAction(e -> {
            agregarClick();
        });
        menuE.getItems().addAll(ed, el, ag);
    }

    public void loadEmpresas() {
        numerosE.getChildren().removeAll();
        idesE.getChildren().removeAll();
        nombresE.getChildren().removeAll();
        telefonosE.getChildren().removeAll();
        boolean par = false;
        Color color1 = Color.WHITE;
        Color color2 = Color.rgb(190, 190, 255);
        for (Empresa empresa : Empresa.getEmpresas()) {
            Label numero = new Label(String.valueOf(empresa.getPos() + 1));
            Label id = new Label(String.valueOf(empresa.getId()));
            Label nombre = new Label(empresa.getNombre());
            Label telefono = new Label(empresa.getTelefono());
            final int pos = empresa.getPos();
            EventHandler listener = (EventHandler<MouseEvent>) e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posE = pos;
                    menuE.show(numero, e.getScreenX(), e.getScreenY());
                }
            };
            numero.setOnMouseClicked(listener);
            id.setOnMouseClicked(listener);
            nombre.setOnMouseClicked(listener);
            telefono.setOnMouseClicked(listener);
            Font font = new Font("System", 14);
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
        Task t = new Task() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ventana/PrincipalUsuario/Agregar/Empresa/AgregarE.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    ((AgregarE) loader.getController()).setExit(stage);
                    Platform.runLater(stage::show);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected Object call() throws Exception {
                return null;
            }
        };
    }

    @FXML
    void editarClick() {

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
