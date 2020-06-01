package sample;
// Clase de arma
public class Weapon {
    // Atributos del arma y game stats (ataque, defensa)
    private String name;
    private float attack;
    private float defense;


    // Constructores
    public Weapon() {
    }

    public Weapon(String name, float attack, float defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }
    // Getters y setters de los atributos
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAttack() {
        return attack;
    }

    public void setAttack(float attack) {
        this.attack = attack;
    }

    public float getDefense() {
        return defense;
    }

    public void setDefense(float defense) {
        this.defense = defense;
    }
}
