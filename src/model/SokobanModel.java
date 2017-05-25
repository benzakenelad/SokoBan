package model;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Observable;

import model.data.Level;
import model.data.Move;
import model.levelLoaders.LoadLevel;
import model.levelSavers.SaveLevel;
import model.policy.Policy;
import searchLib.Action;
import solver.solveSokobanLevel;

public class SokobanModel extends Observable implements Model {
	
	// Data members
	private Level lvl = null;


	// Methods implementation
	@Override
	public void move(Move move, Policy policy, String note) {	
		try 
		{
			move.Action(lvl, policy, note);
			this.setChanged();
			this.notifyObservers("display");
	
		} catch (Exception e) {/*e.printStackTrace();*/}	
	}

	@Override
	public void loadLevel(String note) {
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
	public void saveLevel(String note) {
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

	@Override
	public void solve() {
		solveSokobanLevel solver = new solveSokobanLevel();
		List<Action> actions;
		try {
			actions = solver.optimalSolve(this.lvl, 1000);
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					for(Action action: actions){
						setChanged();
						notifyObservers(action.getName());
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			}).start();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
		
	}
	
	

}
