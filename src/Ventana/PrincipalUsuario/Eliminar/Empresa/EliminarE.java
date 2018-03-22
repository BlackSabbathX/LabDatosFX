package Ventana.PrincipalUsuario.Eliminar.Empresa;

import BaseDeDatos.Empresa;
import Ventana.PrincipalUsuario.PrincipalUsuario;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class EliminarE implements Initializable {

    public static Stage eliminar;
    public static EliminarE controlador;

    public static void toogleVisible() {
        if (eliminar.isShowing()) {
            eliminar.hide();
        } else {
            if (Empresa.getItemCount() == 0) return;
            eliminar.show();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        empresa.setOnAction(event -> {
            Empresa e = Empresa.getEmpresaAt(empresa.getSelectionModel().getSelectedIndex());
            nombre.setText(e.getNombre());
            telefono.setText(e.getTelefono());
        });
    }

    public void loadEmpresa() {
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
        Empresa.getEmpresas().forEach(emp -> empresa.getItems().add(emp.getNombre()));
    }

    public void setItem(int n) {
        if (Empresa.getItemCount() == 0) return;
        if (empresa.getItems().size() > n) {
            empresa.getSelectionModel().select(n);
            empresa.getOnAction().handle(null);
        }
    }

    @FXML
    public void cancelar() {
        toogleVisible();
    }

    @FXML
    public void editar() {
        Empresa.removeAt(empresa.getSelectionModel().getSelectedIndex());
        Empresa.save();
        Empresa.load();
        PrincipalUsuario.controlador.loadEmpresas();
        toogleVisible();
    }

    @FXML
    private JFXTextField telefono;
    @FXML
    private JFXComboBox<String> empresa;
    @FXML
    private JFXTextField nombre;
}
