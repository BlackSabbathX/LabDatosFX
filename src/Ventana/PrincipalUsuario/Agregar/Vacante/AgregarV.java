package Ventana.PrincipalUsuario.Agregar.Vacante;

import BaseDeDatos.Empresa;
import BaseDeDatos.Vacante;
import Ventana.PrincipalUsuario.PrincipalUsuario;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
            controlador.setItemE(0);
            controlador.jornada.getSelectionModel().select(0);
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
        jornada.getItems().addAll("Completa", "Parcial");
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
        ///////////
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
}
