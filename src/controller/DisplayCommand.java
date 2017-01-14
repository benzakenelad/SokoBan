package controller;

import levels.TextLevelDisplay;

public class DisplayCommand extends Command {

    
	public void execute() {
		if(lvl == null)
		{
			System.out.println("There is not a level loaded.");
			return;
		}
		new TextLevelDisplay().Display(lvl); // in this case we chose test level display
	}

}
