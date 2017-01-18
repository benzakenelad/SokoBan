package controller;

public class DisplayCommand extends SokobanCommand {

    
	public void execute() {
		controller.getView().Display(controller.getModel().getLvl());
	}

}
