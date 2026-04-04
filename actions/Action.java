package sc2002project.actions;

import sc2002project.BattleEngine;
import sc2002project.Combatant;

public interface Action {
    void execute(Combatant actor, Combatant target, BattleEngine engine);
}
