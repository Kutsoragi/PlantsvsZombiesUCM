package tp.p1.logic.commands.list;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import tp.p1.exceptions.CommandParseException;
import tp.p1.exceptions.FileContentsException;
import tp.p1.logic.Game;
import tp.p1.logic.commands.Command;
import tp.p1.util.MyFileUtils;;

public class LoadCommand extends Command {
	protected static String commandText = "Load";
	protected static String commandInfo = "[Lo]ad <file_name>";
	protected static String helpText = "Load the state of the game from a file.";
	private String fileName;
	
	public LoadCommand() {
		super(commandText, commandInfo, helpText);
	}

	@Override
	public boolean execute(Game game) {
		if (MyFileUtils.isReadable(fileName)) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(fileName));
				if (reader.readLine().equalsIgnoreCase("Plants Vs Zombies v3.0")) {
					reader.readLine();
					game.load(reader);
					//controller.allowPrint();
					return true;
				}
				reader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileContentsException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return true;
	}

	@Override
	public Command parse(String[] args) throws CommandParseException {
		if (args.length > 0 && (args[0].equalsIgnoreCase(commandName) || args[0].equalsIgnoreCase("lo"))) {
			if (args.length == 2) {
				if (!args[1].endsWith(".dat")) {
					fileName = args[1] + ".dat";	
				} else {
					fileName = args[1];
				}
			} else if (args.length < 2) {
				throw new CommandParseException("ERROR: File name not provided. Please use the following structure: LOAD <file name>");
			} else if (args.length > 2) {
				throw new CommandParseException("ERROR: Arguments limit exceeded. Please use the following structure: LOAD <file name>");
			}
			return this;
		}
		return null;
	}
}