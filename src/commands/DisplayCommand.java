package commands;

import levels.Level;
import levels.TextLevelDisplay;

public class DisplayCommand implements Command {

	public void execute(Level lvl, String note) {
		if(lvl == null)
			return;
		new TextLevelDisplay().Display(lvl); // in this case we chose test level display
	}

}
