package controller;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import controller.general.Controller;
import controller.server.Server;
import controller.server.SokobanClientHandler;
import controller.server.SokobanServer;
import controller.sokobancommands.Command;
import controller.sokobancommands.DisplayCommand;
import controller.sokobancommands.LoadLevelCommand;
import controller.sokobancommands.MoveCommand;
import controller.sokobancommands.SafeExitCommand;
import controller.sokobancommands.SaveLevelCommand;
import controller.sokobancommands.ShowMenuCommand;
import controller.sokobancommands.SokobanCommand;
import model.Model;
import view.GUI.View;


public class SokobanController implements Observer {
	
	// MyController Data Members
	private Model model = null;
	private View view = null;
	private Server server = null;
	private Controller controller = null;
	
//	private boolean stopCLI = false;
	
	HashMap<String, SokobanCommand> sokoCommandHashMap = null;
	
	
	// MyController C'TOR
	public SokobanController(Model model, View view, String note) {
		this.model = model;
		this.view = view;
		this.controller = new Controller();
		controller.start();
		this.initializeSokoCommandHM();
		
		String[] notes = note.split(" ");
		if(note.length() >= 2 && notes[0].compareTo("-server") == 0)
			try { StartPlayWithServer(Integer.parseInt(notes[1])); } catch (NumberFormatException e) { e.printStackTrace(); } catch (Exception e) { e.printStackTrace(); }
		
	}
	
	public void StartPlayWithServer(int port) throws Exception // start the Sokoban with the server
	{
		SokobanClientHandler sch = new SokobanClientHandler();
		sch.addObserver(this);
		server = new SokobanServer(port, sch);
		server.startServer();
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
		view.safeExit(0);
		controller.stop();
//		this.stopTheCLI();
		if(server != null)
			this.server.stopServer();
	}
	
	private Command generateACommand(String[] note){
		SokobanCommand command = sokoCommandHashMap.get(note[0]);
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

	private void initializeSokoCommandHM()
	{
		sokoCommandHashMap = new HashMap<String, SokobanCommand>();
		sokoCommandHashMap.put("move", new MoveCommand());
		sokoCommandHashMap.put("load", new LoadLevelCommand());
		sokoCommandHashMap.put("save", new SaveLevelCommand());
		sokoCommandHashMap.put("exit", new SafeExitCommand(this));
		sokoCommandHashMap.put("display", new DisplayCommand());
		sokoCommandHashMap.put("menu", new ShowMenuCommand());
	}
	
	/*	
	public void StartPlayWithCLI()
	{
		CLI();
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
		}	
	}

		
	private void stopTheCLI() {
		stopCLI = true;
	}
*/	
		
}
