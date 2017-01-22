	package controller.sokoban;

public class MoveCommand extends SokobanCommand {
	
	
	public void execute() {
		model.Move(order);
	}
}
