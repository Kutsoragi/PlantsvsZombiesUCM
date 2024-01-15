package tp.p1.logic.commands.list;

import tp.p1.exceptions.CommandParseException;
import tp.p1.logic.Game;
import tp.p1.logic.factories.ZombieFactory;
import tp.p1.logic.commands.Command;

public class ZombieListCommand extends Command {
	protected static String commandText = "ZombieList";
	protected static String commandInfo = "[Z]ombieList";
	protected static String helpText = "Shows a list of all existing zombies.";
	
	public ZombieListCommand() {
		super(commandText, commandInfo, helpText);
	}

	@Override
	public boolean execute(Game game) {
		System.out.print("\nList of available zombies:\n" + ZombieFactory.listOfAvailableZombies() + "\n");
		return false;
	}

	@Override
	public Command parse(String[] args) throws CommandParseException {
		if (args.length > 0 && (args[0].equalsIgnoreCase(commandName) || args[0].equalsIgnoreCase("z"))) {
			if (args.length > 1) {
				throw new CommandParseException("ERROR: Arguments limit exceeded. Please use the following structure: EXIT");
			}
			return this;
		}
		return null;
	}

}