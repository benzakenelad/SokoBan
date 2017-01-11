package commands;

import levels.Level;

public interface Command {

	public void execute(Level lvl, String note);
}
