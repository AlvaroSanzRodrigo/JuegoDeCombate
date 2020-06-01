package sample.Strategy;

import sample.Models.Character;

// Estrategia de ataque
public class AttackAction implements ActionStrategy {
    @Override
    public void action(Character player, Character enemy) {
        //Hace la formula para calcular la vida restante del enemigo
        float actualDamage = enemy.getDefensePower() - ( player.getAttackPower() +  player.getCurrentWeapon().getAttack());
        if (actualDamage <= 0)
            enemy.setLife(( enemy.getLife() + actualDamage )); ;
    }
}
