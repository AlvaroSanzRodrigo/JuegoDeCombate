package sample.AbstractFactory;

import sample.Models.Weapon;

public class AbstractWeaponFactoryManager {

    AbstractWeaponFactory abstractWeaponFactory;

    private static AbstractWeaponFactoryManager abstractWeaponFactoryManager =
            new AbstractWeaponFactoryManager(new WeaponFactory());

    private AbstractWeaponFactoryManager(AbstractWeaponFactory abstractWeaponFactory){
        this.abstractWeaponFactory= abstractWeaponFactory;
    }

    public static AbstractWeaponFactoryManager getInstance() {
        return abstractWeaponFactoryManager;
    }

    public AbstractWeaponFactory getAbstractWeaponFactory() {
        return abstractWeaponFactory;
    }

    public void setAbstractWeaponFactory(AbstractWeaponFactory abstractWeaponFactory) {
        this.abstractWeaponFactory = abstractWeaponFactory;
    }

    public AbstractWeaponFactoryManager getAbstractWeaponManagerFactory() {
        return abstractWeaponFactoryManager;
    }

    public static void setAbstractWeaponFactoryManager(AbstractWeaponFactoryManager abstractWeaponFactoryManager) {
        AbstractWeaponFactoryManager.abstractWeaponFactoryManager = abstractWeaponFactoryManager;
    }

    public Weapon createWeapon(){
        return this.abstractWeaponFactory.createWeapon();
    }
    
}
