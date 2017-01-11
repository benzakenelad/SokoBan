package commands;

import model.data.Level;

public interface Command {

	public void execute(Level lvl, String note);
}
