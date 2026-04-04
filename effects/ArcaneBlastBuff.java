package sc2002project.effects;

import sc2002project.Combatant;

public class ArcaneBlastBuff implements StatusEffect {

    @Override
    public void apply(Combatant target) {
        target.setAttack(target.getAttack() + 10);
    }

    @Override
    public void tick(Combatant target) {
        // No duration — lasts until end of level
    }

    @Override
    public void remove(Combatant target) {
        // Cleared when BattleEngine resets the combatant list between levels
    }

    @Override
    public boolean isExpired() {
        return false; // Never expires on its own
    }

    @Override
    public String getName() {
        return "ArcaneBlastBuff";
    }
}
