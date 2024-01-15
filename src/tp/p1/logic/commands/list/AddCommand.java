package tp.p1.logic.commands.list;

import tp.p1.logic.Game;
import tp.p1.logic.objects.Plant;
import tp.p1.logic.commands.Command;
import tp.p1.logic.factories.PlantFactory;
import tp.p1.exceptions.CommandExecuteException;
import tp.p1.exceptions.CommandParseException;

public class AddCommand extends Command{
	protected static String commandText = "Add";
	protected static String commandInfo = "[A]dd <Plant> <X> <Y>";
	protected static String helpText = "Adds a plant at (x,y).";
	private int x, y;
	private String plantName;
	
	public AddCommand() {
		super(commandText, commandInfo, helpText);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {

		Plant plant = PlantFactory.parsePlant(plantName);
		if (plant == null) {
			throw new CommandExecuteException("ERROR: Invalid object provided.");
		}
		else {
			String output = game.addPlantToGame(plant, x, y);
			if (output != "Successful") {
				throw new CommandExecuteException(output);
			}
		}
		
		new UpdateCommand().execute(game);
		return true;
	}

	@Override
	public Command parse(String[] args) throws CommandParseException {
		if (args.length > 0 && (args[0].equalsIgnoreCase(commandName) || args[0].equalsIgnoreCase("a"))) {
			if (args.length > 1) {
				plantName = args[1];	
				if (args.length == 4) {
					try {
						x = Integer.parseInt(args[2]);
						y = Integer.parseInt(args[3]);
					}
					catch (Exception ex) {
						throw new CommandParseException("ERROR: Invalid coordinates provided. Please use numeric values.\n");
					}
				} else if (args.length < 4) {
					throw new CommandParseException("ERROR: Coordinates missing. Please use the following structure: ADD <plant> <x> <y>");
				} else if (args.length > 4) {
					throw new CommandParseException("ERROR: Arguments limit exceeded. Please use the following structure: ADD <plant> <x> <y>");
				}
			} else {
				throw new CommandParseException("ERROR: Plant name missing. Please use the following structure: ADD <plant> <x> <y>");
			}
			return this;
		}
		return null;
	}
}