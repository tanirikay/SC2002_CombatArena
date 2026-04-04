package sc2002project.items;

import java.util.List;
import sc2002project.BattleEngine;
import sc2002project.Combatant;
import sc2002project.characters.Warrior;
import sc2002project.characters.Wizard;
import sc2002project.effects.StunEffect;
import sc2002project.ui.GameUI;

public class PowerStone implements Item {
    @Override
    public void use(Combatant user, BattleEngine engine) {
        // Triggers the special skill effect without touching cooldown
        if (user instanceof Warrior) {
            // Shield Bash: pick a target, deal damage, apply stun
            List<Combatant> targets = engine.getLivingEnemies();
            int targetIndex = GameUI.showTargetMenu(targets);
            Combatant target = targets.get(targetIndex);
            int dmg = Math.max(0, user.getAttack() - target.getDefense());
            target.takeDamage(dmg);
            StunEffect stun = new StunEffect();
            stun.apply(target);
            target.addStatusEffect(stun);
            GameUI.showActionResult(user.getName() + " used Power Stone — Shield Bash on " + target.getName() +
                    " for " + dmg + " damage! " + target.getName() + " is stunned!");
        } else if (user instanceof Wizard) {
            ((Wizard) user).executeSpecialSkillEffect(engine);
            GameUI.showActionResult(user.getName() + " used Power Stone — Arcane Blast triggered for free!");
        }
    }

    @Override
    public String getName() {
        return "Power Stone";
    }
}
