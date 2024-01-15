package tp.p1.logic.objects.plants;

import tp.p1.logic.Game;
import tp.p1.logic.objects.Plant;

public class Peashooter extends Plant {
	
	private int nextTurn = 0;
	private static final String pName = "peashooter";
	private static final String pAbrv = "p";
	private static final String info = "Shoots zombies to protect the finish line.\n";
	private static final int damage = 1, cost = 50, maxHealth = 3, turns = 0;
	
	//----CONSTRUCTOR-------------------------------------//
	
	public Peashooter() {
		super(maxHealth, pAbrv, turns, cost);
	}
	
	//---------------------------------------------------//
	
	public void update() {
		if (!isDead()) {
			if (nextTurn == 0) {//Para evitar que obtenga en el primer update.
				nextTurn = game.getCurrentCycle() + turns;
			}
			if (nextTurn <= game.getCurrentCycle()) {
				for (int i = x + 1; i < Game.dimX; i++) {
					if (game.attackZombie(i, y, damage)) {
						break;
					}
				}
				nextTurn = game.getCurrentCycle() + turns;
			}
		}
	}
	
	public String listMsg() {
		return pName.toUpperCase() + " <" + cost + ">: " + info;
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
		return (abrv.toUpperCase() + "[hp:" + health + ",x:" + x + ",y:" + y + ",t:" + nextTurn + "]");
	}

	@Override
	public Plant parse(String name) {
		if (name.equalsIgnoreCase(pName) || name.equalsIgnoreCase(pAbrv)) {
			return new Peashooter();
		}
		return null;
	}
}