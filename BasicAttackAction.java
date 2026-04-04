
public class BasicAttackAction implements EnemyAction {

    @Override
    public String execute(Combatant enemy, Combatant target) {
        int rawDamage = Math.max(0, enemy.getAttack() - target.getDefense());
        int hpBefore = target.getHp();

        target.takeDamage(rawDamage);

        int hpAfter = target.getHp();
        int actualDamage = hpBefore - hpAfter;

        String result = String.format(
            "%s → BasicAttack → %s: HP %d → %d (dmg: %d−%d=%d)",
            enemy.getName(),
            target.getName(),
            hpBefore,
            hpAfter,
            enemy.getAttack(),
            target.getDefense(),
            actualDamage
        );

        if (!target.isAlive()) {
            result += " ✗ ELIMINATED";
        }

        return result;
    }
}
