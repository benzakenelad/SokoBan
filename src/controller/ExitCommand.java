	package controller;

public class ExitCommand extends Command {
	
	
	
	@Override
	public void execute() {
		System.out.println("Good Bye");
		System.exit(0);
	}
}
