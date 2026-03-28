package sc2002project;

import java.util.*;

// for extensibility
public interface TurnOrderStrategy {
	public void determineOrder(List<Combatant> combatatnts);

}
