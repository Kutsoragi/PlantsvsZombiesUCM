package tp.p1.logic.commands;

import tp.p1.exceptions.CommandParseException;
import tp.p1.logic.commands.list.*;

public class CommandParser {
	private static Command[] availableCommands = {
		new AddCommand(),
		new HelpCommand(),
		new ResetCommand(),
		new ExitCommand(),
		new ListCommand(),
		new ZombieListCommand(),
		new UpdateCommand(),
		new PrintMode(),
		new LoadCommand(),
		new SaveCommand(),
	};
	
	//Invokes parse() for each Command's subclass.
	public static Command parseCommand(String[ ] commandArgs) throws CommandParseException {
		Command command = null;
		for (Command c: availableCommands) {
			try {
				command = c.parse(commandArgs);
			}
			catch (CommandParseException ex) {
				throw new CommandParseException(ex.getMessage());
			}
			if (command != null) {break;}
		}
		return command;
	}
	
	//Invoked by execute() from HelpCommand and invokes helpText() from each Command's subclass.
	public static void commandHelp() {
		for (Command c: availableCommands) {
			System.out.println(c.helpText());
		}
	}
}