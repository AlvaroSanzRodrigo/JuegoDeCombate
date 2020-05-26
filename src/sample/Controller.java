package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
        GameController.getInstance().setPlayer(new Character("Sanzius",100f, 60f, 30F, new ArrayList<Weapon>(), new Weapon("Basic Sword", 5f, 2f), TurnState.ACTIVE, ""));
        //GameController.getInstance().setEnemy(new Character("Ingieneria", 100f, 60f, 30F, new ArrayList<Weapon>(), new Weapon("Basic Sword", 5f, 2f), TurnState.WAITING, ""));
        GameController.getInstance().getEnemyFromFactory();
        //recolocamos la escena
        refreshScene();
    }

    @FXML
    private void attackButton() {
        // Cuando se pulsa atacar actualizamos la estrategia del personaje, la ejecutamos y colocamos al personaje en el estado de espera
        GameController.getInstance().setPlayerActionStrategy(new AttackAction());
        GameController.getInstance().playerAction();
        GameController.getInstance().getPlayer().setTurnState(TurnState.WAITING);
        // TODO: ACCION DEL ENEMIGO
        GameController.getInstance().getEnemy().setTurnState(TurnState.ACTIVE);
        GameController.getInstance().setEnemyActionStrategy(new AttackAction());
        GameController.getInstance().enemyAction();
        GameController.getInstance().getEnemy().setTurnState(TurnState.WAITING);
        GameController.getInstance().getPlayer().setTurnState(TurnState.ACTIVE);
        // TODO: RESTAURACION DE VARIABLES
        // actualizamos la interfaz grafica con lo ocurrido
        refreshScene();
        resetStats();
    }

    @FXML
    private void defenseButton(){
        GameController.getInstance().setPlayerActionStrategy(new DefenseAction());
        GameController.getInstance().playerAction();
        GameController.getInstance().getPlayer().setTurnState(TurnState.WAITING);
        // TODO: ACCION DEL ENEMIGO
 
        // TODO: RESTAURACION DE VARIABLES
        // actualizamos la interfaz grafica con lo ocurrido
        refreshScene();
        resetStats();
    }

    @FXML
    private void waitButton(){
        // TODO: implement wait strategy, easy peasy lemon squezzy
        GameController.getInstance().setPlayerActionStrategy(new WaitAction());
        GameController.getInstance().playerAction();
        GameController.getInstance().getPlayer().setTurnState(TurnState.WAITING);
        // TODO: ACCION DEL ENEMIGO

        // RESTAURACION DE VARIABLES
        refreshScene();
        // actualizamos la interfaz grafica con lo ocurrido
        resetStats();
    }

    private void refreshScene(){
        // actualizamos las barras de vida
        enemyLife.setProgress(GameController.getInstance().getEnemy().getLife() / 100);
        playerLife.setProgress(GameController.getInstance().getPlayer().getLife() / 100);
        playerName.setText(GameController.getInstance().getPlayer().getName());
        enemyName.setText(GameController.getInstance().getEnemy().getName());
        playerWeapon.setText(GameController.getInstance().getPlayer().getCurrentWeapon().getName());
        enemyWeapon.setText(GameController.getInstance().getEnemy().getCurrentWeapon().getName());
        // TODO: ACTUALIZAR ARMAS
    }

    private void resetStats(){
        // TODO: Resetear las estadisticas alteradas del jugador y del enemigo
        if (GameController.getInstance().getPlayerActionStrategy() instanceof DefenseAction){
           GameController.getInstance().getPlayer().setDefensePower(GameController.getInstance().getPlayer().getDefensePower() - GameController.getInstance().getPlayer().getCurrentWeapon().getDefense() - 30);
        } else if (GameController.getInstance().getEnemyActionStrategy() instanceof DefenseAction) {
            GameController.getInstance().getPlayer().setDefensePower(GameController.getInstance().getEnemy().getDefensePower() - GameController.getInstance().getEnemy().getCurrentWeapon().getDefense() - 30);
        }
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
