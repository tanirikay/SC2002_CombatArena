public interface EnemyAction {

    /**
     * Executes the enemy's chosen action during their turn.
     *
     * @param enemy  The enemy combatant taking the action
     * @param target The target combatant being acted upon
     * @return       A String describing what happened (for UI display)
     */
    String execute(Combatant enemy, Combatant target);
}
