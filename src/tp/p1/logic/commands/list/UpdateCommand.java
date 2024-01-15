package tp.p1.logic.commands.list;

import tp.p1.exceptions.CommandParseException;
import tp.p1.logic.Game;
import tp.p1.logic.commands.Command;

public class UpdateCommand extends Command{
	protected static String commandText = "None";
	protected static String commandInfo = "[N]one";
	protected static String helpText = "Updates the game by going to the next cycle.";
	
	public UpdateCommand() {
		super(commandText, commandInfo, helpText);
	}

	@Override
	public boolean execute(Game game) {
		game.update();
		game.nextCycle();
		return true;
	}

	@Override
	public Command parse(String[] args) throws CommandParseException {
		if (args.length > 0 && (args[0].equalsIgnoreCase(commandName) || args[0].equalsIgnoreCase("n") || args[0].equals(""))) {
			if (args.length > 1) {
				throw new CommandParseException("ERROR: Arguments limit exceeded. Please use the following structure: EXIT");
			}
			return this;
		}
		return null;
	}
}