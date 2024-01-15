package tp.p1.logic.objects;

import java.io.BufferedWriter;
import java.io.IOException;

public abstract class Plant extends GameObject {
	private int cost;
	
	//----CONSTRUCTOR-------------------------------------//
	
	public Plant (int health, String abrv, int turns, int cost) {
		super(health, abrv, turns);
		this.cost = cost;
	}
	
	//---------------------------------------------------//
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void takeDamage(int dmg) {
		health -= dmg;
	}
	
	public boolean isDead() {
		return (health <= 0);
	}
	public String toString() {
		return abrv.toUpperCase();
	}
	public void store(BufferedWriter writer) throws IOException {
		writer.write(abrv + ":" + health + ":" + x + ":" + y + ":" + this.getTurn());
	}
	
	public abstract String listMsg();
	public abstract int getTurn();
	public abstract void setTurn(int turn);
	public abstract int getMaxHealth();
	public abstract Plant parse(String plantName);
}