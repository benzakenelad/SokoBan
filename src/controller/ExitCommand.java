	package controller;

public class ExitCommand extends SokobanCommand {
	
	
	
	@Override
	public void execute() {
		controller.exit();
		System.out.println("Good Bye");
	}
}
