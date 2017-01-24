package controller.sokobancommands;

public class DisplayCommand extends SokobanCommand {

    
	public void execute() {
		view.Display(model.getLvl());
	}

}
