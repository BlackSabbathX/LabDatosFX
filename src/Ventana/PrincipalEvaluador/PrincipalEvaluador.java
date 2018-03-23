package Ventana.PrincipalEvaluador;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalEvaluador implements Initializable {

    public static Stage evaluador;
    public static PrincipalEvaluador controlador;

    public static void toogleVisible() {
        if (evaluador.isShowing()) {
            evaluador.hide();
        } else {
            evaluador.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
