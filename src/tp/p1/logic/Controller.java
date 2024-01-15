package tp.p1.logic;

import java.util.Scanner;
import tp.p1.logic.Game;
import tp.p1.logic.print.DebugPrinter;
import tp.p1.logic.print.ReleasePrinter;
import tp.p1.logic.commands.Command;
import tp.p1.logic.commands.CommandParser;
import tp.p1.exceptions.*;
//import tp.p1.logic.managers.*;

public class Controller {
	private final int dimX = 8, dimY = 4;
	private Game game;
	private boolean canPrint = true;
	private String unknownCmdText = "ERROR: Unknown command provided. Type 'help' to see all available commands.\n";
	
	//----CONSTRUCTOR-------------------------------------//
	
	public Controller(Game game) {
		this.game = game;
	}
	
	//----------------------------------------------------//
	
	public Game getGame() {
		return this.game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public void disablePrint() {
		this.canPrint = false;
	}
	
	//----------------------------------------------------//
	
	public void drawGame() {		
		//---//PRINT INFO//-------------------------
		System.out.println("Current Cycle: " + game.getCurrentCycle());
		System.out.println("Sun Coins: " + game.getScManager().getCurrentSuncoins());
		System.out.println("Remaining Zombies: " + game.getzManager().getRemainingZombies());
		
		//---//PRINT GAME//-------------------------
		
		if (game.getPrintMode().equalsIgnoreCase("release")) {
			new ReleasePrinter(dimX, dimY).printGame(game);
		} 
		else if (game.getPrintMode().equalsIgnoreCase("debug")) {
			new DebugPrinter(game.getZombieList().zCount + game.getPlantList().pCount,1).printGame(game);
		}
	}
	
	//----------------------------------------------------//
	
	public void run() {
		
		Scanner sc = new Scanner(System.in);
		
		while (!game.hasFinished()) {	
			
			if (canPrint) {
				drawGame();
			}
			disablePrint();
			
			System.out.print("Command > ");
			String commandText = sc.nextLine();
			String [] args = commandText.toLowerCase().trim().split("\\s+");
			
			try {
				Command command = CommandParser.parseCommand(args);
				if (command != null) {
					if (command.execute(game))
					drawGame();
				}
				else {
					System.err.println(unknownCmdText);
					disablePrint();
				}
			} catch(CommandParseException | CommandExecuteException ex) {
				System.err.format(ex.getMessage() + " %n %n");
			}
		} 	
		
		sc.close();
	}
	
	//----------------------------------------------------//
	
}