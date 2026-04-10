package sc2002project.characters;

import java.util.List;
import sc2002project.BattleEngine;
import sc2002project.Combatant;
import sc2002project.actions.BasicAttack;
import sc2002project.actions.DefendAction;
import sc2002project.actions.ItemAction;
import sc2002project.actions.SpecialSkillAction;
import sc2002project.effects.ArcaneBlastBuff;
import sc2002project.items.Item;
import sc2002project.ui.GameUI;

public class Wizard extends Combatant {
    private List<Item> inventory;

    public Wizard(List<Item> inventory) {
        // Stats: HP: 200, Attack: 50, Defense: 10, Speed: 20
        super("Wizard", 200, 50, 10, 20);
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
                // Special Skill (Arcane Blast) — hits all enemies, no target selection
                new SpecialSkillAction().execute(this, null, engine);
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

    // Called by SpecialSkillAction and PowerStone (bypasses cooldown check)
    public void executeSpecialSkillEffect(BattleEngine engine) {
        List<Combatant> enemies = engine.getLivingEnemies();
        for (Combatant enemy : enemies) {
            int dmg = Math.max(0, this.getAttack() - enemy.getDefense());
            enemy.takeDamage(dmg);
            if (!enemy.isAlive()) {
                ArcaneBlastBuff buff = new ArcaneBlastBuff();
                buff.apply(this);
                this.addStatusEffect(buff);
            }
        }
    }
}
