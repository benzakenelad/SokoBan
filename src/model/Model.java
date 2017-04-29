package model;

import model.data.Level;
import model.data.Move;
import model.policy.Policy;

public interface Model {
	public Level getLvl();
	public void setLvl(Level lvl);
	public void Move(Move move, Policy policy, String note);
	public void LoadLevel(String note);
	public void SaveLevel(String note);
}
