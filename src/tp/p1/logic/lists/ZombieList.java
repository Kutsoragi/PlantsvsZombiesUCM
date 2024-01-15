package tp.p1.logic.lists;

import java.io.BufferedWriter;
import java.io.IOException;

import tp.p1.logic.objects.Zombie;

public class ZombieList {
	
	private Zombie[] zombieList;
	public int zCount = 0;
	
	//----CONSTRUCTOR-------------------------------------//
	
	public ZombieList(int size) {
		zombieList = new Zombie [size];
	}
	
	//---------------------------------------------------//
	
	public void update(){
		for (int i = 0; i < zCount; i++) {
			zombieList[i].update();
		}
	}
	
	public void clearDead() {
		for (int zombie = 0; zombie < zCount; zombie++) {
			if (zombieList[zombie].isDead()) {
				for (int oldZombie = zombie; oldZombie < zCount - 1; oldZombie++) {
					zombieList[oldZombie] = zombieList[oldZombie+1];
				}
				zombieList[zCount] = null;
				zCount--;
			}
		}
	}
	
	public boolean hasFinished(){ //Checks if any zombie arrived alive
		boolean finished = false;
		for (int i = 0; i < zCount; i++) {
			if (zombieList[i].getX() == 0 && !zombieList[i].isDead()) {
				finished = true;
				break;
			}
		}
		return finished;
	}
	
	public boolean hasAlive() { //Checks if theres any zombie alive in-game
		boolean alive = false;
		for (int i = 0; i < zCount; i++) {
			if (!zombieList[i].isDead()) {
				alive = true; //At least 1 zombie alive, Player hasn't won yet
				break;
			}
		}
		return alive;
	}
	
	public boolean addObject(Zombie zombie) {
		if (zCount < zombieList.length) {
			zombieList[zCount] = zombie;
			zCount++;
			return true;
		}
		return false;
	}
	
	public Zombie getZombie(int x, int y) {
		Zombie zInPos = null;
		
		for (int i = 0; i < zCount; i++) {
			if (zombieList[i] != null && !zombieList[i].isDead()) {
				if (zombieList[i].getX() == x && zombieList[i].getY() == y) {
					zInPos = zombieList[i];
					break;
				}
			}
		}
		return zInPos;
	}
	public void store(BufferedWriter writer) throws IOException {
		writer.write("zombieList: ");
		if (zCount > 0) {//Para que no pete al hacer el metodo de debajo(***)
			for (int i = 0; i < zCount - 1; i++) {
				zombieList[i].store(writer);
				writer.write(", ");
			}
			zombieList[zCount - 1].store(writer);//***Lo saco para evitar la coma que se crea.
		}
	}
	
	public String retrieveInfo(int index) {
		return zombieList[index].getInfo();
	}
}