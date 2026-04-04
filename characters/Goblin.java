package sc2002project.characters;

import sc2002project.BattleEngine;
import sc2002project.Combatant;
import sc2002project.ui.GameUI;

public class Goblin extends Combatant {
    public Goblin() {
        // Stats: HP: 55, Attack: 35, Defense: 15, Speed: 25
        super("Goblin", 55, 35, 15, 25);
    }

    @Override
    public void performTurn(BattleEngine engine) {
        Combatant target = engine.getLivingPlayers().get(0);
        int dmg = Math.max(0, this.getAttack() - target.getDefense());
        target.takeDamage(dmg);
        GameUI.showActionResult(this.getName() + " attacks " + target.getName() + " for " + dmg + " damage!");
    }
}
