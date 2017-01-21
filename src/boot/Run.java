package boot;

import controller.SokobanController;
import controller.server.SokobanClientHandler;
import controller.server.SokobanServer;
import model.SokobanModel;
import view.SokobanView;

public class Run {
	
	public static void main(String[] args) {
		
		
		
		SokobanView view = new SokobanView();
		SokobanModel model = new SokobanModel();
		SokobanController controller = new SokobanController(model, view);	
		view.addObserver(controller);
		model.addObserver(controller);
		controller.start();

		if(args.length >= 2 && args[0].compareTo("-server") == 0)
		{
			int port = Integer.parseInt(args[1]);
			SokobanServer server = new SokobanServer(port, new SokobanClientHandler());
			controller.setServer(server);
			server.addObserver(controller);
			
			try 
			{
				server.startServer();
			} catch (Exception e) 
			{
				controller.exit();
				e.getMessage();
				System.exit(1);
			}
		}
		else
			controller.CLI();
		
		
	}

}
