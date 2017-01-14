package controller;

import java.io.IOException;

import model.data.SaveLevel;

public class SaveLevelCommand extends Command{
	
	
	
	@Override
	public void execute() {
		if(lvl == null)
			return;
		
		try {
			new SaveLevel().Action(lvl, order);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
