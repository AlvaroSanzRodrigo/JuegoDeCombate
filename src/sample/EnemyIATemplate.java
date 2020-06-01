package sample;

public interface EnemyIATemplate {
    public ActionStrategy getActionStrategy();
    public void setEnemy(Character enemy);
    public void setPlayer(Character player);
}
