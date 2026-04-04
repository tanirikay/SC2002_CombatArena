package sc2002project.actions;

import sc2002project.BattleEngine;
import sc2002project.Combatant;
import sc2002project.effects.DefendEffect;
import sc2002project.ui.GameUI;

public class DefendAction implements Action {
    @Override
    public void execute(Combatant actor, Combatant target, BattleEngine engine) {
        DefendEffect effect = new DefendEffect();
        effect.apply(actor);
        actor.addStatusEffect(effect);
        GameUI.showActionResult(actor.getName() + " takes a defensive stance! DEF +10 for 2 turns.");
    }
}
