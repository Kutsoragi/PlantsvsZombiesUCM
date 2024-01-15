package tp.p1.logic.print;

import tp.p1.logic.Game;
import tp.p1.logic.print.BoardPrinter;

public class DebugPrinter extends BoardPrinter {

	private int cellSize = 19;
	
	public DebugPrinter(int dimX, int dimY) {
		super(dimY, dimX);
	}


	@Override
	protected void encodeGame(Game game) {
		board = new String[dimX][dimY];
		
		int currentX = 0;
		for (int plant = 0; plant < game.getPlantList().pCount; plant++) {
			board[0][currentX] = game.getPlantList().retrieveInfo(plant);
			currentX++;
		}
		for (int zombie = 0; zombie < game.getZombieList().zCount; zombie++) {
			board[0][currentX] = game.getZombieList().retrieveInfo(zombie);
			currentX++;
		}
	}

	@Override
	public void printGame(Game game) {
		encodeGame(game);
		
		System.out.println("Level: " + game.getLevel());
		System.out.println("Seed: " + game.getSeed());
		System.out.println(boardToString(cellSize));
	}
}