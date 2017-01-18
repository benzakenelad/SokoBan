	package controller;

public class MoveCommand extends SokobanCommand {
	
	
	public void execute() {
		controller.getModel().Move(order);
	}
}
