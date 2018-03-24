import Ventana.Login.Login;
import Ventana.PrincipalEvaluador.PrincipalEvaluador;
import Ventana.PrincipalUsuario.Agregar.Aspirante.AgregarA;
import Ventana.PrincipalUsuario.Agregar.Empresa.AgregarE;
import Ventana.PrincipalUsuario.Agregar.Vacante.AgregarV;
import Ventana.PrincipalUsuario.Editar.Empresa.EditarE;
import Ventana.PrincipalUsuario.Eliminar.Empresa.EliminarE;
import Ventana.PrincipalUsuario.Eliminar.Vacante.EliminarV;
import Ventana.PrincipalUsuario.PrincipalUsuario;
import Ventana.Registro.Registro;
import Ventana.SplashScreen.SplashScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainClass extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loaderSS = new FXMLLoader(getClass().getResource("/Ventana/SplashScreen/SplashScreen.fxml"));

        Parent rootSS = loaderSS.load();
        Scene sceneSS = new Scene(rootSS);
        SplashScreen.splashScreen = new Stage(StageStyle.UNDECORATED);
        SplashScreen.splashScreen.setScene(sceneSS);
        SplashScreen.controlador = loaderSS.getController();

        FXMLLoader loaderLG = new FXMLLoader(getClass().getResource("/Ventana/Login/Login.fxml"));
        FXMLLoader loaderPU = new FXMLLoader(getClass().getResource("/Ventana/PrincipalUsuario/PrincipalUsuario.fxml"));
        FXMLLoader loaderPE = new FXMLLoader(getClass().getResource("/Ventana/PrincipalEvaluador/PrincipalEvaluador.fxml"));
        FXMLLoader loaderRE = new FXMLLoader(getClass().getResource("/Ventana/Registro/Registro.fxml"));
        FXMLLoader loaderAE = new FXMLLoader(getClass().getResource("/Ventana/PrincipalUsuario/Agregar/Empresa/AgregarE.fxml"));
        FXMLLoader loaderEE = new FXMLLoader(getClass().getResource("/Ventana/PrincipalUsuario/Editar/Empresa/EditarE.fxml"));
        FXMLLoader loaderRME = new FXMLLoader(getClass().getResource("/Ventana/PrincipalUsuario/Eliminar/Empresa/EliminarE.fxml"));
        FXMLLoader loaderAV = new FXMLLoader(getClass().getResource("/Ventana/PrincipalUsuario/Agregar/Vacante/AgregarV.fxml"));
        FXMLLoader loaderRMV = new FXMLLoader(getClass().getResource("/Ventana/PrincipalUsuario/Eliminar/Vacante/EliminarV.fxml"));
        FXMLLoader loaderAA = new FXMLLoader(getClass().getResource("/Ventana/PrincipalUsuario/Agregar/Aspirante/AgregarA.fxml"));

        Parent rootLG = loaderLG.load();
        Parent rootPU = loaderPU.load();
        Parent rootPE = loaderPE.load();
        Parent rootRE = loaderRE.load();
        Parent rootAE = loaderAE.load();
        Parent rootEE = loaderEE.load();
        Parent rootRME = loaderRME.load();
        Parent rootAV = loaderAV.load();
        Parent rootRMV = loaderRMV.load();
        Parent rootAA = loaderAA.load();

        Scene sceneLG = new Scene(rootLG);
        Scene scenePU = new Scene(rootPU);
        Scene scenePE = new Scene(rootPE);
        Scene sceneRE = new Scene(rootRE);
        Scene sceneAE = new Scene(rootAE);
        Scene sceneEE = new Scene(rootEE);
        Scene sceneRME = new Scene(rootRME);
        Scene sceneAV = new Scene(rootAV);
        Scene sceneRMV = new Scene(rootRMV);
        Scene sceneAA = new Scene(rootAA);

        Login.login = new Stage(StageStyle.UNDECORATED);
        PrincipalUsuario.usuario = new Stage(StageStyle.UNDECORATED);
        PrincipalEvaluador.evaluador = new Stage(StageStyle.UNDECORATED);
        Registro.registro = new Stage(StageStyle.UNDECORATED);
        AgregarE.agregar = new Stage(StageStyle.UNDECORATED);
        EditarE.editar = new Stage(StageStyle.UNDECORATED);
        EliminarE.eliminar = new Stage(StageStyle.UNDECORATED);
        AgregarV.agregar = new Stage(StageStyle.UNDECORATED);
        EliminarV.eliminar = new Stage(StageStyle.UNDECORATED);
        AgregarA.agregar = new Stage(StageStyle.UNDECORATED);

        Login.login.setScene(sceneLG);
        PrincipalUsuario.usuario.setScene(scenePU);
        PrincipalEvaluador.evaluador.setScene(scenePE);
        Registro.registro.setScene(sceneRE);
        AgregarE.agregar.setScene(sceneAE);
        EditarE.editar.setScene(sceneEE);
        EliminarE.eliminar.setScene(sceneRME);
        AgregarV.agregar.setScene(sceneAV);
        EliminarV.eliminar.setScene(sceneRMV);
        AgregarA.agregar.setScene(sceneAA);

        Login.controlador = loaderLG.getController();
        PrincipalUsuario.controlador = loaderPU.getController();
        PrincipalEvaluador.controlador = loaderPE.getController();
        Registro.controlador = loaderRE.getController();
        AgregarE.controlador = loaderAE.getController();
        EditarE.controlador = loaderEE.getController();
        EliminarE.controlador = loaderRME.getController();
        AgregarV.controlador = loaderAV.getController();
        EliminarV.controlador = loaderRMV.getController();
        AgregarA.controlador = loaderAA.getController();

        PrincipalUsuario.usuario.focusedProperty().addListener((observable, ov, nv) -> {
            if (nv && AgregarA.agregar.isShowing()){
                AgregarA.toogleVisible();
            }
        });
        AgregarE.agregar.focusedProperty().addListener((observable, ov, nv) -> {
            if (!nv) {
                AgregarE.toogleVisible();
            }
        });
        EditarE.editar.focusedProperty().addListener((observable, ov, nv) -> {
            if (!nv) {
                EditarE.toogleVisible();
            }
        });
        EliminarE.eliminar.focusedProperty().addListener((observable, ov, nv) -> {
            if (!nv) {
                EliminarE.toogleVisible();
            }
        });
        AgregarV.agregar.focusedProperty().addListener((observable, ov, nv) -> {
            if (!nv) {
                AgregarV.toogleVisible();
            }
        });
        EliminarV.eliminar.focusedProperty().addListener((observable, ov, nv) -> {
            if (!nv) {
                EliminarV.toogleVisible();
            }
        });

        SplashScreen.toogleVisible();
        SplashScreen.controlador.startApp();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
