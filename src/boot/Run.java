package boot;

import controller.general.Controller;
import controller.server.SokobanClientHandler;
import controller.server.SokobanServer;
import controller.sokoban.SokobanController;
import model.SokobanModel;
import view.SokobanView;

public class Run {
	
	public static void main(String[] args) {
		
		SokobanView view = new SokobanView();
		SokobanModel model = new SokobanModel();
		Controller controller = new Controller();
		controller.start();
		SokobanController Sokocontroller = new SokobanController(model, view, controller);	
		view.addObserver(Sokocontroller);
		model.addObserver(Sokocontroller);

		if(args.length >= 2 && args[0].compareTo("-server") == 0)
		{
			int port = Integer.parseInt(args[1]);
			SokobanServer server = new SokobanServer(port, new SokobanClientHandler());
			Sokocontroller.setServer(server);
			server.addObserver(Sokocontroller);
			
			try 
			{
				server.startServer();
			} catch (Exception e) 
			{
				Sokocontroller.exit();
				e.getMessage();
				System.exit(1);
			}
		}
		else
			Sokocontroller.CLI();
			
	}

}
