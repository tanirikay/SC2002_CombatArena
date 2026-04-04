package sc2002project.items;

import sc2002project.BattleEngine;
import sc2002project.Combatant;
import sc2002project.effects.SmokeBombEffect;
import sc2002project.ui.GameUI;

public class SmokeBomb implements Item {
    @Override
    public void use(Combatant user, BattleEngine engine) {
        SmokeBombEffect effect = new SmokeBombEffect();
        effect.apply(user);
        user.addStatusEffect(effect);
        GameUI.showActionResult(user.getName() + " used a Smoke Bomb! Enemy attacks deal 0 damage for 2 turns.");
    }

    @Override
    public String getName() {
        return "Smoke Bomb";
    }
}
