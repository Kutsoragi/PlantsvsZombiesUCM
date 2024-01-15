package tp.p1.logic.objects.zombies;

import tp.p1.logic.objects.Zombie;

public class SportyZombie extends Zombie {
	private int nextTurn = 0;
	private static final String zName = "sporty zombie";
	private static final String zAbrv = "x";
	private static final String zID = "1";
	private static final String info = "Fast zombie with low resistance.";
	private static final int damage = 1, move = 1, maxHealth = 2;
	
	//----CONSTRUCTOR-------------------------------------//
	
	public SportyZombie () {
		super(maxHealth, zAbrv, move);
	}
	
	//---------------------------------------------------//
	
	public void update() {
		if (!isDead()) {
			if (game.attackPlant(x - 1, y, damage)) {
				nextTurn = game.getCurrentCycle() + move;
			}
			else if (nextTurn <= game.getCurrentCycle() && game.isPositionEmpty(x - 1, y)) {
				nextTurn = game.getCurrentCycle() + move;
				x--;
			}	
		}
	}

	public String listMsg() {
		return zName.toUpperCase() + ": " + info;
	}

	public int getTurn() {
		return nextTurn;
	}
	
	public void setTurn(int turn) {
		this.nextTurn = turn;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public String getInfo() {
		return (abrv.toUpperCase() + "[hp:" + health + ",x:" + x + ",y:" + y + ",t:" + turns + "]");
	}

	@Override
	public Zombie parse(String name) {
		if (name.equalsIgnoreCase(zName) || name.equalsIgnoreCase(zAbrv) || name.equals(zID)) {
			return new SportyZombie();
		}
		return null;
	}
}