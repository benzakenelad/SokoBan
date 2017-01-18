package model;

import java.util.Observable;

import model.data.Level;
import model.data.LoadLevel;
import model.data.Move1Step;
import model.data.SaveLevel;
import model.policy.MySokobanPolicy;


public class SokobanModel extends Observable implements Model  {
	
	private Level lvl = null;

	
	
	
	public Level getLvl() {
		return lvl;
	}

	public void setLvl(Level lvl) {
		this.lvl = lvl;
	}

	@Override
	public void Move(String note) {
		
		
		try 
		{
			new Move1Step().Action(lvl, new MySokobanPolicy(), note); // depend what policy we want
			if(lvl.levelCompletionCheck() == true)
			{
				this.setChanged();
				this.notifyObservers("display");
				System.out.println("Congratulations, Level Completed");
			}
			else
			{
				this.setChanged();
				this.notifyObservers("display");
			}		
			
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void LoadLevel(String note) {
		Level lvl = null;
		try 
		{
			lvl = new LoadLevel().Action(note);
			this.lvl = lvl;
			System.out.println(note + " Loaded Successfully");
			this.setChanged();
			this.notifyObservers("display");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void SaveLevel(String note) {
		try 
		{
			new SaveLevel().Action(lvl, note);
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}		
	}
	

}
