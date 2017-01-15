package controller;

public class DisplayCommand extends SokoCommand {

    
	public void execute() {
		view.Display(model.getLvl());
	}

}
