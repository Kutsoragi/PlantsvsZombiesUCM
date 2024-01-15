package tp.p1.logic.objects.plants;

import tp.p1.logic.objects.Plant;

public class Sunflower extends Plant {
	
	private int nextTurn = 0;
	private static final String pName = "sunflower";
	private static final String pAbrv = "s";
	private static final String info = "Generates 10 suncoins each 2 cycles.\n";
	private static final int maxHealth = 2, turns = 3, cost = 20, coinValue = 10;
	
	//----CONSTRUCTOR-------------------------------------//
	
	public Sunflower () {
		super(maxHealth, pAbrv, turns, cost);
	}
	
	//---------------------------------------------------//

	public void update() {
		if (!isDead()) {
			if (nextTurn == 0) {//Para evitar que obtenga en el primer update.
				nextTurn = game.getCurrentCycle() + turns;
			}
			else if (nextTurn <= game.getCurrentCycle()) { //If 2 turns pass, generates SunCoins
				game.generateSun(coinValue);
				nextTurn = game.getCurrentCycle() + turns;
			}
		}
	}
	
	public String listMsg() {
		return pName.toUpperCase() + " <" + cost + ">: " + info;
	}

	public String getInfo() {
		return (abrv.toUpperCase() + "[hp:" + health + ",x:" + x + ",y:" + y + ",t:" + nextTurn + "]");
	}

	public int getTurn() {
		return nextTurn;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setTurn(int turn) {
		this.nextTurn = turn;
	}

	@Override
	public Plant parse(String name) {
		if (name.equalsIgnoreCase(pName) || name.equalsIgnoreCase(pAbrv)) {
			return new Sunflower();
		}
		return null;
	}
}