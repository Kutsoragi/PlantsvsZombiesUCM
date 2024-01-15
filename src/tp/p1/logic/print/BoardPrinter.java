package tp.p1.logic.print;

import tp.p1.logic.Game;
import tp.p1.logic.print.GamePrinter;
import tp.p1.util.MyStringUtils;

public abstract class BoardPrinter implements GamePrinter {
	protected int dimX; 
	protected int dimY;
	protected Game game;
	
	String[][] board;
	final String space = " ";

	protected BoardPrinter(int x, int y) {
		this.dimX = x;
		this.dimY = y;
	}
	
	public String boardToString(int cellSize) {

		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (dimY * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
		
		str.append(lineDelimiter);
		
		for(int i=0; i<dimX; i++) {
				str.append(margin).append(vDelimiter);
				for (int j=0; j<dimY; j++) {
					str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				str.append(lineDelimiter);
		}
		return str.toString();
	}
	
	protected abstract void encodeGame(Game game);
}