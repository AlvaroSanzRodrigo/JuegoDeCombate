package sample;
// Estrategia de ataque
public class AttackAction implements ActionStrategy {
    @Override
    public void action(Character player, Character enemy) {
        //Hace la formula para calcular la vida restante del enemigo
        enemy.setLife(enemy.getLife() - ( player.getAttackPower() +  player.getCurrentWeapon().getAttack() )); ;
    }
}
