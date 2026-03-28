import java.util.Scanner;  // Import the Scanner class

public class GameUI {
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
                "Each enemy defeated by Arcane Blast adds 10 to the Wizard’s Attack, lasting until end of the level.\n");
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
        Scanner sc = new Scanner(System.in);
        PlayerInit();
        while (true) {
            System.out.print("Select your player: ");
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
        }
    }

    public static String[] ItemChoice() {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        String[] items = new String[2];
        ItemInit();
        while (count < 2) {
            System.out.print("Select your items: ");
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
        }

        return items;
    }

    public static int DifficultyChoice() {
        Scanner sc = new Scanner(System.in);
        DifficultyInit();
        while (true) {
            System.out.print("\nSelect your difficulty: ");
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
            case 2 : case 3:
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
        }  else if (difficulty == 2) {
            difficultyLevel = "Medium";

        }   else if (difficulty == 3) {
            difficultyLevel = "Hard";
        }

        System.out.println("\nDifficulty Level: (" + difficultyLevel + ")");
        System.out.println("Player: " + playerClass + ", " + stats);
        System.out.println("Items: " + items[0] + " + " + items[1]);
        System.out.print("Level: " + difficultyLevel); enemies(difficulty);
        System.out.print("Turn Order: "); turnOrder(player, difficulty);
    }

    public static void main(String[] args) {
        int player = PlayerChoice();
        String[] items = ItemChoice();
        int difficulty = DifficultyChoice();
        GameInit(player, items, difficulty);
    }
}
