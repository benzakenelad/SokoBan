	package controller;

public class ExitCommand extends SokoCommand {
	
	
	
	@Override
	public void execute() {
		System.out.println("Good Bye");
		System.exit(0);
	}
}
