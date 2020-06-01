package sample.TemplateMethod;

import sample.Models.Character;
import sample.Strategy.ActionStrategy;
import sample.Strategy.AttackAction;
import sample.Strategy.DefenseAction;
import sample.Strategy.WaitAction;
import sample.TemplateMethod.EnemyIATemplate;

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


    @Override
    public void setEnemy(Character enemy) {
        this.enemy = enemy;
    }

    @Override
    public void setPlayer(Character player) {
        this.player = player;
    }
}
