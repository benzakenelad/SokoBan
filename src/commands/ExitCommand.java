	package commands;

import levels.Level;

public class ExitCommand implements Command {

	@Override
	public void execute(Level lvl, String note) {
		System.out.println("Good Bye");
		System.exit(0);
	}
}
