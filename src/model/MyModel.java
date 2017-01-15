package model;

import java.io.IOException;
import java.util.Observable;

import model.data.Direction;
import model.data.Level;
import model.data.LoadLevel;
import model.data.Move1Step;
import model.data.SaveLevel;
import model.policy.MySokobanPolicy;


public class MyModel extends Observable implements Model  {
	
	private Level lvl = null;

	
	
	
	public Level getLvl() {
		return lvl;
	}

	public void setLvl(Level lvl) {
		this.lvl = lvl;
	}

	@Override
	public void Move(String note) {
		if(lvl == null)
			return;
		Direction dir = null;
		if(note.compareTo("left") == 0 || note.compareTo("up") == 0 || note.compareTo("right") == 0 || note.compareTo("down") == 0)
 			dir = Direction.valueOf(note);		
		else
			return;
		
		try {
			new Move1Step().Action(lvl, new MySokobanPolicy(), dir); // depend what policy we want
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		if(lvl.levelCompletionCheck() == true)
		{

			this.setChanged();
			this.notifyObservers("display");
			System.out.println("Congratulations, Level Completed");
		}
		else
		{
//		this.setChanged();
//		this.notifyObservers("display");
		}
	}

	@Override
	public void LoadLevel(String note) {
		Level l = null;
		try {
			l = new LoadLevel().Action(note);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(l != null)
		{
			System.out.println(note + " Loaded Successfully");
//			this.setChanged();
//			this.notifyObservers("display");
		}
		else
			System.out.println("Could not find " + note + ". The required level was not loaded");
		
		this.lvl = l;	
	}

	@Override
	public void SaveLevel(String note) {
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
