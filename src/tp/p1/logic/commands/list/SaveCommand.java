package tp.p1.logic.commands.list;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import tp.p1.exceptions.CommandParseException;
import tp.p1.logic.Game;
import tp.p1.logic.commands.Command;

public class SaveCommand extends Command {
	protected static String commandText = "Save";
	protected static String commandInfo = "[S]ave <file name>";
	protected static String helpText = "Save the state of the game to a file.";
	private String fileName;
	
	public SaveCommand() {
		super(commandText, commandInfo, helpText);
	}

	@Override
	public boolean execute(Game game) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.write("Plants Vs Zombies v3.0");
			writer.newLine();
			writer.newLine();
			game.store(writer);
			writer.close();
			System.out.println("Game has been successfully saved at '" + fileName + "'. Use the LOAD command to load this save.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Command parse(String[] args) throws CommandParseException {
		if (args.length > 1 && (args[0].equalsIgnoreCase(commandName) || args[0].equalsIgnoreCase("s"))) {
			if (args.length == 2) {
				if (!args[1].endsWith(".dat")) {
					fileName = args[1] + ".dat";	
				} else {
					fileName = args[1];
				}
			} else if (args.length < 2) {
				throw new CommandParseException("ERROR: File name not provided. Please use the following structure: SAVE <file name>");
			} else if (args.length > 2) {
				throw new CommandParseException("ERROR: Arguments limit exceeded. Please use the following structure: SAVE <file name>");
			}
			return this;
		}
		return null;
	}
}