package boot;

import controller.SokobanController;
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
			SokobanServer server = new SokobanServer();
			controller.setServer(server);
			server.addObserver(controller);
			int port = Integer.parseInt(args[1]);
			
			try 
			{
				server.startServer(port);
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
