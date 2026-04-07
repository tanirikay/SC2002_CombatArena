package sc2002project;

// reminder: add status effects
import java.util.*;
public abstract class Combatant {
	protected String name;
    protected int hp;
    protected int maxHp;
    protected int attack;
    protected int defense;
    protected int speed;
    protected int specialSkillCooldown = 0; 
    protected List<String> activeStatusEffects = new ArrayList<>(); 

    public Combatant(String name, int hp, int attack, int defense, int speed) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }
 
   
    // Common behavior: Taking damage 
    // Damage formula: $Damage = \max(0, AttackerAttack - TargetDefense)$ 
    public void takeDamage(int damage) {
        this.hp = Math.max(0, this.hp - damage); 
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    // Getters for stats 
    public int getSpeed() { return this.speed; }
    public String getName() { return this.name; }
    public int getHp() { return this.hp; }
   
    public int getAttack() {return this.attack;}

    public int getDefense() {return this.defense;}
    public int getMaxHp() { return this.maxHp; }
    public int getSpecialSkillCooldown() { return this.specialSkillCooldown; }
    
    
    // Abstract method: Each type decides how to act
    public abstract void performTurn(BattleEngine engine);

}
