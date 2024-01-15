package tp.p1.logic.factories;

import tp.p1.logic.objects.Zombie;
import tp.p1.logic.objects.zombies.*;

public class ZombieFactory {
	private static Zombie[] availableZombies = {
		new CommonZombie(),
		new SportyZombie(),
		new BucketheadZombie()
	};
	
	public static Zombie parseZombie(String zombieName){
		Zombie zombie = null;
		for (Zombie z: availableZombies) {
			zombie = z.parse(zombieName);
			
			if (zombie != null) {break;}
		}
		return zombie;
	}
	
	public static String listOfAvailableZombies() {
		StringBuilder sb = new StringBuilder();
			
		for(Zombie z: availableZombies) {
			sb.append(String.format("    " + z.listMsg() + "%n"));
		}
		
		return sb.toString();
	}
	
	public static int getNumberTypes() {
		return availableZombies.length;
	}
}