package Ventana.PrincipalUsuario.Eliminar.Empresa;

import BaseDeDatos.Empresa;
import BaseDeDatos.Vacante;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
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
            controlador.setItem(0);
            eliminar.show();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        empresa.setOnAction(event -> {
            Empresa e = Empresa.getEmpresaAt(empresa.getSelectionModel().getSelectedIndex());
            if (e == null) return;
            nombre.setText(e.getNombre());
            telefono.setText(e.getTelefono());
        });
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
    }

    public void loadEmpresas() {
        empresa.getItems().clear();
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
        Vacante.removeByEmpresa(Empresa.getEmpresaAt(empresa.getSelectionModel().getSelectedIndex()).getId());
        Empresa.removeAt(empresa.getSelectionModel().getSelectedIndex());
        Empresa.save(content);
        Empresa.load(content);
        Vacante.save(content);
        Vacante.load(content);
        toogleVisible();
    }

    @FXML
    private JFXTextField telefono;
    @FXML
    private JFXComboBox<String> empresa;
    @FXML
    private JFXTextField nombre;
    @FXML
    private StackPane content;
}
