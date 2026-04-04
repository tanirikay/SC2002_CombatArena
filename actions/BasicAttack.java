package sc2002project.actions;

import sc2002project.BattleEngine;
import sc2002project.Combatant;
import sc2002project.ui.GameUI;

public class BasicAttack implements Action {
    @Override
    public void execute(Combatant actor, Combatant target, BattleEngine engine) {
        int dmg = Math.max(0, actor.getAttack() - target.getDefense());
        target.takeDamage(dmg);
        GameUI.showActionResult(actor.getName() + " attacks " + target.getName() + " for " + dmg + " damage!");
    }
}
