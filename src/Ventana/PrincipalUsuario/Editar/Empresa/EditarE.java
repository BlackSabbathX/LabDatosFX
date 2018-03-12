package Ventana.PrincipalUsuario.Editar.Empresa;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

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

    }
}
