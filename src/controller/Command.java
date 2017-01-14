package controller;

import model.data.Level;
	
public abstract class Command {
	protected String order;
	protected Level lvl;
	
	public abstract void execute();
    public void setLevel(Level lvl)
    {
    	this.lvl = lvl;
    }
    public void setOrder(String order)
    {
    	this.order = new String(order);
    }
}
