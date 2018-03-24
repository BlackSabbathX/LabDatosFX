package Ventana.PrincipalUsuario;

import BaseDeDatos.Aspirante;
import BaseDeDatos.Empresa;
import BaseDeDatos.Vacante;
import Estructura.Lista;
import Ventana.Login.Login;
import Ventana.PrincipalUsuario.Agregar.Aspirante.AgregarA;
import Ventana.PrincipalUsuario.Agregar.Empresa.AgregarE;
import Ventana.PrincipalUsuario.Agregar.Vacante.AgregarV;
import Ventana.PrincipalUsuario.Editar.Empresa.EditarE;
import Ventana.PrincipalUsuario.Eliminar.Aspirante.EliminarA;
import Ventana.PrincipalUsuario.Eliminar.Empresa.EliminarE;
import Ventana.PrincipalUsuario.Eliminar.Vacante.EliminarV;
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
import java.text.NumberFormat;
import java.util.Locale;
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
        controlador.loadAspirantes();
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
        menuA = new ContextMenu();
        menuE.setStyle(styleM);
        menuV.setStyle(styleM);
        menuA.setStyle(styleM);
        MenuItem agE = new MenuItem("Agregar vacante"), edE = new MenuItem("Editar"), elE = new MenuItem("Eliminar");
        MenuItem elV = new MenuItem("Eliminar");
        MenuItem elA = new MenuItem("Eliminar");
        edE.setStyle(styleA);
        elE.setStyle(styleA);
        agE.setStyle(styleA);
        elV.setStyle(styleA);
        elA.setStyle(styleA);
        edE.setOnAction(e -> {
            editarClick();
            EditarE.controlador.setItem(posE);
        });
        elE.setOnAction(e -> {
            eliminarClick();
            EliminarE.controlador.setItem(posE);
        });
        agE.setOnAction(e -> {
            pestanas.getSelectionModel().select(1);
            agregarClick();
            AgregarV.controlador.setItemE(posE);
        });
        elV.setOnAction(e -> {
            eliminarClick();
            EliminarV.controlador.setItem(posV);
        });
        elA.setOnAction(e -> {
            eliminarClick();
            EliminarA.controlador.setItem(posA);
        });
        menuE.getItems().addAll(edE, elE, agE);
        menuV.getItems().addAll(elV);
        menuA.getItems().addAll(elA);
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
            NumberFormat f = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
            Label rango = new Label(f.format(vacante.getMin()) + " - " + f.format(vacante.getMax()));
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

    public void loadAspirantes() {
        numerosA.getChildren().clear();
        nombresA.getChildren().clear();
        emailsA.getChildren().clear();
        telefonosA.getChildren().clear();
        jornadasA.getChildren().clear();
        boolean par = true;
        Color color1 = Color.WHITE;
        Color color2 = Color.rgb(190, 190, 255);
        for (Aspirante aspirante : Aspirante.getAspirantes()) {
            Label numero = new Label(String.valueOf(aspirante.getPos() + 1));
            Label nombre = new Label(String.valueOf(aspirante.getNombre()));
            Label email = new Label(aspirante.getEmail());
            Label telefono = new Label(aspirante.getTelefono());
            Label jornada = new Label(aspirante.getJornada().toString());
            final int pos = aspirante.getPos();
            numero.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posA = pos;
                    menuA.show(numero, e.getScreenX(), e.getScreenY());
                }
            });
            nombre.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posA = pos;
                    menuA.show(nombre, e.getScreenX(), e.getScreenY());
                }
            });
            email.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posA = pos;
                    menuA.show(email, e.getScreenX(), e.getScreenY());
                }
            });
            telefono.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posA = pos;
                    menuA.show(telefono, e.getScreenX(), e.getScreenY());
                }
            });
            jornada.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posA = pos;
                    menuA.show(telefono, e.getScreenX(), e.getScreenY());
                }
            });
            Font font = new Font("Segoe UI", 15);
            numero.setFont(font);
            nombre.setFont(font);
            email.setFont(font);
            telefono.setFont(font);
            jornada.setFont(font);
            if (par) {
                numero.setTextFill(color1);
                nombre.setTextFill(color1);
                email.setTextFill(color1);
                telefono.setTextFill(color1);
                jornada.setTextFill(color1);
            } else {
                numero.setTextFill(color2);
                nombre.setTextFill(color2);
                email.setTextFill(color2);
                telefono.setTextFill(color2);
                jornada.setTextFill(color2);
            }
            par = !par;
            numerosA.getChildren().add(numero);
            nombresA.getChildren().add(nombre);
            emailsA.getChildren().add(email);
            telefonosA.getChildren().add(telefono);
            jornadasA.getChildren().add(jornada);
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
                AgregarV.toogleVisible();
                break;
            case 2:
                AgregarA.toogleVisible();
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
                EliminarV.toogleVisible();
                break;
            case 2:
                EliminarA.toogleVisible();
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
    private VBox numerosA;
    @FXML
    private VBox nombresA;
    @FXML
    private VBox emailsA;
    @FXML
    private VBox telefonosA;
    @FXML
    private VBox jornadasA;
    @FXML
    private JFXTabPane pestanas;
    @FXML
    private StackPane content;
    private ContextMenu menuE, menuV, menuA;
    private int posE, posV, posA;

}
