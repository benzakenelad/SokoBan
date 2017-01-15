package model;

import model.data.Level;

public interface Model {
	public Level getLvl();
	public void setLvl(Level lvl);
	public void Move(String note);
	public void LoadLevel(String note);
	public void SaveLevel(String note);
}
