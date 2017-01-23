package boot;

import controller.sokoban.SokobanController;
import model.SokobanModel;
import view.TXT.SokobanTXTView;

public class Run {
	
	public static void main(String[] args) {
		
		SokobanTXTView view = new SokobanTXTView();
		SokobanModel model = new SokobanModel();
		SokobanController controller = new SokobanController(model, view);	
		view.addObserver(controller);
		model.addObserver(controller);

//		if(args.length >= 2 && args[0].compareTo("-server") == 0)
//			try { controller.StartPlayWithServer(Integer.parseInt(args[1])); } catch (Exception e) { System.out.println(e.getMessage());}
//		else
			controller.StartPlayWithCLI();
			
	}

}
