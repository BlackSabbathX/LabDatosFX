package Ventana.PrincipalUsuario.Editar.Empresa;

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

public class EditarE implements Initializable {

    public static Stage editar;
    public static EditarE controlador;

    public static void toogleVisible() {
        if (editar.isShowing()) {
            editar.hide();
        } else {
            editar.show();
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
        empresa.getItems().add("Hola");
        empresa.getItems().add("Chao");
    }

    @FXML
    private JFXTextField telefono;

    @FXML
    private JFXComboBox<String> empresa;

    @FXML
    private JFXTextField nombre;

    public void cancelar() {
    }

    public void agregar() {
    }
}
