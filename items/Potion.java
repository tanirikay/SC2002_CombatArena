package sc2002project.items;

import sc2002project.BattleEngine;
import sc2002project.Combatant;
import sc2002project.ui.GameUI;

public class Potion implements Item {
    @Override
    public void use(Combatant user, BattleEngine engine) {
        user.heal(100);
        GameUI.showActionResult(user.getName() + " used a Potion and healed 100 HP!");
    }

    @Override
    public String getName() {
        return "Potion";
    }
}
