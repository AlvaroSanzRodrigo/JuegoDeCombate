package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.AbstractFactory.AbstractWeaponFactoryManager;
import sample.Models.Character;
import sample.Models.Weapon;
import sample.Singleton.GameController;
import sample.State.TurnState;
import sample.Strategy.AttackAction;
import sample.Strategy.DefenseAction;
import sample.Strategy.WaitAction;

import java.util.ArrayList;
import java.util.Optional;

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
        GameController.getInstance().setPlayer(new Character("Sanzius",100f, 45F, 30F, new ArrayList<Weapon>(), new Weapon("Basic Sword", 10f, 5f), TurnState.ACTIVE, ""));
        // Lanzamos uin dialog para pedir el nombre!
        TextInputDialog dialog = new TextInputDialog("Nombre");
        dialog.setTitle("Introduce tu nombre");
        dialog.setHeaderText("Bienvenido!");
        dialog.setContentText("Por favor, introduce tu nombre:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> GameController.getInstance().getPlayer().setName(name));

        // Creamos un enemigo
        GameController.getInstance().getEnemyFromFactory();
        //recolocamos la escena
        refreshScene();
    }

    @FXML
    private void attackButton() {
        // Cuando se pulsa atacar actualizamos la estrategia del personaje, la ejecutamos y colocamos al personaje en el estado de espera
        GameController.getInstance().setPlayerActionStrategy(new AttackAction());
        gameAction();
    }

    @FXML
    private void defenseButton(){
        GameController.getInstance().setPlayerActionStrategy(new DefenseAction());
        this.gameAction();
    }

    @FXML
    private void waitButton(){
        // implement wait strategy, easy peasy lemon squezzy
        GameController.getInstance().setPlayerActionStrategy(new WaitAction());
        gameAction();
    }

    private  void gameAction(){
        GameController.getInstance().playerAction();
        GameController.getInstance().getPlayer().setTurnState(TurnState.WAITING);
        // ACCION DEL ENEMIGO
        GameController.getInstance().getEnemy().setTurnState(TurnState.ACTIVE);
        GameController.getInstance().setEnemyActionStrategy(GameController.getInstance().getEnemyIA().getActionStrategy());
        GameController.getInstance().enemyAction();
        GameController.getInstance().getEnemy().setTurnState(TurnState.WAITING);
        GameController.getInstance().getPlayer().setTurnState(TurnState.ACTIVE);
        // RESTAURACION DE VARIABLES
        refreshScene();
        battleStatusChecker();
        // actualizamos la interfaz grafica con lo ocurrido
        resetStats();
    }

    private void refreshScene(){
        // actualizamos las barras de vida
        enemyLife.setProgress(GameController.getInstance().getEnemy().getLife() / 100);
        playerLife.setProgress(GameController.getInstance().getPlayer().getLife() / 100);
        // actualizamos los nombres
        playerName.setText(GameController.getInstance().getPlayer().getName());
        enemyName.setText(GameController.getInstance().getEnemy().getName());
        // actulizamos las armas
        playerWeapon.setText(GameController.getInstance().getPlayer().getCurrentWeapon().getName());
        enemyWeapon.setText(GameController.getInstance().getEnemy().getCurrentWeapon().getName());
        // TODO: ACTUALIZAR ARMAS
    }

    private void resetStats(){
        //  Resetear las estadisticas alteradas del jugador y del enemigo
        if (GameController.getInstance().getPlayerActionStrategy() instanceof DefenseAction){
           GameController.getInstance().getPlayer().setDefensePower(GameController.getInstance().getPlayer().getDefensePower() - GameController.getInstance().getPlayer().getCurrentWeapon().getDefense() - 30);
        } else if (GameController.getInstance().getEnemyActionStrategy() instanceof DefenseAction) {
            GameController.getInstance().getPlayer().setDefensePower(GameController.getInstance().getEnemy().getDefensePower() - GameController.getInstance().getEnemy().getCurrentWeapon().getDefense() - 30);
        }
    }

    private void battleStatusChecker(){
        if (GameController.getInstance().getEnemy().getLife() <= 0){
            GameController.getInstance().getEnemyFromFactory();
            GameController.getInstance().getPlayer().setLife(100F);
            // TODO: Get a new weapon and win message
            Weapon weapon = AbstractWeaponFactoryManager.getInstance().createWeapon();
            GameController.getInstance().getPlayer().getWeapons().add(weapon);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You Win");
            alert.setHeaderText("Congratulations and you got a new weapon");
            alert.setContentText("Weapon name: " + weapon.getName() + "\n Weapon attack: " + weapon.getAttack() + " \n Weapon Defense: " + weapon.getDefense());
            alert.showAndWait();
        }

        if (GameController.getInstance().getPlayer().getLife() <= 0){
            // TODO: Get a message of game over and close the app
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("GAME OVER");
            alert.setHeaderText("You loose your battle...");
            alert.setContentText("be braver next time!");
            alert.showAndWait();
            System.exit(0);
        }

        refreshScene();
    }

    @FXML
    private void chooseWeaponButton() throws Exception {
        // SELECCION DE ARMAS MEDIANTE LISTA AUTORELLENABLE
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("weaponChooser.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, 300, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        refreshScene();
    }
}
