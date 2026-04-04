package sc2002project;

import java.util.*;
import java.util.function.Supplier;
import sc2002project.characters.Goblin;
import sc2002project.characters.Wolf;

public class Level {
    private String difficultyName;
    private Supplier<List<Combatant>> initialEnemies;
    private Supplier<List<Combatant>> backupEnemies;

    public Level(String difficultyName,
                 Supplier<List<Combatant>> initialEnemies,
                 Supplier<List<Combatant>> backupEnemies) {
        this.difficultyName = difficultyName;
        this.initialEnemies = initialEnemies;
        this.backupEnemies = backupEnemies;
    }

    public List<Combatant> getInitialEnemies() { return initialEnemies.get(); }
    public List<Combatant> getBackupEnemies()  { return backupEnemies.get(); }
    public String getDifficultyName()          { return difficultyName; }

    // Factory methods for each difficulty
    public static Level easy() {
        return new Level("Easy",
            () -> new ArrayList<>(List.of(new Goblin(), new Goblin(), new Goblin())),
            () -> Collections.emptyList());
    }

    public static Level medium() {
        return new Level("Medium",
            () -> new ArrayList<>(List.of(new Goblin(), new Wolf())),
            () -> new ArrayList<>(List.of(new Wolf(), new Wolf())));
    }

    public static Level hard() {
        return new Level("Hard",
            () -> new ArrayList<>(List.of(new Goblin(), new Goblin())),
            () -> new ArrayList<>(List.of(new Goblin(), new Wolf(), new Wolf())));
    }
}
