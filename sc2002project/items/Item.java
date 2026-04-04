package sc2002project.items;

import sc2002project.BattleEngine;
import sc2002project.Combatant;

public interface Item {
    void use(Combatant user, BattleEngine engine);
    String getName();
}
