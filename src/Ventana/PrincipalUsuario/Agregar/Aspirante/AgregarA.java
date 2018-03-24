package Ventana.PrincipalUsuario.Agregar.Aspirante;

import BaseDeDatos.Aspirante;
import Estructura.Habilidad;
import Estructura.Lista;
import Estructura.Titulo;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarA implements Initializable {

    public static Stage agregar;
    public static AgregarA controlador;

    public static void toogleVisible() {
        if (agregar.isShowing()) {
            agregar.hide();
        } else {
            controlador.restart();
            agregar.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jornada.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> p) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            setStyle("" +
                                    "-fx-font-family: \"Segoe UI\";" +
                                    "-fx-text-fill: #111111;");
                        }
                    }
                };
            }
        });
        titulo.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> p) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            setStyle("" +
                                    "-fx-font-family: \"Segoe UI\";" +
                                    "-fx-text-fill: #111111;");
                        }
                    }
                };
            }
        });
        habilidad.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> p) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            setStyle("" +
                                    "-fx-font-family: \"Segoe UI\";" +
                                    "-fx-text-fill: #111111;");
                        }
                    }
                };
            }
        });
        minimo.focusedProperty().addListener((observable, ov, nv) -> {
            if (ov) {
                try {
                    Float.parseFloat(minimo.getText().trim());
                    minimo.setText(minimo.getText().trim());
                } catch (NumberFormatException ignored) {
                    minimo.setText("");
                }
            }
        });
        menus();
        loadComboBoxes();
    }

    private void menus() {
        menu = new ContextMenu();
        String styleM = "" +
                "-fx-background-color: #ffffff;" +
                "-fx-text-fill: white;" +
                "";
        String styleA = "" +
                "-fx-text-fill: black;" +
                "-fx-font-size: 13px;" +
                "-fx-font-family: \"Segoe UI\"";
        MenuItem nu = new MenuItem("Nivel 1"), nd = new MenuItem("Nivel 2"), nt = new MenuItem("Nivel 3"), nc = new MenuItem("Nivel 4"), nci = new MenuItem("Nivel 5");
        menu.setStyle(styleM);
        nu.setStyle(styleA);
        nd.setStyle(styleA);
        nt.setStyle(styleA);
        nc.setStyle(styleA);
        nci.setStyle(styleA);
        nu.setOnAction(event -> {
            String temporal = temp.getText();
            temp.setText(temp.getText().split(" ")[0] + " 1");
            _habilidades.set(_habilidades.indexOf(temporal), temp.getText());
            print();
        });
        nd.setOnAction(event -> {
            String temporal = temp.getText();
            temp.setText(temp.getText().split(" ")[0] + " 2");
            _habilidades.set(_habilidades.indexOf(temporal), temp.getText());
            print();
        });
        nt.setOnAction(event -> {
            String temporal = temp.getText();
            temp.setText(temp.getText().split(" ")[0] + " 3");
            _habilidades.set(_habilidades.indexOf(temporal), temp.getText());
            print();
        });
        nc.setOnAction(event -> {
            String temporal = temp.getText();
            temp.setText(temp.getText().split(" ")[0] + " 4");
            _habilidades.set(_habilidades.indexOf(temporal), temp.getText());
            print();
        });
        nci.setOnAction(event -> {
            String temporal = temp.getText();
            temp.setText(temp.getText().split(" ")[0] + " 5");
            _habilidades.set(_habilidades.indexOf(temporal), temp.getText());
            print();
        });
        menu.getItems().addAll(nu, nd, nt, nc, nci);
    }

    private void print() {
        for (String s : _titulos) {
            System.out.println(s);
        }
        for (String s : _habilidades) {
            System.out.println(s);
        }
    }

    private void loadTitHab() {
        titulo.getItems().clear();
        for (Titulo t : Titulo.values()) {
            titulo.getItems().add(t.toString());
        }
        habilidad.getItems().clear();
        for (Habilidad h : Habilidad.values()) {
            habilidad.getItems().add(h.toString());
        }
    }

    private void loadComboBoxes() {
        jornada.getItems().addAll("Completa", "Parcial", "Ambas");
        loadTitHab();
        titulo.setOnAction(event -> {
            if (titulo.getSelectionModel().getSelectedItem() == null) return;
            if (titulo.getSelectionModel().getSelectedIndex() == 0) return;
            if (titulo.getSelectionModel().getSelectedItem().contains("***")) return;
            String nv = titulo.getSelectionModel().getSelectedItem();
            Label t = new Label(nv);
            t.setFont(new Font("Segoe UI", 15));
            titulos.getChildren().add(t);
            _titulos.insertar(nv);
            titulo.getItems().set(titulo.getSelectionModel().getSelectedIndex(), "***" + nv + "***");
        });
        habilidad.setOnAction(event -> {
            if (habilidad.getSelectionModel().getSelectedItem() == null) return;
            if (habilidad.getSelectionModel().getSelectedIndex() == 0) return;
            if (habilidad.getSelectionModel().getSelectedItem().contains("***")) return;
            String nv = habilidad.getSelectionModel().getSelectedItem();
            Label t = new Label(nv + " 1");
            t.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    temp = t;
                    menu.show(t, e.getScreenX(), e.getScreenY());
                }
            });
            t.setFont(new Font("Segoe UI", 15));
            habilidades.getChildren().add(t);
            _habilidades.insertar(t.getText());
            habilidad.getItems().set(habilidad.getSelectionModel().getSelectedIndex(), "***" + nv + "***");
        });
    }

    private void restart() {
        loadTitHab();
        _titulos = new Lista<>();
        _habilidades = new Lista<>();
        jornada.getSelectionModel().select(0);
        habilidad.getSelectionModel().select(0);
        titulo.getSelectionModel().select(0);
        titulos.getChildren().clear();
        habilidades.getChildren().clear();
    }

    private void clear() {
        nombre.setText("");
        email.setText("");
        minimo.setText("");
        telefono.setText("");
    }

    @FXML
    public void cancelar() {
        clear();
        toogleVisible();
    }

    @FXML
    public void agregar() {
        if (_titulos.getItemCount() == 0) _titulos.insertar("Ninguno");
        if (_habilidades.getItemCount() == 0) _habilidades.insertar("Ninguna 1");
        if (_titulos.getItemCount() > 1 || !_titulos.get(0).equals(Titulo.Ninguno.toString())) {
            boolean okay = false;
            for (String t : _titulos) {
                if (t.equals(Titulo.Bachillerato.toString())) {
                    _titulos.reset();
                    okay = true;
                    break;
                }
            }
            if (!okay) {
                _titulos.insertar(Titulo.Bachillerato.toString());
            }
        }
        Aspirante.add(Aspirante.generateId(), nombre.getText().trim(), email.getText().trim(), telefono.getText().trim(), Float.parseFloat(minimo.getText()), "vacio", jornada.getSelectionModel().getSelectedItem(), _titulos, _habilidades);
        Aspirante.save(content);
        Aspirante.load(content);
        clear();
        toogleVisible();
    }

    @FXML
    private JFXTextField nombre;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField telefono;
    @FXML
    private JFXTextField minimo;
    @FXML
    private JFXComboBox<String> jornada;
    @FXML
    private JFXComboBox<String> titulo;
    @FXML
    private JFXComboBox<String> habilidad;
    @FXML
    private VBox titulos;
    @FXML
    private VBox habilidades;
    @FXML
    private StackPane content;
    private Lista<String> _titulos;
    private Lista<String> _habilidades;
    private ContextMenu menu;
    private Label temp;
}
