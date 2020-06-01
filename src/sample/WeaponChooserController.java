package sample;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import sample.Models.Weapon;
import sample.Singleton.GameController;

import java.util.List;
import java.util.stream.Collectors;

public class WeaponChooserController {
    @FXML
    private ListView weaponList;

    @FXML
    public void initialize() {
        // Cargamos la lista de armas
        List<String> values = GameController.getInstance().getPlayer().getWeapons().stream().map(Weapon::getName).collect(Collectors.toList());
        weaponList.setItems(FXCollections.observableList(values));

        weaponList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                // asignamos el arma al jugador
                GameController.getInstance().getPlayer().setCurrentWeapon(
                        (GameController.getInstance()
                        .getPlayer()
                        .getWeapons()
                        .stream()
                        .filter(weapon -> weaponList
                                .getSelectionModel()
                                .getSelectedItem()
                                .toString().equals(weapon.getName()))
                        .findFirst()
                        .orElse(null)));
                
            }
        });

    }

}
