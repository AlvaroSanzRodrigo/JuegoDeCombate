package sample;

import java.util.ArrayList;
// Clase de personaje con todos sus atributos necesarios
public class Character{
    // Atributos de stats de juego (nombre, vida, ataque y defensa)
    private String name;
    private float life;
    private float attackPower;
    private float defensePower;
    // Armas que posee el personaje
    private ArrayList<Weapon> weapons;
    // Arma que actualmente esta usando el personaje
    private Weapon currentWeapon;
    // Estado del turno en el que se encuentra el personaje
    private TurnState turnState;
    // Ruta de la imagen del personaje
    private String imageRoute;

    //Constructores
    public Character() {
    }

    public Character(String name, float life, float attackPower, float defensePower, ArrayList<Weapon> weapons, Weapon currentWeapon, TurnState turnState, String imageRoute) {
        this.name = name;
        this.life = life;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.weapons = weapons;
        this.currentWeapon = currentWeapon;
        this.turnState = turnState;
        this.imageRoute = imageRoute;
    }

    //Getters y setters de los atributos del personaje
    public float getLife() {
        return life;
    }

    public void setLife(float life) {
        this.life = life;
    }

    public float getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(float attackPower) {
        this.attackPower = attackPower;
    }

    public float getDefensePower() {
        return defensePower;
    }

    public void setDefensePower(float defensePower) {
        this.defensePower = defensePower;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public TurnState getTurnState() {
        return turnState;
    }

    public void setTurnState(TurnState turnState) {
        this.turnState = turnState;
    }

    public String getImageRoute() {
        return imageRoute;
    }

    public void setImageRoute(String imageRoute) {
        this.imageRoute = imageRoute;
    }

    public void changeWeapon(Weapon weapon) {
        currentWeapon = weapon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
