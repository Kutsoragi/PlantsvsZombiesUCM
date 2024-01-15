package tp.p1.logic.objects;

import java.io.BufferedWriter;
import java.io.IOException;

import tp.p1.logic.Game;

public abstract class Zombie extends GameObject {
	
	//----CONSTRUCTOR-------------------------------------//
	
	public Zombie (int health, String abrv, int turns) {
		super(health, abrv, turns);
	}
	
	//---------------------------------------------------//
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	public void setGame(Game game) {
		this.game = game;
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
	
	public boolean hasWon() {
		return (x == 0);
	}
	
	public void store(BufferedWriter writer) throws IOException {
		writer.write(abrv + ":" + health + ":" + x + ":" + y + ":" + this.getTurn());
	}
	
	public abstract int getTurn();
	public abstract void setTurn(int turn);
	public abstract int getMaxHealth();
	public abstract String listMsg();
	public abstract Zombie parse(String zombieName);
}