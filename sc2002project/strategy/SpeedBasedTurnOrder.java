package sc2002project.strategy;

import java.util.*;
import sc2002project.Combatant;
import sc2002project.TurnOrderStrategy;

public class SpeedBasedTurnOrder implements TurnOrderStrategy {
    @Override
    public List<Combatant> determineOrder(List<Combatant> combatants) {
        List<Combatant> sorted = new ArrayList<>(combatants);
        sorted.sort((a, b) -> b.getSpeed() - a.getSpeed());
        return sorted;
    }
}
