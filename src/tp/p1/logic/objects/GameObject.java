package tp.p1.logic.objects;

import tp.p1.logic.Game;

public abstract class GameObject {

	protected int x, y, health, turns;
	protected String abrv;
	protected Game game;
		
	public GameObject(int health, String abrv, int turns) {
		this.health = health;
		this.abrv = abrv;
		this.turns = turns;
	}
		
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
		
	public void setGame(Game game) {
		this.game = game;
	}
	
	public abstract String getInfo();
	public abstract void update();
}