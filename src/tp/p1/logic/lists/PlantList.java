package tp.p1.logic.lists;

import java.io.BufferedWriter;
import java.io.IOException;

import tp.p1.logic.objects.Plant;

public class PlantList {
	
	private Plant [] plantList;
	public int pCount = 0;
	
	//----CONSTRUCTOR-------------------------------------//
	
	public PlantList(int size) {
		plantList = new Plant [size];
	}
	
	//---------------------------------------------------//
	
	public void update(){
		for (int i = 0; i < pCount; i++) {
			plantList[i].update();
		}
		clearDead();
	}
	
	public void clearDead() {
		for (int plant = 0; plant < pCount; plant++) {
			if (plantList[plant].isDead()) {
				for (int oldPlant = plant; oldPlant < pCount - 1; oldPlant++) {
					plantList[oldPlant] = plantList[oldPlant+1];
				}
				plantList[pCount] = null;
				pCount--;
			}
		}
	}
	
	public boolean addObject(Plant plant) {
		if (pCount < plantList.length) {
			plantList[pCount] = plant;
			pCount++;
			return true;
		}
		return false;
	}
	
	public Plant getPlant(int x, int y) {
		Plant pInPos = null;

		for (int i = 0; i < pCount; i++) {
			if (plantList[i] != null && !plantList[i].isDead()) {
				if (plantList[i].getX() == x && plantList[i].getY() == y) {
					pInPos = plantList[i];
					break;
				}
			}
		}
		return pInPos;
	}
	public void store(BufferedWriter writer) throws IOException {
		writer.write("plantList: ");
		if (pCount > 0) {//Para que no pete al hacer el metodo de debajo(***)
			for (int i = 0; i < pCount - 1; i++) {
				plantList[i].store(writer);
				writer.write(", ");
			}
			plantList[pCount - 1].store(writer);//***Lo saco para evitar la coma que se crea.
		}
	}
	
	public String retrieveInfo(int index) {
		return plantList[index].getInfo();
	}
}