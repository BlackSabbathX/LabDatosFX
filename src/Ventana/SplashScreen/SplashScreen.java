package Ventana.SplashScreen;

import BaseDeDatos.Aspirante;
import BaseDeDatos.Empresa;
import BaseDeDatos.Usuario;
import BaseDeDatos.Vacante;
import Ventana.Login.Login;
import Ventana.PrincipalUsuario.Editar.Empresa.EditarE;
import Ventana.PrincipalUsuario.PrincipalUsuario;
import javafx.application.Platform;
import javafx.fxml.Initializable;
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
        new Thread(() -> {
            Empresa.init();
            Vacante.init();
            Aspirante.init();
            Usuario.init();
            Empresa.load();
            Vacante.load();
            Aspirante.load();
            Usuario.load();
            PrincipalUsuario.loadAll();
            try {
                Thread.sleep(4000);
                Platform.runLater(() -> {
                    EditarE.controlador.loadEmpresa();
                    EditarE.controlador.setItem(0);
                    Login.toogleVisible();
                    toogleVisible();
                });
            } catch (InterruptedException ignored) {
            }
        }).start();
    }

}
