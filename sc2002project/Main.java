package sc2002project;

import java.util.*;
import sc2002project.characters.Warrior;
import sc2002project.characters.Wizard;
import sc2002project.items.Item;
import sc2002project.items.Potion;
import sc2002project.items.PowerStone;
import sc2002project.items.SmokeBomb;
import sc2002project.strategy.SpeedBasedTurnOrder;
import sc2002project.ui.GameUI;

public class Main {
    public static void main(String[] args) {
        // 1. Player selection
        int playerChoice = GameUI.PlayerChoice();

        // 2. Item selection
        String[] itemNames = GameUI.ItemChoice();

        // 3. Difficulty selection
        int difficultyChoice = GameUI.DifficultyChoice();

        int replay;
        do {
            replay = runGame(playerChoice, itemNames, difficultyChoice);
            if (replay == 2) {
                // New game — re-prompt all selections
                playerChoice = GameUI.PlayerChoice();
                itemNames = GameUI.ItemChoice();
                difficultyChoice = GameUI.DifficultyChoice();
            }
        } while (replay == 1 || replay == 2);
    }

    private static int runGame(int playerChoice, String[] itemNames, int difficultyChoice) {
        List<Item> inventory = buildInventory(itemNames);
        Level level = buildLevel(difficultyChoice);

        // Show game summary
        GameUI.GameInit(playerChoice, itemNames, difficultyChoice);

        // Build player combatant
        Combatant player = (playerChoice == 1)
                ? new Warrior(inventory)
                : new Wizard(inventory);

        // Start battle
        List<Combatant> players  = new ArrayList<>(List.of(player));
        List<Combatant> enemies  = level.getInitialEnemies();
        TurnOrderStrategy strategy = new SpeedBasedTurnOrder();

        BattleEngine engine = new BattleEngine(players, enemies, strategy, level);
        engine.startBattle();

        // Replay menu
        return GameUI.showReplayMenu();
    }

    private static List<Item> buildInventory(String[] itemNames) {
        List<Item> inventory = new ArrayList<>();
        for (String name : itemNames) {
            switch (name) {
                case "Potion":      inventory.add(new Potion());      break;
                case "Power Stone": inventory.add(new PowerStone());  break;
                case "Smoke Bomb":  inventory.add(new SmokeBomb());   break;
            }
        }
        return inventory;
    }

    private static Level buildLevel(int difficulty) {
        switch (difficulty) {
            case 1:  return Level.easy();
            case 2:  return Level.medium();
            default: return Level.hard();
        }
    }
}
