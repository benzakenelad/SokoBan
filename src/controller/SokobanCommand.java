package controller;

public abstract class SokobanCommand implements Command{
	protected String order;
	protected Controller controller = null;
	
	public void setOrder(String order) {
		this.order = order;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}	
	
}
