package Ventana.PrincipalEvaluador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;
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
        load();
    }

    private void load() {
        XYChart.Series<String, Integer> s = new XYChart.Series<>();
        ObservableList<String> lista = FXCollections.observableArrayList();
        lista.addAll("Amor", "Desengaño", "Traición", "Cachos xD", "LOL", "Aló", "Hola");
        x.setCategories(lista);
        for (String string : lista) {
            s.getData().addAll(new XYChart.Data<>(string, new Random().nextInt(30)));
        }
        jj.getData().add(s);
    }

    @FXML
    private AreaChart<String, Integer> jj;
    @FXML
    private CategoryAxis x;

}
