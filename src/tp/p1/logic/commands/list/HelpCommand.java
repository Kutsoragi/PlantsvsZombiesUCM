package tp.p1.logic.commands.list;

import tp.p1.logic.Game;
import tp.p1.logic.commands.Command;
import tp.p1.logic.commands.CommandParser;
import tp.p1.exceptions.CommandParseException;


public class HelpCommand extends Command{
	protected static String commandText = "Help";
	protected static String commandInfo = "[H]elp";
	protected static String helpText = "Shows a list of all available commands.";
	
	public HelpCommand() {
		super(commandText, commandInfo, helpText);
	}

	@Override
	public boolean execute(Game game) {
		System.out.print("\nAvailable commands:\n");
		CommandParser.commandHelp();
		System.out.print("\n");
		return false;
	}

	@Override
	public Command parse(String[] args) throws CommandParseException {
		if (args.length > 0 && (args[0].equalsIgnoreCase(commandName) || args[0].equalsIgnoreCase("h"))) {
			if (args.length > 1) {
				throw new CommandParseException("ERROR: Arguments limit exceeded. Please use the following structure: HELP");
			}
			return this;
		} 
		return null;
	}

}