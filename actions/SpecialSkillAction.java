package sc2002project.actions;

import sc2002project.BattleEngine;
import sc2002project.Combatant;
import sc2002project.characters.Warrior;
import sc2002project.characters.Wizard;
import sc2002project.effects.StunEffect;
import sc2002project.ui.GameUI;

public class SpecialSkillAction implements Action {
    @Override
    public void execute(Combatant actor, Combatant target, BattleEngine engine) {
        if (actor.getSpecialSkillCooldown() > 0) {
            GameUI.showActionResult("Special skill is on cooldown! (" + actor.getSpecialSkillCooldown() + " turns remaining)");
            return;
        }
        if (actor instanceof Warrior) {
            // Shield Bash: deal BasicAttack damage and stun the target
            int dmg = Math.max(0, actor.getAttack() - target.getDefense());
            target.takeDamage(dmg);
            StunEffect stun = new StunEffect();
            stun.apply(target);
            target.addStatusEffect(stun);
            GameUI.showActionResult(actor.getName() + " uses Shield Bash on " + target.getName() +
                    " for " + dmg + " damage! " + target.getName() + " is stunned!");
        } else if (actor instanceof Wizard) {
            ((Wizard) actor).executeSpecialSkillEffect(engine);
            GameUI.showActionResult(actor.getName() + " unleashes Arcane Blast on all enemies!");
        }
        actor.setSpecialSkillCooldown(3);
    }
}
