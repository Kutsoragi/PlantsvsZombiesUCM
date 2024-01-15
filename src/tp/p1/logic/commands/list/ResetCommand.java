package tp.p1.logic.commands.list;

import tp.p1.exceptions.CommandParseException;
import tp.p1.logic.Game;
import tp.p1.logic.commands.Command;

public class ResetCommand extends Command{
	protected static String commandText = "Reset";
	protected static String commandInfo = "[R]eset";
	protected static String helpText = "Resets the game.";
	
	public ResetCommand() {
		super(commandText, commandInfo, helpText);
	}

	@Override
	public boolean execute(Game game) {
		//controller.setGame(new Game(game.getLevel(), game.getSeed()));
		return true;
	}

	@Override
	public Command parse(String[] args) throws CommandParseException {
		if (args.length > 0 && (args[0].equalsIgnoreCase(commandName) || args[0].equalsIgnoreCase("r"))) {
			if (args.length > 1) {
				throw new CommandParseException("ERROR: Arguments limit exceeded. Please use the following structure: EXIT");
			}
			return this;
		}
		return null;
	}

}