package sc2002project;

import java.util.*;
import sc2002project.effects.StatusEffect;
import sc2002project.effects.StunEffect;
import sc2002project.ui.GameUI;

public class BattleEngine {
    private List<Combatant> players;
    private List<Combatant> enemies;
    private TurnOrderStrategy turnOrderStrategy;
    private Level level;
    private int roundCount = 0;
    private boolean backupSpawned = false;
    private final boolean hasBackup;

    public BattleEngine(List<Combatant> players, List<Combatant> enemies,
                        TurnOrderStrategy strategy, Level level) {
        this.players = players;
        this.enemies = enemies;
        this.turnOrderStrategy = strategy;
        this.level = level;
        this.hasBackup = !level.getBackupEnemies().isEmpty();
    }

    public void startBattle() {
        while (!isBattleOver()) {
            // 1. Combine all living combatants into one list
            List<Combatant> all = new ArrayList<>();
            all.addAll(players);
            all.addAll(enemies);

            // 2. Sort by speed
            List<Combatant> turnOrder = turnOrderStrategy.determineOrder(all);

            // 3. Each combatant takes their turn
            for (Combatant c : turnOrder) {
                // a. Skip if dead
                if (!c.isAlive()) continue;
                if (isBattleOver()) break;

                // b. Check for stun — skip turn, tick effect
                StunEffect stun = findStunEffect(c);
                if (stun != null) {
                    GameUI.showStunSkip(c.getName());
                    stun.tick(c);
                    if (stun.isExpired()) {
                        stun.remove(c);
                        c.removeStatusEffect(stun);
                    }
                    continue;
                }

                // c. Tick all other active effects; remove expired ones
                tickEffects(c);

                // d. Perform turn
                c.performTurn(this);

                // e. Check battle end after every action
                if (checkBattleEnd()) return;
            }

            // 4. After all combatants have acted — check backup spawn
            if (getLivingEnemies().isEmpty() && !backupSpawned) {
                List<Combatant> backup = level.getBackupEnemies();
                if (!backup.isEmpty()) {
                    enemies.addAll(backup);
                    backupSpawned = true;
                    GameUI.showBackupSpawn(backup);
                }
            }

            // 5. Increment round count
            roundCount++;

            // 6. Show end-of-round summary
            List<Combatant> allForSummary = new ArrayList<>();
            allForSummary.addAll(players);
            allForSummary.addAll(enemies);
            GameUI.showRoundSummary(roundCount, allForSummary, players);
        }
    }

    // Helper methods

    private StunEffect findStunEffect(Combatant c) {
        for (StatusEffect e : c.getStatusEffects()) {
            if (e instanceof StunEffect) return (StunEffect) e;
        }
        return null;
    }

    private void tickEffects(Combatant c) {
        List<StatusEffect> toRemove = new ArrayList<>();
        for (StatusEffect e : new ArrayList<>(c.getStatusEffects())) {
            e.tick(c);
            if (e.isExpired()) {
                e.remove(c);
                toRemove.add(e);
            }
        }
        for (StatusEffect e : toRemove) {
            c.removeStatusEffect(e);
        }
    }

    private boolean checkBattleEnd() {
        boolean allEnemiesDead = enemies.stream().noneMatch(Combatant::isAlive);
        boolean allPlayersDead = players.stream().noneMatch(Combatant::isAlive);

        // Don't declare victory yet if backup wave hasn't spawned
        if (allEnemiesDead && hasBackup && !backupSpawned) return false;

        if (allEnemiesDead) {
            Combatant player = players.get(0);
            GameUI.showVictory(player.getHp(), player.getMaxHp(), roundCount);
            return true;
        }
        if (allPlayersDead) {
            int remaining = (int) enemies.stream().filter(Combatant::isAlive).count();
            GameUI.showDefeat(remaining, roundCount);
            return true;
        }
        return false;
    }

    private boolean isBattleOver() {
        boolean allEnemiesDead = enemies.stream().noneMatch(Combatant::isAlive);
        boolean allPlayersDead = players.stream().noneMatch(Combatant::isAlive);

        // Keep going if backup exists and hasn't spawned yet
        if (allEnemiesDead && hasBackup && !backupSpawned) return false;

        return allEnemiesDead || allPlayersDead;
    }

    // -------------------------------------------------------------------------
    // Public accessors used by Actions and characters
    // -------------------------------------------------------------------------

    public List<Combatant> getLivingEnemies() {
        List<Combatant> living = new ArrayList<>();
        for (Combatant c : enemies) {
            if (c.isAlive()) living.add(c);
        }
        return living;
    }

    public List<Combatant> getLivingPlayers() {
        List<Combatant> living = new ArrayList<>();
        for (Combatant c : players) {
            if (c.isAlive()) living.add(c);
        }
        return living;
    }

    public List<Combatant> getEnemies()  { return enemies; }
    public List<Combatant> getPlayers()  { return players; }
    public int getRoundCount()           { return roundCount; }
}
