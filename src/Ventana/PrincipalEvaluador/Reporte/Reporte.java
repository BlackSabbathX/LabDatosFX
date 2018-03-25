package Ventana.PrincipalEvaluador.Reporte;

import BaseDeDatos.Aspirante;
import BaseDeDatos.Vacante;
import Estructura.HabilidadNivel;
import Estructura.Titulo;
import Ventana.Dialog;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Reporte implements Initializable {

    public static Stage reporte;
    public static Reporte controlador;
    private final String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    @FXML
    private JFXTextArea descripcion;
    @FXML
    private StackPane content;

    public static void toogleVisible() {
        if (reporte.isShowing()) {
            reporte.hide();
        } else {
            reporte.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setText(int posV) {
        Vacante v = Vacante.getVacanteAt(posV);
        Calendar hoy = Calendar.getInstance();
        assert v != null;
        StringBuilder lista = new StringBuilder();
        for (Aspirante a : v.getEmparejados()) {
            lista.append("Nombre: ").append(a.getNombre()).append("\n")
                    .append("Email: ").append(a.getEmail()).append("\n")
                    .append("Telefono: ").append(a.getTelefono()).append("\n")
                    .append("Titulos:").append("\n");
            for (Titulo t : a.getTitulos()) {
                lista.append("\t").append(t.toString()).append("\n");
            }
            lista.append("Habilidades:").append("\n");
            for (HabilidadNivel h : a.getHabilidades()) {
                lista.append("\t").append(h.getText()).append(", nivel: ").append(h.getNivel()).append("\n");
            }
            lista.append("\n");
        }
        String text = "" +
                "HUMAN-WARE\n" +
                "Empresa para la gestión oportuna de empleos.\n\n" +
                "Barranquilla, " + hoy.get(Calendar.DAY_OF_MONTH) + " de " + months[hoy.get(Calendar.MONTH)] + " del " + hoy.get(Calendar.YEAR) + "\n\n" +
                "Señores " + v.getEmpresa().getNombre() + "\n" +
                "Reporte final de selección de empleados.\n\n" +
                "Anexamos una lista e información de empleados seleccionados como posibles aspirantes al puesto de " + v.getNombre() + " con información de contacto:\n\n" +
                lista +
                "\nGracias por usar nuestros sistemas de información.";


        descripcion.setText(text);
    }

    @FXML
    public void cancelar() {
        descripcion.setText("");
        toogleVisible();
    }

    @FXML
    public void copiar() {
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection ss = new StringSelection(descripcion.getText());
        cb.setContents(ss, ss);
        Dialog.showSimpleDialog(content, "Okay", "Copiado exitosamente!", "Aceptar");
    }
}
