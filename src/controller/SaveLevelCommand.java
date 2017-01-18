package controller;

public class SaveLevelCommand extends SokobanCommand{
	
	
	
	@Override
	public void execute() {
		controller.getModel().SaveLevel(order);
	}

}
