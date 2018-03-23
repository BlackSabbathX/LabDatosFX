package Ventana.SplashScreen;

import BaseDeDatos.Aspirante;
import BaseDeDatos.Empresa;
import BaseDeDatos.Usuario;
import BaseDeDatos.Vacante;
import Ventana.Login.Login;
import Ventana.PrincipalUsuario.PrincipalUsuario;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreen implements Initializable {

    public static Stage splashScreen;
    public static SplashScreen controlador;

    public static void toogleVisible() {
        if (splashScreen.isShowing()) {
            splashScreen.hide();
        } else {
            splashScreen.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void startApp() {
        Empresa.init(content);
        Vacante.init(content);
        Aspirante.init(content);
        Usuario.init(content);
        Empresa.load(content);
        Vacante.load(content);
        Aspirante.load(content);
        Usuario.load(content);
        PrincipalUsuario.loadAll();
        new Thread(() -> {
            try {
                Thread.sleep(4);
                Platform.runLater(() -> {
                    Login.toogleVisible();
                    toogleVisible();
                });
            } catch (InterruptedException ignored) {
            }
        }).start();
    }

    @FXML
    private StackPane content;

}
