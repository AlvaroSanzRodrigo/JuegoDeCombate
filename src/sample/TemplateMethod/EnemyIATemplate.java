package sample.TemplateMethod;

import sample.Models.Character;
import sample.Strategy.ActionStrategy;

public interface EnemyIATemplate {
    public ActionStrategy getActionStrategy();
    public void setEnemy(Character enemy);
    public void setPlayer(Character player);
}
