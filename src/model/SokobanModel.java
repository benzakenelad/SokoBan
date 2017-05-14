package model;

import java.util.Observable;

import model.data.Level;
import model.data.LoadLevel;
import model.data.Move;
import model.data.SaveLevel;
import model.policy.Policy;

public class SokobanModel extends Observable implements Model {
	
	// Data members
	private Level lvl = null;


	// Methods implementation
	@Override
	public void Move(Move move, Policy policy, String note) {	
		try 
		{
			move.Action(lvl, policy, note);
			this.setChanged();
			this.notifyObservers("display");
	
		} catch (Exception e) {/*e.printStackTrace();*/}	
	}

	@Override
	public void LoadLevel(String note) {
		Level lvl = null;
		try {
			lvl = new LoadLevel().Action(note);
			this.lvl = lvl;
			this.setChanged();
			this.notifyObservers("display");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void SaveLevel(String note) {
		try {
			new SaveLevel().Action(lvl, note);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// getters and setters
	public Level getLvl() {
		return lvl;
	}
	public void setLvl(Level lvl) {
		this.lvl = lvl;
	}

}
