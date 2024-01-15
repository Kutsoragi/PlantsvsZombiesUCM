package tp.p1.logic.commands.list;

import tp.p1.exceptions.CommandParseException;
import tp.p1.logic.Game;
import tp.p1.logic.factories.PlantFactory;
import tp.p1.logic.commands.Command;

public class ListCommand extends Command{
	protected static String commandText = "List";
	protected static String commandInfo = "[L]ist";
	protected static String helpText = "Shows a list of all available plants.";
	
	public ListCommand() {
		super(commandText, commandInfo, helpText);
	}

	@Override
	public boolean execute(Game game) {
		System.out.print("\nList of available plants:\n" + PlantFactory.listOfAvilablePlants() + "\n");
		return false;
	}

	@Override
	public Command parse(String[] args) throws CommandParseException {
		if (args.length > 0 && (args[0].equalsIgnoreCase(commandName) || args[0].equalsIgnoreCase("l"))) {
			if (args.length > 1) {
				throw new CommandParseException("ERROR: Arguments limit exceeded. Please use the following structure: LIST");
			}
			return this;
		}
		return null;
	}

}