package controller;

import java.io.IOException;

import model.data.Level;
import model.data.LoadLevel;

public class LoadLevelCommand extends Command {

	
	
	@Override
	public void execute() {
		
		Level l = null;
		try {
			l = new LoadLevel().Action(order);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(l != null)
			System.out.println(order + " Loaded Successfully");
		else
			System.out.println("Could not find " + order + ". The required level was not loaded");
		
		this.lvl = l;
	}
	public Level getLvl() {
		return lvl;
	}
	public void setLvl(Level lvl) {
		this.lvl = lvl;
	}
}
