package tp.p1.logic.objects.plants;

import tp.p1.logic.objects.Plant;

public class Cherrybomb extends Plant {
	private int nextTurn = 0;
	private static final String pName = "cherrybomb";
	private static final String pAbrv = "c";
	private static final String info = "Explodes and affects all surrounding zombies.\n";
	private static final int cost = 50, maxHealth = 2, damage = 10, turns = 3;
	
	//----CONSTRUCTOR-------------------------------------//
	
	public Cherrybomb () {
		super(maxHealth, pAbrv, turns, cost);
	}
	
	//---------------------------------------------------//

	public void update() {
		if (!isDead()) {
			if (nextTurn == 0) {//Para evitar que obtenga en el primer update.
				nextTurn = game.getCurrentCycle() + turns;
			}
			else if (nextTurn <= game.getCurrentCycle()) { //If 2 turns pass, KABOOM!!
				for (int i = x - 1; i <= x + 1; i++) {
					for(int j = y - 1; j <= y + 1; j++) {
						if (game.attackZombie(i, j, damage)) {
							this.setHealth(0);
						}
					}
				}
			}
		}
	}
	
	public String listMsg() {
		return pName.toUpperCase() + " <" + cost + ">: " + info;
	}

	@Override
	public String getInfo() {
		return (abrv.toUpperCase() + "[hp:" + health + ",x:" + x + ",y:" + y + ",t:" + nextTurn + "]");
	}

	@Override
	public int getTurn() {
		return nextTurn;
	}

	public void setTurn(int turn) {
		this.nextTurn = turn;
	}
	public int getMaxHealth() {
		return maxHealth;
	}

	@Override
	public Plant parse(String name) {
		if (name.equalsIgnoreCase(pName) || name.equalsIgnoreCase(pAbrv)) {
			return new Cherrybomb();
		}
		return null;
	}
}