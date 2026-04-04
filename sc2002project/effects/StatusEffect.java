package sc2002project.effects;

import sc2002project.Combatant;

public interface StatusEffect {
    void apply(Combatant target);
    void tick(Combatant target);
    void remove(Combatant target);
    boolean isExpired();
    String getName();
    default boolean blocksDamage() { return false; }
}
