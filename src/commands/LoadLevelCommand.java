package commands;

import java.io.IOException;

import levels.Level;
import levels.LoadLevel;

public class LoadLevelCommand implements Command {
	private Level lvl = null;
	@Override
	public void execute(Level lvl, String note) {
		
		Level l = null;
		try {
			l = new LoadLevel().Action(note);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(l != null)
			System.out.println(note + " Loaded Successfully");
		else
			System.out.println("Could not find " + note + ". The required level was not loaded");
		
		this.lvl = l;
	}
	public Level getLvl() {
		return lvl;
	}
	public void setLvl(Level lvl) {
		this.lvl = lvl;
	}
}
