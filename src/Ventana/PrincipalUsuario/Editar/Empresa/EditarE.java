package Ventana.PrincipalUsuario.Editar.Empresa;

import BaseDeDatos.Empresa;
import Ventana.Dialog;
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

public class EditarE implements Initializable {

    public static Stage editar;
    public static EditarE controlador;
    @FXML
    private JFXTextField telefono;
    @FXML
    private JFXComboBox<String> empresa;
    @FXML
    private JFXTextField nombre;
    @FXML
    private StackPane content;

    public static void toogleVisible() {
        if (editar.isShowing()) {
            editar.hide();
        } else {
            if (Empresa.getItemCount() == 0) return;
            controlador.setItem(0);
            editar.show();
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
        if (telefono.getText().trim().equals("")) {
            Dialog.showSimpleDialog(content, "Complete", "Datos incompletos.", "Aceptar");
            return;
        }
        Empresa edit = Empresa.getEmpresaAt(empresa.getSelectionModel().getSelectedIndex());
        if (edit == null) return;
        edit.setNombre(nombre.getText().trim());
        edit.setTelefono(telefono.getText().trim());
        Empresa.save(content);
        Empresa.load(content);
        toogleVisible();
        loadEmpresas();
    }
}
