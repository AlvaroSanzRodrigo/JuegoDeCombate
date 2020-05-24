package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    @FXML
    private Label playerName;

    @FXML
    private Label enemyName;

    @FXML
    private Label playerWeapon;

    @FXML
    private Label enemyWeapon;

    @FXML
    private ProgressBar enemyLife;

    @FXML
    private ProgressBar playerLife;


    // Add a public no-args constructor
    public Controller()
    {
    }

    @FXML
    private void initialize()
    {
        // Inicializamos la batalla en la interfaz grafica
        GameController.getInstance().setPlayer(new Character("Sanzius",100f, 20f, 30F, new ArrayList<Weapon>(), new Weapon("Basic Sword", 5f, 2f), TurnState.ACTIVE, ""));
        GameController.getInstance().setEnemy(new Character("Ingieneria", 100f, 20f, 30F, new ArrayList<Weapon>(), new Weapon("Basic Sword", 5f, 2f), TurnState.WAITING, ""));
        playerName.setText(GameController.getInstance().getPlayer().getName());
        enemyName.setText(GameController.getInstance().getEnemy().getName());
        playerWeapon.setText(GameController.getInstance().getPlayer().getCurrentWeapon().getName());
        enemyWeapon.setText(GameController.getInstance().getEnemy().getCurrentWeapon().getName());
    }

    @FXML
    private void attackButton()
    {
        // Cuando se pulsa atacar actualizamos la estrategia del personaje, la ejecutamos y colocamos al personaje en el estado de espera
        GameController.getInstance().setActionStrategy(new AttackAction());
        GameController.getInstance().playerAction();
        GameController.getInstance().getPlayer().setTurnState(TurnState.WAITING);
        // TODO: ACCION DEL ENEMIGO
        // TODO: RESTAURACION DE VARIABLES
        // actualizamos la interfaz grafica con lo ocurrido
        refreshScene();
    }

    private void refreshScene(){
        // actualizamos las barras de vida
        enemyLife.setProgress(GameController.getInstance().getEnemy().getLife() / 100);
        playerLife.setProgress(GameController.getInstance().getPlayer().getLife() / 100);
        // TODO: ACTUALIZAR ARMAS
    }

    @FXML
    private void chooseWeaponButton() throws Exception {
        // TODO: SELECCION DE ARMAS MEDIANTE LISTA AUTORELLENABLE
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("weaponChooser.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, 300, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
