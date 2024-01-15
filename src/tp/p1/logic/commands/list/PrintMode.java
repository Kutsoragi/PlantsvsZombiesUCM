package tp.p1.logic.commands.list;

import tp.p1.exceptions.CommandExecuteException;
import tp.p1.exceptions.CommandParseException;
import tp.p1.logic.Game;
import tp.p1.logic.commands.Command;

public class PrintMode extends Command {
	protected static String commandText = "PrintMode";
	protected static String commandInfo = "[P]rintMode <Mode>";
	protected static String helpText = "Lets you select a specific printing mode.";
	private String printMode;
	
	public PrintMode() {
		super(commandText, commandInfo, helpText);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		switch (printMode) {
			case "release":
			case "r":
			case "debug":
			case "d":
				game.setPrintMode(printMode);
				return true; //Allow printing
			default:
				throw new CommandExecuteException("Unknown print mode provided. Please provide a valid mode.");
		}
	}

	@Override
	public Command parse(String[] args) throws CommandParseException {

		if (args.length > 0 && (args[0].equalsIgnoreCase(commandName) || args[0].equalsIgnoreCase("p"))) {
			if (args.length > 1) {
				printMode = args[1];
			} else {
				throw new CommandParseException("ERROR: Mode not provided. Please use the following structure: PRINTMODE <mode>");
			}
			return this;
		}
		return null;
	}

}