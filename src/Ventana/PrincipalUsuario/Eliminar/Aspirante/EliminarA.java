package Ventana.PrincipalUsuario.Eliminar.Aspirante;

import BaseDeDatos.Aspirante;
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

public class EliminarA implements Initializable {

    public static Stage eliminar;
    public static EliminarA controlador;

    public static void toogleVisible() {
        if (eliminar.isShowing()) {
            eliminar.hide();
        } else {
            if (Aspirante.getItemCount() == 0) return;
            controlador.setItem(0);
            eliminar.show();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        aspirante.setOnAction(event -> {
            Aspirante v = Aspirante.getAspiranteAt(aspirante.getSelectionModel().getSelectedIndex());
            if (v == null) return;
            nombre.setText(v.getNombre());
            email.setText(v.getEmail());
        });
        aspirante.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
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

    public void loadAspirantes() {
        aspirante.getItems().clear();
        Aspirante.getAspirantes().forEach(asp -> aspirante.getItems().add(asp.getNombre()));
    }

    public void setItem(int n) {
        if (Aspirante.getItemCount() == 0) return;
        if (aspirante.getItems().size() > n) {
            aspirante.getSelectionModel().select(n);
            aspirante.getOnAction().handle(null);
        }
    }

    @FXML
    public void cancelar() {
        toogleVisible();
    }

    @FXML
    public void editar() {
        Aspirante.removeAt(aspirante.getSelectionModel().getSelectedIndex());
        Aspirante.save(content);
        Aspirante.load(content);
        toogleVisible();
    }

    @FXML
    private JFXTextField nombre;
    @FXML
    private JFXComboBox<String> aspirante;
    @FXML
    private JFXTextField email;
    @FXML
    private StackPane content;
}
