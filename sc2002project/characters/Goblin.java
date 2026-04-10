package sc2002project.characters;

import sc2002project.BattleEngine;
import sc2002project.Combatant;
import sc2002project.ui.GameUI;

public class Goblin extends Combatant {
    public Goblin() {
        super("Goblin", 55, 35, 15, 25);
    }

    public Goblin(String name) {
        super(name, 55, 35, 15, 25);
    }

    @Override
    public void performTurn(BattleEngine engine) {
        Combatant target = engine.getLivingPlayers().get(0);
        int dmg = Math.max(0, this.getAttack() - target.getDefense());
        int hpBefore = target.getHp();
        target.takeDamage(dmg);
        int actualDmg = hpBefore - target.getHp();
        if (actualDmg == 0) {
            GameUI.showActionResult(this.getName() + "'s attack was blocked by Smoke Bomb!");
        } else {
            GameUI.showActionResult(this.getName() + " attacks " + target.getName() + " for " + actualDmg + " damage!");
        }
    }
}
