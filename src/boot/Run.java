package boot;

import controller.MyController;
import model.MyModel;
import view.MyView;

public class Run {

	public static void main(String[] args) {
		
		MyView view = new MyView();
		MyModel model = new MyModel();	
		MyController controller = new MyController(model, view);
		view.addObserver(controller);
		model.addObserver(controller);
		
		
		
		
		/*
		CLI game = new CLI();
		game.StartSokoban();
		*/
	}

}
