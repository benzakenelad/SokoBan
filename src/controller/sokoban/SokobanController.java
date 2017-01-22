package controller.sokoban;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.general.Command;
import controller.general.Controller;
import controller.server.Server;
import model.Model;
import view.View;


public class SokobanController implements Observer {
	
	// MyController Data Members
	private Model model = null;
	private View view = null;
	private Server server = null;
	private Controller controller = null;
	
	private boolean stopCLI = false;
	
	HashMap<String, SokobanCommand> sokoCommandHM = null;
	
	
	// MyController C'TOR
	public SokobanController(Model model, View view, Controller controller) {
		this.model = model;
		this.view = view;
		this.controller = controller;
		this.initializeSokoCommandHM();
	}
	
	// CLI
	public void CLI()
	{
		String input = new String();
		String note[];
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter 'menu' for menu display.");
		
		while(stopCLI != true)
		{
			input = scan.nextLine().toLowerCase();
			note = input.split(" ");
			
			Command command = generateACommand(note);
			controller.insertCommand(command);
			
			try { Thread.sleep(10);} catch (InterruptedException e1) {} // DELETE AFTER ALL TESTING
		}	
	}

	@Override 
	public void update(Observable arg0, Object arg1) // update when ever the Model / View are changed
	{
		if(arg1 == null)
			return;
		
		String s = (String)arg1;
		s = s.toLowerCase();
		String note[] = s.split(" ");
		
		Command command = this.generateACommand(note);
		
		controller.insertCommand(command);	
	}

	public void exit() // stop the blocking queue thread & stop the running server thread
	{
		controller.stop();
		this.stopTheCLI();
		if(server != null)
			this.server.stopServer();
	}
	
	private Command generateACommand(String[] note){
		SokobanCommand command = sokoCommandHM.get(note[0]);
		if(command != null)
		{
			if(note.length >= 2)
				
				command.setOrder(note[1]);
			command.setModel(model);
			command.setView(view);
			return command;
		}
		else
		    return null;
	}

	public Model getModel() {
		return this.model;
	}

	public View getView() {
		return this.view;
	}

	public void setServer(Server server) {
		this.server = server;
	}
	
	private void stopTheCLI() {
		stopCLI = true;
	}
	
	private void initializeSokoCommandHM()
	{
		sokoCommandHM = new HashMap<String, SokobanCommand>();
		sokoCommandHM.put("move", new MoveCommand());
		sokoCommandHM.put("load", new LoadLevelCommand());
		sokoCommandHM.put("save", new SaveLevelCommand());
		sokoCommandHM.put("exit", new SafeExitCommand(this));
		sokoCommandHM.put("display", new DisplayCommand());
		sokoCommandHM.put("menu", new ShowMenuCommand());
	}
		
}
