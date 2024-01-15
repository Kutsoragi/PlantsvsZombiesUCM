package tp.p1.logic.print;

import tp.p1.logic.Game;
import tp.p1.logic.objects.Plant;
import tp.p1.logic.objects.Zombie;
import tp.p1.logic.print.BoardPrinter;

public class ReleasePrinter extends BoardPrinter {

	private int cellSize = 7;
	
	public ReleasePrinter(int dimX, int dimY) {
		super(dimY, dimX);
	}

	@Override
	protected void encodeGame(Game game) {
		board = new String[dimX][dimY];
		for(int i = 0; i < dimX; i++) {
			for(int j = 0; j < dimY; j++) {

				board[i][j] =  space;
				
				// [DONE] Fill the board with simulation objects
				Plant possiblePlant	= game.getPinPosition(j, i);
				Zombie possibleZombie = game.getZinPosition(j, i);
				
				if (possiblePlant != null) {
					board[i][j] = possiblePlant.toString() + "[" + possiblePlant.getHealth() + "]";
				}
				else if (possibleZombie != null) {
					board[i][j] = possibleZombie.toString() + "[" + possibleZombie.getHealth() + "]";
				}
			}
		}
	}

	@Override
	public void printGame(Game game) {
		encodeGame(game);
		System.out.println(boardToString(cellSize));
	}

}