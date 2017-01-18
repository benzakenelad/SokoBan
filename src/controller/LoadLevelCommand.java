package controller;

public class LoadLevelCommand extends SokobanCommand {

	
	
	@Override
	public void execute() {
		controller.getModel().LoadLevel(order);
	}
}
