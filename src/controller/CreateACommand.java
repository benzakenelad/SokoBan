package controller;

import java.util.HashMap;

public class CreateACommand {
	HashMap<String, Command> hm;
	
	public CreateACommand() 
	{
		hm = new HashMap<String, Command>();
		hm.put("move", new MoveCommand());
		hm.put("load", new LoadLevelCommand());
		hm.put("save", new SaveLevelCommand());
		hm.put("exit", new ExitCommand());
		hm.put("display", new DisplayCommand());
		hm.put("menu", new ShowMenuCommand());
	}

	public Command Action(String note)
	{
		Command c = hm.get(note);
		return c;
	}
}
