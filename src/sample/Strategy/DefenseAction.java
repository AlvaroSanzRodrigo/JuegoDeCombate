package sample.Strategy;

import sample.Models.Character;

// Estrategia de defensa
public class DefenseAction implements ActionStrategy {
    @Override
    public void action(Character player, Character enemy) {
        // aumentamos la defensa del personaje segun el arma y un extra, debe de volver a su estado inicial cuando termina el turno
        player.setDefensePower(player.getDefensePower() + player.getCurrentWeapon().getDefense() + 30);
    }
}
