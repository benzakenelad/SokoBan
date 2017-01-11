package commands;

import levels.TextLevelDisplay;
import model.data.Level;

public class DisplayCommand implements Command {

	public void execute(Level lvl, String note) {
		if(lvl == null)
			return;
		new TextLevelDisplay().Display(lvl); // in this case we chose test level display
	}

}
