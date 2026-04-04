package sc2002project.effects;

import sc2002project.Combatant;

public class DefendEffect implements StatusEffect {
    private int duration = 2;

    @Override
    public void apply(Combatant target) {
        target.setDefense(target.getDefense() + 10);
    }

    @Override
    public void tick(Combatant target) {
        duration--;
    }

    @Override
    public void remove(Combatant target) {
        target.setDefense(target.getDefense() - 10);
    }

    @Override
    public boolean isExpired() {
        return duration <= 0;
    }

    @Override
    public String getName() {
        return "Defend";
    }
}
