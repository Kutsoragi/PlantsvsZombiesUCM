package tp.p1;

import tp.p1.logic.Game;
import tp.p1.logic.Controller;
import tp.p1.logic.Level;

public class PlantsVsZombies {
	private final static boolean defaultAllowed = true; //If no arguments given
	
	public static void main(String[] args) {
		// [DONE] Setup args conditionals
	
		boolean canPlay = false;
		String printMode = "Release";
		Level level = Level.EASY;
		int seed = 0;
	
		//----ARGS CHECK--------------------------------------//
		
		if (args.length < 1) { // [[No arguments Given]]
			if (defaultAllowed) { // Default Level + Default Seed Allowed
				System.out.println("No arguments provided. Using <Default> settings: <LVL> EASY <SEED> 0.\n");
				canPlay = true;
			} 
			else { // No Default
				System.err.println("No arguments provided. Please use the following structure: <LEVEL> <SEED>.");
			}
		}
		else if (args.length == 1) { // [[Only Level Given]]
			if (defaultAllowed) { // Custom Level + Default Seed Allowed
				try {
					level = Level.valueOf(args[0].toUpperCase());
					System.out.println("No seed provided. Using <Default> settings: <SEED> 0.\n");
					canPlay = true;
				}
				catch (IllegalArgumentException ex) {
					System.err.println("Unknown level provided. Please provide a valid level.");
				}
			} 
			else { // No default
				System.err.println("No seed provided. Please use the following structure: <LEVEL> <SEED>.");
			}
		}
		else if (args.length == 2) { // [[Custom Level + Custom Seed]]
			try {
				level = Level.valueOf(args[0].toUpperCase());
				canPlay = true;
			}
			catch (IllegalArgumentException ex) {
				System.err.println("Unknown level provided. Please provide a valid level.");
			}
			try {
				seed = Integer.parseInt(args[1]);
			}
			catch (IllegalArgumentException ex) {
				if (canPlay) { canPlay = false; }; //If Level is valid and seed throws an exception, canPlay is disabled
				System.err.println("Invalid seed provided. Please provide a valid seed.");
			}
		}
		
		//----GAME INSTANCE---------------------------------------//
		
		if (canPlay) {
			Controller control	= new Controller(new Game(level, seed, printMode));
			
			control.run();
		}
	}
}