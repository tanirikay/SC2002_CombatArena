package sc2002project;

public class Warrior extends Combatant{
	public Warrior() {
        // Stats: HP: 260, Attack: 40, Defense: 20, Speed: 30
        super("Warrior", 260, 40, 20, 30);
    }

    @Override
    public void performTurn(BattleEngine engine) {
        // UI Lead will implement the menu here to choose actions 
    }

}
