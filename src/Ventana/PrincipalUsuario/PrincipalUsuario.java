package Ventana.PrincipalUsuario;

import BaseDeDatos.Empresa;
import BaseDeDatos.Vacante;
import Estructura.Lista;
import Ventana.Login.Login;
import Ventana.PrincipalUsuario.Agregar.Empresa.AgregarE;
import Ventana.PrincipalUsuario.Editar.Empresa.EditarE;
import Ventana.PrincipalUsuario.Eliminar.Empresa.EliminarE;
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
        controlador.loadVacantes();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agregarMenu();
        posE = 0;
        posV = 0;
        posA = 0;
    }

    private void agregarMenu() {
        String styleM = "" +
                "-fx-background-color: #34495e;" +
                "-fx-text-fill: white;" +
                "";
        String styleA = "" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-family: \"Segoe UI\"";
        menuE = new ContextMenu();
        menuV = new ContextMenu();
        menuE.setStyle(styleM);
        menuV.setStyle(styleM);
        MenuItem agE = new MenuItem("Agregar vacante"), edE = new MenuItem("Editar"), elE = new MenuItem("Eliminar");
        MenuItem elV = new MenuItem("Eliminar vacante");
        edE.setStyle(styleA);
        elE.setStyle(styleA);
        agE.setStyle(styleA);
        elV.setStyle(styleA);
        edE.setOnAction(e -> {
            editarClick();
            EditarE.controlador.setItem(posE);
        });
        elE.setOnAction(e -> {
            eliminarClick();
            EliminarE.controlador.setItem(posE);
        });
        agE.setOnAction(e -> {
        });
        elV.setOnAction(e -> {
            eliminarClick();
            //////////////////////////
        });
        menuE.getItems().addAll(edE, elE, agE);
        menuV.getItems().addAll(elV);
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

    public void loadVacantes() {
        numerosV.getChildren().clear();
        nombresV.getChildren().clear();
        empresasV.getChildren().clear();
        rangosV.getChildren().clear();
        boolean par = true;
        Color color1 = Color.WHITE;
        Color color2 = Color.rgb(190, 190, 255);
        for (Vacante vacante : Vacante.getVacantes()) {
            Label numero = new Label(String.valueOf(vacante.getPos() + 1));
            Label nombre = new Label(String.valueOf(vacante.getNombre()));
            Label empresa = new Label(vacante.getEmpresa().getNombre());
            Label rango = new Label(vacante.getMin() + " - " + vacante.getMax());
            final int pos = vacante.getPos();
            numero.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posV = pos;
                    menuV.show(numero, e.getScreenX(), e.getScreenY());
                }
            });
            nombre.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posV = pos;
                    menuV.show(nombre, e.getScreenX(), e.getScreenY());
                }
            });
            empresa.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posV = pos;
                    menuE.show(empresa, e.getScreenX(), e.getScreenY());
                }
            });
            rango.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posV = pos;
                    menuE.show(rango, e.getScreenX(), e.getScreenY());
                }
            });
            Font font = new Font("Segoe UI", 15);
            numero.setFont(font);
            nombre.setFont(font);
            empresa.setFont(font);
            rango.setFont(font);
            if (par) {
                numero.setTextFill(color1);
                nombre.setTextFill(color1);
                empresa.setTextFill(color1);
                rango.setTextFill(color1);
            } else {
                numero.setTextFill(color2);
                nombre.setTextFill(color2);
                empresa.setTextFill(color2);
                rango.setTextFill(color2);
            }
            par = !par;
            numerosV.getChildren().add(numero);
            nombresV.getChildren().add(nombre);
            empresasV.getChildren().add(empresa);
            rangosV.getChildren().add(rango);
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
        switch (pestanas.getSelectionModel().getSelectedIndex()) {
            case 0:
                EditarE.toogleVisible();
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }

    @FXML
    void eliminarClick() {
        switch (pestanas.getSelectionModel().getSelectedIndex()) {
            case 0:
                EliminarE.toogleVisible();
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }

    @FXML
    private VBox numerosE;
    @FXML
    private VBox nombresE;
    @FXML
    private VBox idesE;
    @FXML
    private VBox telefonosE;
    @FXML
    private VBox numerosV;
    @FXML
    private VBox nombresV;
    @FXML
    private VBox empresasV;
    @FXML
    private VBox rangosV;
    @FXML
    private JFXTabPane pestanas;
    @FXML
    private StackPane content;
    private ContextMenu menuE, menuV;
    private int posE, posV, posA;

}
