package model;

import java.util.Observable;

import model.data.Level;


public class MyModel extends Observable implements Model  {
	
	private Level lvl = null;

	
	
	
	public Level getLvl() {
		return lvl;
	}

	public void setLvl(Level lvl) {
		this.lvl = lvl;
	}
	

}
