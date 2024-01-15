package tp.p1.logic.commands;

import tp.p1.exceptions.CommandExecuteException;
import tp.p1.exceptions.CommandParseException;
import tp.p1.logic.Game;

public abstract class Command {
	private String helpText;
	private String commandText;
	protected final String commandName;
	
	protected Command(String commandText, String commandInfo, String helpInfo){
		this.commandText = commandInfo;
		helpText = helpInfo;
		String[] commandArgs = commandText.split("\\s+");
		commandName = commandArgs[0];
	}
	/*
	Some commands may generate an error in the execute or parse methods.
	In the absence of exceptions , they must the tell the controller not to print the board
	*/
	public abstract boolean execute(Game game) throws CommandExecuteException;
	public abstract Command parse(String[] args) throws CommandParseException;
	public String helpText(){return "   " + commandText + ": " + helpText;}
}