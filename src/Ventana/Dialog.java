package Ventana;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class Dialog {

    private static boolean okay;

    public static boolean showConfirmationDialog(StackPane padre, String title, String content, String ok, String _cerrar) {
        okay = true;
        JFXDialogLayout layout = new JFXDialogLayout();
        JFXButton aceptar = new JFXButton(ok);
        JFXButton cerrar = new JFXButton(_cerrar);
        aceptar.setButtonType(JFXButton.ButtonType.FLAT);
        cerrar.setButtonType(JFXButton.ButtonType.FLAT);
        aceptar.setStyle("" +
                "-fx-background-color: #eeeeee;" +
                "-fx-text-fill: #111111");
        cerrar.setStyle("" +
                "-fx-background-color: #eeeeee;" +
                "-fx-text-fill: #111111");
        layout.setHeading(new Text(title));
        layout.setBody(new Text(content));
        layout.setActions(aceptar, cerrar);
        JFXDialog dialog = new JFXDialog(padre, layout, JFXDialog.DialogTransition.CENTER);
        cerrar.setOnAction(event -> {
            okay = false;
            dialog.close();
        });
        aceptar.setOnAction(event -> {
            okay = true;
            dialog.close();
        });
        dialog.show();
        return okay;
    }

    public static void showSimpleDialog(StackPane padre, String title, String content, String ok) {
        JFXDialogLayout layout = new JFXDialogLayout();
        JFXButton aceptar = new JFXButton(ok);
        aceptar.setButtonType(JFXButton.ButtonType.FLAT);
        aceptar.setStyle("" +
                "-fx-background-color: #eeeeee;" +
                "-fx-text-fill: #111111");
        layout.setHeading(new Text(title));
        layout.setBody(new Text(content));
        layout.setActions(aceptar);
        JFXDialog dialog = new JFXDialog(padre, layout, JFXDialog.DialogTransition.CENTER);
        aceptar.setOnAction(event -> dialog.close());
        dialog.show();
    }

}
