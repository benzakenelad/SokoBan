package controller.sokoban;

public class DisplayCommand extends SokobanCommand {

    
	public void execute() {
		view.Display(model.getLvl());
	}

}
