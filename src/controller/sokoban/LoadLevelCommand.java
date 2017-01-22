package controller.sokoban;

public class LoadLevelCommand extends SokobanCommand {

	
	
	@Override
	public void execute() {
		model.LoadLevel(order);
	}
}
