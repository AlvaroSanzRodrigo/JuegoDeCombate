package sample.Strategy;

import sample.Models.Character;

// Interfaz para la estrategia de acción
public interface ActionStrategy {
    public void action(Character player, Character enemy);
}
