package Ventana.PrincipalUsuario.Agregar.Vacante;

import BaseDeDatos.Empresa;
import BaseDeDatos.Vacante;
import Estructura.Habilidad;
import Estructura.Lista;
import Estructura.Titulo;
import Ventana.PrincipalUsuario.PrincipalUsuario;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarV implements Initializable {

    public static Stage agregar;
    public static AgregarV controlador;

    public static void toogleVisible() {
        if (agregar.isShowing()) {
            agregar.hide();
        } else {
            if (Empresa.getItemCount() == 0) return;
            controlador.restart();
            agregar.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        empresa.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
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
        maximo.focusedProperty().addListener((observable, ov, nv) -> {
            if (ov) {
                try {
                    Float.parseFloat(maximo.getText().trim());
                    maximo.setText(maximo.getText().trim());
                } catch (NumberFormatException ignored) {
                    maximo.setText("");
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
        jornada.getItems().addAll("Completa", "Parcial");
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
        setItemE(0);
        jornada.getSelectionModel().select(0);
        habilidad.getSelectionModel().select(0);
        titulo.getSelectionModel().select(0);
        titulos.getChildren().clear();
        habilidades.getChildren().clear();
    }

    public void setItemE(int n) {
        if (Empresa.getItemCount() == 0) return;
        if (empresa.getItems().size() > n) {
            empresa.getSelectionModel().select(n);
        }
    }

    public void loadEmpresas() {
        empresa.getItems().clear();
        Empresa.getEmpresas().forEach(emp -> empresa.getItems().add(emp.getNombre()));
    }

    private void clear() {
        nombre.setText("");
        descripcion.setText("");
        minimo.setText("");
        maximo.setText("");
    }

    @FXML
    public void cancelar() {
        clear();
        toogleVisible();
    }

    @FXML
    public void agregar() {
        Vacante.add(Vacante.generateId(), nombre.getText().trim(), descripcion.getText().trim(), Float.parseFloat(minimo.getText()), Float.parseFloat(maximo.getText()), jornada.getSelectionModel().getSelectedItem(), _titulos, _habilidades, Empresa.getEmpresaAt(empresa.getSelectionModel().getSelectedIndex()).getId());
        Vacante.save();
        Vacante.load();
        PrincipalUsuario.controlador.loadVacantes();
        clear();
        toogleVisible();
    }

    @FXML
    private JFXTextField nombre;
    @FXML
    private JFXTextField minimo;
    @FXML
    private JFXTextField maximo;
    @FXML
    private JFXTextArea descripcion;
    @FXML
    private JFXComboBox<String> empresa;
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
    private Lista<String> _titulos;
    private Lista<String> _habilidades;
    private ContextMenu menu;
    private Label temp;
}
