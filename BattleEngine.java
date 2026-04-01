package sc2002project;
import java.util.*;

public class BattleEngine {
    private List<Combatant> players;
    private List<Combatant> enemies;
    private TurnOrderStrategy turnOrderStrategy; 

    public BattleEngine(List<Combatant> players, List<Combatant> enemies, TurnOrderStrategy strategy) {
        this.players = players;
        this.enemies = enemies;
        this.turnOrderStrategy = strategy;
    }

    // Main game loop 
    public void startBattle() {
        while (!isBattleOver()) {
            List<Combatant> allCombatants = new ArrayList<>();
            allCombatants.addAll(players);
            allCombatants.addAll(enemies);

            // Sort by Speed using Strategy Pattern 
            turnOrderStrategy.determineOrder(allCombatants);

            for (Combatant c : allCombatants) {
                if (c.isAlive() && !isBattleOver()) {
                    c.performTurn(this);
                    checkGameStatus(); 
                }
            }
        }
    }

    private boolean isBattleOver() {
        // Win: all enemies defeated 
        // Lose: player defeated
        boolean allEnemiesDead = enemies.stream().noneMatch(Combatant::isAlive);
        boolean allPlayersDead = players.stream().noneMatch(Combatant::isAlive);
        return allEnemiesDead || allPlayersDead;
    }

    private void checkGameStatus() {
        if (enemies.stream().noneMatch(Combatant::isAlive)) {
        }
    }
}