package sc2002project.characters;

import java.util.List;
import sc2002project.BattleEngine;
import sc2002project.Combatant;
import sc2002project.actions.BasicAttack;
import sc2002project.actions.DefendAction;
import sc2002project.actions.ItemAction;
import sc2002project.actions.SpecialSkillAction;
import sc2002project.items.Item;
import sc2002project.ui.GameUI;

public class Warrior extends Combatant {
    private List<Item> inventory;

    public Warrior(List<Item> inventory) {
        // Stats: HP: 260, Attack: 40, Defense: 20, Speed: 30
        super("Warrior", 260, 40, 20, 30);
        this.inventory = inventory;
    }

    @Override
    public List<Item> getInventory() { return inventory; }

    @Override
    public void performTurn(BattleEngine engine) {
        // Decrement cooldown at the start of each turn
        if (specialSkillCooldown > 0) specialSkillCooldown--;

        GameUI.showBattleStatus(engine.getPlayers());
        GameUI.showBattleStatus(engine.getEnemies());

        boolean hasItems = !inventory.isEmpty();
        boolean skillReady = specialSkillCooldown == 0;
        int choice = GameUI.showPlayerActions(hasItems, skillReady);

        switch (choice) {
            case 1: {
                // Basic Attack — pick a target
                List<Combatant> targets = engine.getLivingEnemies();
                int targetIndex = GameUI.showTargetMenu(targets);
                Combatant target = targets.get(targetIndex);
                new BasicAttack().execute(this, target, engine);
                break;
            }
            case 2: {
                // Defend — no target needed
                new DefendAction().execute(this, this, engine);
                break;
            }
            case 3: {
                // Special Skill (Shield Bash) — pick a target
                List<Combatant> targets = engine.getLivingEnemies();
                int targetIndex = GameUI.showTargetMenu(targets);
                Combatant target = targets.get(targetIndex);
                new SpecialSkillAction().execute(this, target, engine);
                break;
            }
            case 4: {
                // Use Item
                if (!hasItems) {
                    GameUI.showActionResult("No items available!");
                    performTurn(engine); // re-prompt
                    return;
                }
                int itemIndex = GameUI.showItemMenu(inventory);
                new ItemAction(inventory.get(itemIndex), inventory).execute(this, this, engine);
                break;
            }
            default:
                GameUI.showActionResult("Invalid choice — turn skipped.");
        }
    }
}
