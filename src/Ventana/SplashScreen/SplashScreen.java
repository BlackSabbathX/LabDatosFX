package Ventana.SplashScreen;

import Ventana.Login.Login;
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
