package Ventana.SplashScreen;

import java.net.URL;
import java.util.ResourceBundle;

import Ventana.Dialog;
import Ventana.Login.Login;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

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
        new Splash().start();
    }

    class Splash extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(40);
                Platform.runLater(() -> {
                    Login.toogleVisible();
                    toogleVisible();
                });
            } catch (InterruptedException e) {
            }
        }
    }

}
