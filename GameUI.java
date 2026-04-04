import java.util.List;
import java.util.Scanner;

public class GameUI {

    private static final Scanner sc = new Scanner(System.in);

    public static void PlayerInit() {
        System.out.println("Players");
        System.out.println("=======");
        System.out.println("1. Warrior - HP: 260, ATK: 40, DEF: 20, SPD: 30");
        System.out.println("-----------------Special Skill-----------------");
        System.out.println("Shield Bash Effect: Deal BasicAttack damage to selected enemy.\n" +
                "Selected enemy is unable to take actions for the current turn and the next turn.\n");
        System.out.println("2. Wizard - HP: 200, ATK: 50, DEF: 10, SPD: 20");
        System.out.println("----------------Special Skill-----------------");
        System.out.println("Arcane Blast Effect: Deal BasicAttack damage to all enemies currently in combat.\n" +
                "Each enemy defeated by Arcane Blast adds 10 to the Wizard's Attack, lasting until end of the level.\n");
    }

    public static void ItemInit() {
        System.out.println("Items");
        System.out.println("=====");
        System.out.println("1. Potion: When used, Heal 100HP.");
        System.out.println("2. Power Stone: Trigger the special skill effect once, but it does not start or change the cooldown timer. In short, free extra use of the skill.");
        System.out.println("3. Smoke Bomb: When used, Enemy attacks do 0 damage in the current turn and the next turn\n");
    }

    public static void DifficultyInit() {
        System.out.println("Game Difficulty");
        System.out.println("===============");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
    }

    public static int PlayerChoice() {
        PlayerInit();
        while (true) {
            System.out.print("Select your player: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Warrior has been selected.\n");
                        return 1;
                    case 2:
                        System.out.println("Wizard has been selected.\n");
                        return 2;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                sc.next();
                System.out.println("Invalid choice.");
            }
        }
    }

    public static String[] ItemChoice() {
        int count = 0;
        String[] items = new String[2];
        ItemInit();
        while (count < 2) {
            System.out.print("Select your items (" + (count + 1) + " of 2): ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Potion has been selected.\n");
                        items[count] = "Potion";
                        count++;
                        break;
                    case 2:
                        System.out.println("Power Stone has been selected.\n");
                        items[count] = "Power Stone";
                        count++;
                        break;
                    case 3:
                        System.out.println("Smoke Bomb has been selected.\n");
                        items[count] = "Smoke Bomb";
                        count++;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                sc.next();
                System.out.println("Invalid choice.");
            }
        }
        return items;
    }

    public static int DifficultyChoice() {
        DifficultyInit();
        while (true) {
            System.out.print("\nSelect your difficulty: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Easy Difficulty has been selected.");
                        return 1;
                    case 2:
                        System.out.println("Medium Difficulty has been selected.");
                        return 2;
                    case 3:
                        System.out.println("Hard Difficulty has been selected.");
                        return 3;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                sc.next();
                System.out.println("Invalid choice.");
            }
        }
    }

    public static void enemies(int difficulty) {
        switch (difficulty) {
            case 1:
                System.out.println(" (3 Goblins) - A, B, C, Goblin Stats: HP: 55, ATK: 35, DEF: 15, SPD: 25");
                break;
            case 2:
                System.out.println(" - 1 Goblin + 1 Wolf | Backup: Wolf A + Wolf B");
                System.out.println("Goblin Stats: HP: 55, ATK: 35, DEF: 15, SPD: 25");
                System.out.println("Wolf Stats: HP: 40, ATK: 45, DEF: 5, SPD: 35");
                break;
            case 3:
                System.out.println(" (2 Goblins) - A, B | Backup: 1 Goblin + Wolf A + Wolf B");
                System.out.println("Goblin Stats: HP: 55, ATK: 35, DEF: 15, SPD: 25");
                System.out.println("Wolf Stats: HP: 40, ATK: 45, DEF: 5, SPD: 35");
                break;
        }
    }

    public static void turnOrder(int player, int difficulty) {
        switch (difficulty) {
            case 1:
                if (player == 1) {
                    System.out.println("Warrior (SPD 30) -> Goblin (SPD 25)");
                } else if (player == 2) {
                    System.out.println("Goblin (SPD 25) -> Wizard (SPD 20)");
                }
                break;
            case 2: case 3:
                if (player == 1) {
                    System.out.println("Wolf (SPD 35) -> Warrior (SPD 30) -> Goblin (SPD 25)");
                } else if (player == 2) {
                    System.out.println("Wolf (SPD 35) -> Goblin (SPD 25) -> Wizard (SPD 20)");
                }
                break;
        }
    }

    public static void GameInit(int player, String[] items, int difficulty) {
        String playerClass = "";
        String stats = "";
        String difficultyLevel = "";

        if (player == 1) {
            playerClass = "Warrior";
            stats = "Warrior Stats: HP: 260, ATK: 40, DEF: 20, SPD: 30";
        } else if (player == 2) {
            playerClass = "Wizard";
            stats = "Wizard Stats: HP: 200, ATK: 50, DEF: 10, SPD: 20";
        }

        if (difficulty == 1) {
            difficultyLevel = "Easy";
        } else if (difficulty == 2) {
            difficultyLevel = "Medium";
        } else if (difficulty == 3) {
            difficultyLevel = "Hard";
        }

        System.out.println("\nDifficulty Level: (" + difficultyLevel + ")");
        System.out.println("Player: " + playerClass + ", " + stats);
        System.out.println("Items: " + items[0] + " + " + items[1]);
        System.out.print("Level: " + difficultyLevel); enemies(difficulty);
        System.out.print("Turn Order: "); turnOrder(player, difficulty);
    }

    public static void RoundHeader(int roundNumber, Combatant player, List<Combatant> enemies) {
        System.out.println("\n=== Round " + roundNumber + " ===");
        // System.out.println(player.getStatusLine()); (For status effect implementation)
        for (Combatant enemy : enemies) {
            // System.out.println(enemy.getStatusLine()); (For status effect implementation)
        }
        System.out.println("-----------------------------------------");
    }

    public static void BackupSpawn(List<Combatant> backupEnemies) {
        System.out.println("\nBackup enemies have spawned!");
        for (Combatant e : backupEnemies) {
            // System.out.println("+ " + e.getStatusLine()); (For status effect implementation)
        }
        System.out.println();
    }

    public static void ActionMenu(String[] itemNames, int[] itemsRemaining, int skillCooldown) {
        System.out.println("\nChoose your action:");
        System.out.println("1. Basic Attack");
        System.out.println("2. Defend");

        // Item slot 1
        if (itemsRemaining[0] > 0) {
            System.out.println("3. Item - " + itemNames[0]);
        } else {
            System.out.println("3. Item - " + itemNames[0] + " (Used)");
        }

        // Item slot 2
        if (itemsRemaining[1] > 0) {
            System.out.println("4. Item - " + itemNames[1]);
        } else {
            System.out.println("4. Item - " + itemNames[1] + " (Used)");
        }

        // Special Skill
        if (skillCooldown == 0) {
            System.out.println("5. Special Skill");
        } else {
            System.out.println("5. Special Skill (Cooldown: " + skillCooldown + " turn(s))");
        }
    }

    public static int ActionChoice(String[] itemNames, int[] itemsRemaining, int skillCooldown) {
        ActionMenu(itemNames, itemsRemaining, skillCooldown);
        while (true) {
            System.out.print("Select your action: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: case 2:
                        return choice;
                    case 3:
                        if (itemsRemaining[0] > 0) return 3;
                        System.out.println("That item has already been used.");
                        break;
                    case 4:
                        if (itemsRemaining[1] > 0) return 4;
                        System.out.println("That item has already been used.");
                        break;
                    case 5:
                        if (skillCooldown == 0) return 5;
                        System.out.println("Special Skill is on cooldown (" + skillCooldown + " turn(s) remaining).");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                sc.next();
                System.out.println("Invalid choice.");
            }
        }
    }

    public static int TargetChoice(List<Combatant> aliveEnemies) {
        System.out.println("Choose a target:");
        for (int i = 0; i < aliveEnemies.size(); i++) {
            // System.out.println((i + 1) + ". " + aliveEnemies.get(i).getStatusLine()); (For status effect implementation)
        }
        while (true) {
            System.out.print("Select your target: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                if (choice >= 1 && choice <= aliveEnemies.size()) {
                    return choice - 1;
                }
                System.out.println("Invalid choice.");
            } else {
                sc.next();
                System.out.println("Invalid choice.");
            }
        }
    }

    public static void ActionResult(String actionLog) {
        System.out.println(actionLog);
    }

    public static void StunnedSkip(Combatant combatant) {
        System.out.println(combatant.getName() + " -> STUNNED: Turn skipped");
    }

    public static void EndOfRound(int roundNumber, Combatant player, List<Combatant> enemies,
                                  String[] itemNames, int[] itemsRemaining, int skillCooldown) {
        StringBuilder summary = new StringBuilder();
        summary.append("End of Round ").append(roundNumber).append(": ");
        summary.append(player.getName()).append(" HP: ").append(player.getHp())
                .append("/").append(player.getMaxHp());

        for (Combatant e : enemies) {
            summary.append(" | ").append(e.getName()).append(" HP: ");
            if (e.isAlive()) {
                summary.append(e.getHp());
//                if (!e.getActiveEffects().isEmpty()) {
//                    summary.append(" ").append(e.getActiveEffects());
//                } (For status effect implementation)
            } else {
                summary.append("X");
            }
        }

        for (int i = 0; i < itemNames.length; i++) {
            summary.append(" | ").append(itemNames[i]).append(": ").append(itemsRemaining[i]);
        }

        if (skillCooldown == 0) {
            summary.append(" | Special Skill Cooldown: 0 Rounds");
        } else {
            summary.append(" | Special Skill Cooldown: ").append(skillCooldown).append(" Round(s)");
        }

        System.out.println(summary);
    }

    public static void VictoryScreen(Combatant player, int totalRounds,
                                     String[] itemNames, int[] itemsRemaining) {
        System.out.println("\nCongratulations, you have defeated all your enemies.");
        System.out.println("Statistics: Remaining HP: " + player.getHp() + "/" + player.getMaxHp()
                + " | Total Rounds: " + totalRounds);
        for (int i = 0; i < itemNames.length; i++) {
            System.out.println("Remaining " + itemNames[i] + ": " + itemsRemaining[i]);
        }
    }

    public static void DefeatScreen(List<Combatant> enemies, int totalRounds,
                                    String[] itemNames, int[] itemsRemaining) {
        long enemiesRemaining = 0;
        for (Combatant e : enemies) {
            if (e.isAlive()) enemiesRemaining++;
        }
        System.out.println("\nDefeated. Don't give up, try again!");
        System.out.println("Statistics: Enemies remaining: " + enemiesRemaining
                + " | Total Rounds Survived: " + totalRounds);
        for (int i = 0; i < itemNames.length; i++) {
            System.out.println("Remaining " + itemNames[i] + ": " + itemsRemaining[i]);
        }
    }

    public static int ReplayChoice() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Replay with the same settings");
        System.out.println("2. Start a new game");
        System.out.println("3. Exit");
        while (true) {
            System.out.print("Select your choice: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Replaying with the same settings.\n");
                        return 1;
                    case 2:
                        System.out.println("Starting a new game.\n");
                        return 2;
                    case 3:
                        System.out.println("Thank you for playing. Goodbye!");
                        return 3;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                sc.next();
                System.out.println("Invalid choice.");
            }
        }
    }

    public static void main(String[] args) {
        boolean running = true;
        int player = -1;
        String[] items = null;
        int difficulty = -1;

        while (running) {
            player = PlayerChoice();
            items = ItemChoice();
            difficulty = DifficultyChoice();
            GameInit(player, items, difficulty);

            // BattleEngine.run(player, items, difficulty) would be called here.
            // After the battle ends, BattleEngine returns the result to drive
            // VictoryScreen() or DefeatScreen(), then ReplayChoice() is shown.

            int replay = ReplayChoice();
            switch (replay) {
                case 1:
                    // Replay with same settings — skip re-selection, re-run battle directly
                    GameInit(player, items, difficulty);
                    // BattleEngine.run(player, items, difficulty);
                    break;
                case 2:
                    // New game — loop back to player selection
                    break;
                case 3:
                    running = false;
                    break;
            }
        }

        sc.close();
    }
}
