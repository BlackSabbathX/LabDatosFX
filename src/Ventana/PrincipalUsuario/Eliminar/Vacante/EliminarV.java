package Ventana.PrincipalUsuario.Eliminar.Vacante;

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

public class EliminarV implements Initializable {

    public static Stage eliminar;
    public static EliminarV controlador;

    public static void toogleVisible() {
        if (eliminar.isShowing()) {
            eliminar.hide();
        } else {
            if (Vacante.getItemCount() == 0) return;
            controlador.setItem(0);
            eliminar.show();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vacante.setOnAction(event -> {
            Vacante v = Vacante.getVacanteAt(vacante.getSelectionModel().getSelectedIndex());
            if (v == null) return;
            nombre.setText(v.getNombre());
            empresa.setText(v.getEmpresa().getNombre());
        });
        vacante.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
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

    public void loadVacantes() {
        vacante.getItems().clear();
        Vacante.getVacantes().forEach(vac -> vacante.getItems().add(vac.getNombre()));
    }

    public void setItem(int n) {
        if (Vacante.getItemCount() == 0) return;
        if (vacante.getItems().size() > n) {
            vacante.getSelectionModel().select(n);
            vacante.getOnAction().handle(null);
        }
    }

    @FXML
    public void cancelar() {
        toogleVisible();
    }

    @FXML
    public void editar() {
        Vacante.removeAt(vacante.getSelectionModel().getSelectedIndex());
        Vacante.save(content);
        Vacante.load(content);
        toogleVisible();
    }

    @FXML
    private JFXTextField nombre;
    @FXML
    private JFXComboBox<String> vacante;
    @FXML
    private JFXTextField empresa;
    @FXML
    private StackPane content;
}
