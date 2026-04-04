package sc2002project.actions;

import java.util.List;
import sc2002project.BattleEngine;
import sc2002project.Combatant;
import sc2002project.items.Item;
import sc2002project.ui.GameUI;

public class ItemAction implements Action {
    private Item item;
    private List<Item> inventory;

    public ItemAction(Item item, List<Item> inventory) {
        this.item = item;
        this.inventory = inventory;
    }

    @Override
    public void execute(Combatant actor, Combatant target, BattleEngine engine) {
        item.use(actor, engine);
        inventory.remove(item);
        GameUI.showActionResult(actor.getName() + " used " + item.getName() + "!");
    }
}
