package tp.p1.logic.objects.zombies;

import tp.p1.logic.objects.Zombie;

public class CommonZombie extends Zombie {
	private int nextTurn = 0;
	private static final String zName = "common zombie";
	private static final String zAbrv = "z";
	private static final String zID = "0";
	private static final String info = "Average zombie.";
	private static final int damage = 1, move = 2, maxHealth = 3;
	
	//----CONSTRUCTOR-------------------------------------//
	
	public CommonZombie () {
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
			return new CommonZombie();
		}
		return null;
	}
}