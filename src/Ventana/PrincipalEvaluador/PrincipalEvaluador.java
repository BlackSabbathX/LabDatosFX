package Ventana.PrincipalEvaluador;

import BaseDeDatos.Aspirante;
import BaseDeDatos.Emparejador;
import BaseDeDatos.Vacante;
import Estructura.HabilidadNivel;
import Estructura.Lista;
import Estructura.Titulo;
import Ventana.Login.Login;
import Ventana.PrincipalEvaluador.Reporte.Reporte;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalEvaluador implements Initializable {

    public static Stage evaluador;
    public static PrincipalEvaluador controlador;
    @FXML
    private JFXComboBox<String> vacante;
    @FXML
    private VBox numeros;
    @FXML
    private VBox titulos;
    @FXML
    private VBox ides;
    @FXML
    private VBox nombres;
    @FXML
    private VBox habilidades;
    private int posV;

    public static void toogleVisible() {
        if (evaluador.isShowing()) {
            evaluador.hide();
        } else {
            controlador.reset();
            evaluador.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        posV = 0;
        vacante.setOnAction(e -> mostrarParejas());
        vacante.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
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

    public void reset() {
        for (Vacante v : Vacante.getVacantes()) {
            v.setEmparejados(new Lista<>());
        }
        vacante.getSelectionModel().select("Todo");
    }

    public void loadVacantes() {
        vacante.getItems().clear();
        vacante.getItems().add("Todo");
        Vacante.getVacantes().forEach(vac -> vacante.getItems().add(vac.getNombre() + " - " + vac.getEmpresa().getNombre()));
    }

    @FXML
    void generar() {
        if (vacante.getSelectionModel().getSelectedIndex() == 0) {
            for (Vacante v : Vacante.getVacantes()) {
                v.setEmparejados(Emparejador.lista(v));
            }
        } else {
            Vacante v = Vacante.getVacanteAt(vacante.getSelectionModel().getSelectedIndex() - 1);
            assert v != null;
            v.setEmparejados(Emparejador.lista(v));
            mostrarParejas();
        }
    }

    private void mostrarParejas() {
        numeros.getChildren().clear();
        ides.getChildren().clear();
        titulos.getChildren().clear();
        habilidades.getChildren().clear();
        nombres.getChildren().clear();

        if (vacante.getSelectionModel().getSelectedIndex() == 0) return;
        Vacante v = Vacante.getVacanteAt(vacante.getSelectionModel().getSelectedIndex() - 1);

        boolean par = true;
        Color color1 = Color.BLACK;
        Color color2 = Color.rgb(77, 44, 77);
        int i = 1;

        assert v != null;
        for (Aspirante aspirante : v.getEmparejados()) {
            Label numero = new Label(String.valueOf(i));
            Label id = new Label(String.valueOf(aspirante.getId()));
            Label nombre = new Label(aspirante.getNombre());

            i++;
            final int pos = aspirante.getPos();

            numero.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posV = pos;
//                    menuE.show(numero, e.getScreenX(), e.getScreenY());
                }
            });

            id.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posV = pos;
//                    menuE.show(id, e.getScreenX(), e.getScreenY());
                }
            });

            nombre.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    posV = pos;
//                    menuE.show(nombre, e.getScreenX(), e.getScreenY());
                }
            });

            Font font = new Font("Segoe UI", 15);
            numero.setFont(font);
            id.setFont(font);
            nombre.setFont(font);

            if (par) {
                numero.setTextFill(color1);
                id.setTextFill(color1);
                nombre.setTextFill(color1);
            } else {
                numero.setTextFill(color2);
                id.setTextFill(color2);
                nombre.setTextFill(color2);
            }

            numeros.getChildren().add(numero);
            ides.getChildren().add(id);
            nombres.getChildren().add(nombre);

            Lista<Titulo> tit = aspirante.getTitulos();
            Lista<HabilidadNivel> hab = aspirante.getHabilidades();

            for (Titulo t : tit) {
                Label titulo = new Label();

                titulo.setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.SECONDARY) {
                        posV = pos;
//                    menuE.show(titulo, e.getScreenX(), e.getScreenY());
                    }
                });

                if (par) titulo.setTextFill(color1);
                else titulo.setTextFill(color2);

                titulo.setFont(font);
                titulo.setText(t.toString());
                titulos.getChildren().add(titulo);
            }

            for (HabilidadNivel h : hab) {
                Label habilidad = new Label();

                habilidad.setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.SECONDARY) {
                        posV = pos;
//                    menuE.show(habilidad, e.getScreenX(), e.getScreenY());
                    }
                });

                if (par) habilidad.setTextFill(color1);
                else habilidad.setTextFill(color2);

                habilidad.setFont(font);
                habilidad.setText(h.habilidad.toString() + " " + h.getNivel());
                habilidades.getChildren().add(habilidad);
            }
            while (habilidades.getChildren().size() > titulos.getChildren().size()) {
                Label vacio = new Label("  ");
                vacio.setFont(font);
                titulos.getChildren().add(vacio);
            }
            while (habilidades.getChildren().size() < titulos.getChildren().size()) {
                Label vacio = new Label("  ");
                vacio.setFont(font);
                habilidades.getChildren().add(vacio);
            }
            while (habilidades.getChildren().size() > ides.getChildren().size()) {
                Label vacio = new Label("  ");
                Label vacio1 = new Label("  ");
                Label vacio2 = new Label("  ");

                vacio.setFont(font);
                vacio1.setFont(font);
                vacio2.setFont(font);

                numeros.getChildren().add(vacio);
                ides.getChildren().add(vacio1);
                nombres.getChildren().add(vacio2);
            }

            Label vacio = new Label("   ");
            Label vacio1 = new Label("   ");
            Label vacio2 = new Label("   ");
            Label vacio3 = new Label("   ");
            Label vacio4 = new Label("   ");

            vacio.setFont(font);
            vacio1.setFont(font);
            vacio2.setFont(font);
            vacio3.setFont(font);
            vacio4.setFont(font);

            numeros.getChildren().add(vacio);
            ides.getChildren().add(vacio1);
            titulos.getChildren().add(vacio2);
            habilidades.getChildren().add(vacio3);
            nombres.getChildren().add(vacio4);

            par = !par;
        }
    }

    @FXML
    void reporte() {
        Reporte.toogleVisible();
        Reporte.controlador.setText(vacante.getSelectionModel().getSelectedIndex() - 1);
    }

    @FXML
    void cerrarSesion() {
        Login.toogleVisible();
        toogleVisible();
    }

    @FXML
    void cerrarAplicacion() {
        toogleVisible();
    }
}
