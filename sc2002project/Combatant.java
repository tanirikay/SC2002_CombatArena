package sc2002project;

import java.util.*;
import sc2002project.effects.StatusEffect;
import sc2002project.items.Item;

public abstract class Combatant {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attack;
    protected int defense;
    protected int speed;
    protected int specialSkillCooldown = 0;
    protected List<StatusEffect> activeStatusEffects = new ArrayList<>();

    public Combatant(String name, int hp, int attack, int defense, int speed) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    // Damage formula: max(0, AttackerAttack - TargetDefense) — subtraction happens in Action, not here
    public void takeDamage(int damage) {
        for (StatusEffect e : activeStatusEffects) {
            if (e.blocksDamage()) return; // SmokeBombEffect active — no damage
        }
        this.hp = Math.max(0, this.hp - damage);
    }

    public void heal(int amount) {
        this.hp = Math.min(this.hp + amount, this.maxHp);
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    // Getters
    public String getName()               { return name; }
    public int getHp()                    { return hp; }
    public int getMaxHp()                 { return maxHp; }
    public int getAttack()                { return attack; }
    public int getDefense()               { return defense; }
    public int getSpeed()                 { return speed; }
    public int getSpecialSkillCooldown()  { return specialSkillCooldown; }
    public List<StatusEffect> getStatusEffects() { return activeStatusEffects; }

    // Setters
    public void setAttack(int a)               { this.attack = a; }
    public void setDefense(int d)              { this.defense = d; }
    public void setSpecialSkillCooldown(int n) { this.specialSkillCooldown = n; }

    // Status effect management
    public void addStatusEffect(StatusEffect e)    { activeStatusEffects.add(e); }
    public void removeStatusEffect(StatusEffect e) { activeStatusEffects.remove(e); }

    // Returns the combatant's inventory (overridden by player classes)
    public List<Item> getInventory() { return Collections.emptyList(); }

    // Abstract method: each subclass decides how to act on its turn
    public abstract void performTurn(BattleEngine engine);
}
