package commands;

import java.io.IOException;

import model.data.Level;
import model.data.SaveLevel;

public class SaveLevelCommand implements Command{

	@Override
	public void execute(Level lvl, String note) {
		if(lvl == null)
			return;
		
		try {
			new SaveLevel().Action(lvl, note);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
