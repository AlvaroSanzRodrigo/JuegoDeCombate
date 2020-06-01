package sample;

import java.util.concurrent.ThreadLocalRandom;

// Controla el juego y calcula todos los posibles eventos (ataque, defensa...)
public class GameController {
    // Implementacion del patron Singleton
    private static final GameController mGameController = new GameController();
    private GameController(){}
    public static GameController getInstance(){
        return mGameController;
    }

    private Character enemy;
    private Character player;
    private ActionStrategy playerActionStrategy;
    private ActionStrategy enemyActionStrategy;
    private EnemyIATemplate enemyIA;

    public ActionStrategy getPlayerActionStrategy() {
        return playerActionStrategy;
    }

    public void setPlayerActionStrategy(ActionStrategy playerActionStrategy) {
        this.playerActionStrategy = playerActionStrategy;
    }

    public static GameController getmGameController() {
        return mGameController;
    }

    public Character getEnemy() {
        return enemy;
    }

    public void setEnemy(Character enemy) {
        this.enemy = enemy;
    }

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public ActionStrategy getEnemyActionStrategy() {
        return enemyActionStrategy;
    }

    public void setEnemyActionStrategy(ActionStrategy enemyActionStrategy) {
        this.enemyActionStrategy = enemyActionStrategy;
    }

    public void getEnemyFromFactory(){
       this.enemy = AbstractEnemyFactoryManager.getInstance().createCharacter();
       int randomInt = ThreadLocalRandom.current().nextInt(0, 2);
       switch (randomInt){
           case 0:
               this.enemyIA = new GenericEnemyIA();
               break;
           case 1:
               this.enemyIA = new DefensiveEnemyIA();
               break;
           case 2:
               this.enemyIA = new OffensiveEnemyIA();
               break;
           default:
               this.enemyIA = new GenericEnemyIA();
       }

       this.enemyIA.setEnemy(this.enemy);
       this.enemyIA.setPlayer(this.player);
    }

    public EnemyIATemplate getEnemyIA() {
        return enemyIA;
    }

    public void setEnemyIA(EnemyIATemplate enemyIA) {
        this.enemyIA = enemyIA;
    }

    //Realiza la accion del jugador segun la estrategia que posea
    public void playerAction(){
        playerActionStrategy.action(this.player, this.enemy);
    }

    //Realiza la accion del enemigo segun la estrategia que posea
    public void enemyAction(){
        enemyActionStrategy.action(this.enemy, this.player);
    }
}
