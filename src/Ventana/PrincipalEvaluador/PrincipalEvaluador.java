package Ventana.PrincipalEvaluador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

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
