package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;
import java.util.stream.Collectors;

public class WeaponChooserController {
    @FXML
    private ListView weaponList;

    @FXML
    public void initialize() {
        // change next line to DB load
        List<String> values = GameController.getInstance().getPlayer().getWeapons().stream().map(Weapon::getName).collect(Collectors.toList());
        weaponList.setItems(FXCollections.observableList(values));

    }

}
