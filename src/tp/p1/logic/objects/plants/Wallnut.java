package tp.p1.logic.objects.plants;

import tp.p1.logic.objects.Plant;

public class Wallnut extends Plant{
	
	private int nextTurn = 0;
	private static final String pName = "wallnut";
	private static final String pAbrv = "n";
	private static final String info = "Acts as a wall that holds back the zombies.\n";
	private static final int maxHealth = 10, turns = 0, cost = 50;
	
	//----CONSTRUCTOR-------------------------------------//
	
	public Wallnut() {
		super(maxHealth, pAbrv, turns, cost);
	}
	
	//---------------------------------------------------//

	public void update() {
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
			return new Wallnut();
		}
		return null;
	}
}