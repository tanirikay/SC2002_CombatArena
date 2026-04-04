package sc2002project.effects;

import sc2002project.Combatant;

public class SmokeBombEffect implements StatusEffect {
    private int duration = 2;

    @Override
    public void apply(Combatant target) {
        // No stat change — BattleEngine sets incoming enemy damage to 0 while active
    }

    @Override
    public void tick(Combatant target) {
        duration--;
    }

    @Override
    public void remove(Combatant target) {
        // Nothing to undo
    }

    @Override
    public boolean isExpired() {
        return duration <= 0;
    }

    @Override
    public boolean blocksDamage() { return true; }

    @Override
    public String getName() {
        return "SmokeBomb";
    }
}
