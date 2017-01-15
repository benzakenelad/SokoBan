	package controller;

public class MoveCommand extends SokoCommand {
	
	
	public void execute() {
		model.Move(order);
	}
}
