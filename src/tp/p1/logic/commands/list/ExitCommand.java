package tp.p1.logic.commands.list;

import tp.p1.logic.Game;
import tp.p1.logic.commands.Command;
import tp.p1.exceptions.CommandParseException;


public class ExitCommand extends Command{
	protected static String commandText = "Exit";
	protected static String commandInfo = "[E]xit";
	protected static String helpText = "Exits the game.";
	
	public ExitCommand() {
		super(commandText, commandInfo, helpText);
	}

	@Override
	public boolean execute(Game game) {
		game.exitGame();
		System.out.println("\nTermination ordered: GAME OVER");
		return false;
	}

	@Override
	public Command parse(String[] args) throws CommandParseException {
		if (args.length > 0 && (args[0].equalsIgnoreCase(commandName) || args[0].equalsIgnoreCase("e"))) {
			if (args.length > 1) {
				throw new CommandParseException("ERROR: Arguments limit exceeded. Please use the following structure: EXIT");
			}
			return this;
		}
		return null;
	}

}