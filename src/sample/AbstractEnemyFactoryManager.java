package sample;

public class AbstractEnemyFactoryManager {

    AbstractEnemyFactory abstractEnemyFactory;

    private static AbstractEnemyFactoryManager abstractEnemyFactoryManager =
            new AbstractEnemyFactoryManager(new EnemyFactory());

    private AbstractEnemyFactoryManager(AbstractEnemyFactory abstractEnemyFactory){
        this.abstractEnemyFactory= abstractEnemyFactory;
    }

    public static AbstractEnemyFactoryManager getInstance() {
        return abstractEnemyFactoryManager;
    }

    public AbstractEnemyFactory getAbstractEnemyFactory() {
        return abstractEnemyFactory;
    }

    public void setAbstractEnemyFactory(AbstractEnemyFactory abstractEnemyFactory) {
        this.abstractEnemyFactory = abstractEnemyFactory;
    }

    public AbstractEnemyFactoryManager getAbstractEnemyFactoryManager() {
        return abstractEnemyFactoryManager;
    }

    public void setAbstractEnemyFactoryManager(AbstractEnemyFactoryManager abstractEnemyFactoryManager) {
        AbstractEnemyFactoryManager.abstractEnemyFactoryManager = abstractEnemyFactoryManager;
    }

    public Character createCharacter(){
        return this.abstractEnemyFactory.createEnemy();
    }
}
