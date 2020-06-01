package sample;

public class DefensiveEnemyIA implements EnemyIATemplate {

    Character enemy;
    Character player;

    @Override
    public ActionStrategy getActionStrategy() {
        if (enemy.getLife() <= 45 && player.getLife() <= 25){
            return new WaitAction();
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

    @Override
    public void setEnemy(Character enemy) {
        this.enemy = enemy;
    }

    @Override
    public void setPlayer(Character player) {
        this.player = player;
    }
}
