package sc2002project;

import java.util.*;

// for extensibility
public interface TurnOrderStrategy {
    List<Combatant> determineOrder(List<Combatant> combatants);
}
