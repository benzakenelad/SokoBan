package controller;

public class SaveLevelCommand extends SokoCommand{
	
	
	
	@Override
	public void execute() {
		model.SaveLevel(order);	
	}

}
