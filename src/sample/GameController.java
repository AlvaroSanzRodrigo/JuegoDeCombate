package sample;

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
    private ActionStrategy actionStrategy;

    public ActionStrategy getActionStrategy() {
        return actionStrategy;
    }

    public void setActionStrategy(ActionStrategy actionStrategy) {
        this.actionStrategy = actionStrategy;
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

    //Realiza la accion del jugador segun la estrategia que posea
    public void playerAction(){
        actionStrategy.action(this.player, this.enemy);
    }
}
