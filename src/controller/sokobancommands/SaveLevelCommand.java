package controller.sokobancommands;

public class SaveLevelCommand extends SokobanCommand{
	
	
	
	@Override
	public void execute() {
		model.SaveLevel(order);
	}

}
