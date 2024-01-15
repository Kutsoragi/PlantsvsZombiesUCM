package tp.p1.logic.managers;

import java.util.Random;

public class ZombieManager {
	
	private int remainingZombies;
	private Random rand;

	//----CONSTRUCTOR-------------------------------------//
	
	public ZombieManager(Random rand, int remainingZombies) {
		this.rand = rand;
		this.remainingZombies = remainingZombies;
	}

	//----GETTERS & SETTERS-------------------------------//	
	
	public int getRemainingZombies() {
		return remainingZombies;
	}

	public void setRemainingZombies(int remainingZombies) {
		this.remainingZombies = remainingZombies;
	}

	//----METHODS----------------------------------------//

	public boolean isZombieAdded(float frequency) {
		boolean canAdd = false;
		
		if (this.remainingZombies > 0) { //Security Check
			float probability =  (float) (Math.round(rand.nextFloat()*100.0)/100.0); //Sets precision to 1 decimal
			//System.out.println("Probability -" + probability + " | Frequency -" + frequency + " | " + (probability <= frequency));
			
			if (probability <= frequency) { 
				canAdd = true;
			}
		}
		
		return canAdd;
	}
	
	public void updateRemainingZombies() {
		remainingZombies--;
	}
	
	//---------------------------------------------------//
}