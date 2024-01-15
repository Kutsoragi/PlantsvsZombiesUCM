package tp.p1.logic.factories;

import tp.p1.logic.objects.Plant;
import tp.p1.logic.objects.plants.*;

public class PlantFactory {
	private static Plant[] availablePlants = {
		new Sunflower(),
		new Peashooter(),
		new Cherrybomb(),
		new Wallnut()
	};

	public static Plant parsePlant(String plantName){
		Plant plant = null;
		for (Plant p: availablePlants) {
			plant = p.parse(plantName);
			
			if (plant != null) {break;}
		}
		return plant;
	}
	
	public static String listOfAvilablePlants() {
		StringBuilder sb = new StringBuilder();
		
		for (Plant p: availablePlants) { 
			sb.append("   " + String.format(p.listMsg()));
		}
		
		return sb.toString();
	}
}