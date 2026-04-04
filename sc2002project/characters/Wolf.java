package sc2002project.characters;

import sc2002project.BattleEngine;
import sc2002project.Combatant;
import sc2002project.ui.GameUI;

public class Wolf extends Combatant {
    public Wolf() {
        // Stats: HP: 40, Attack: 45, Defense: 5, Speed: 35
        super("Wolf", 40, 45, 5, 35);
    }

    @Override
    public void performTurn(BattleEngine engine) {
        Combatant target = engine.getLivingPlayers().get(0);
        int dmg = Math.max(0, this.getAttack() - target.getDefense());
        target.takeDamage(dmg);
        GameUI.showActionResult(this.getName() + " attacks " + target.getName() + " for " + dmg + " damage!");
    }
}
