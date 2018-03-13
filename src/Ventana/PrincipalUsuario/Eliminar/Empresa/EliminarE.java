package Ventana.PrincipalUsuario.Eliminar.Empresa;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EliminarE implements Initializable {

    public void setExit(Stage stage){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.exit();
    }
}
