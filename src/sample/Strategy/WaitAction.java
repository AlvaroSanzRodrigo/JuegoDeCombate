package sample.Strategy;

import sample.Models.Character;

// Estrategia de espera
public class WaitAction implements ActionStrategy {
    @Override
    public void action(Character player, Character enemy) {
        //Se añade a la vida del jugador un pequeño extra por esperar
        player.setLife(player.getLife() + 10);
    }
}
