package controller;

public class LoadLevelCommand extends SokoCommand {

	
	
	@Override
	public void execute() {
		model.LoadLevel(order);
	}
}
