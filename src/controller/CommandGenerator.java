package controller;

import java.util.HashMap;

public class CommandGenerator {
	HashMap<String, SokobanCommand> hm;
	
	public CommandGenerator() 
	{
		hm = new HashMap<String, SokobanCommand>();
		hm.put("move", new MoveCommand());
		hm.put("load", new LoadLevelCommand());
		hm.put("save", new SaveLevelCommand());
		hm.put("exit", new ExitCommand());
		hm.put("display", new DisplayCommand());
		hm.put("menu", new ShowMenuCommand());
	}

	public SokobanCommand Action(String[] note, Controller controller)
	{
		SokobanCommand c = hm.get(note[0]);
		if(c != null)
		{
			if(note.length >= 2)
				c.setOrder(note[1]);
			c.setController(controller);
			return c;
		}
		else
		    return null;
	}
}
