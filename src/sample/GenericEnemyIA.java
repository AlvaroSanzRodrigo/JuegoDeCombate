package sample;

public class GenericEnemyIA implements EnemyIATemplate {

    Character enemy;
    Character player;

    @Override
    public ActionStrategy getActionStrategy() {
        if (enemy.getLife() <= 45 && player.getLife() <= 25){
            return new AttackAction();
        }else if (enemy.getLife() >= 45){
            return new AttackAction();
        }else if(enemy.getLife() <= 45 && player.getLife() >= 25){
            return new WaitAction();
        }else if(enemy.getLife() <= 35){
            return new DefenseAction();
        }else {
            return new DefenseAction();
        }
    }
}
