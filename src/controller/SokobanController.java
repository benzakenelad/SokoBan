package controller;

import java.util.Observable;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

import controller.server.Server;
import model.Model;
import view.View;


public class SokobanController implements Controller {
	
	// MyController Data Members
	private Model model = null;
	private View view = null;
	private Server server = null;
	private ArrayBlockingQueue<Command> bqueue = new ArrayBlockingQueue<Command>(2048);
	private volatile boolean stopBQueueThread = false;
	private boolean stopCLI = false;
	
	// MyController C'TOR
	public SokobanController(Model model, View view) {
		this.model = model;
		this.view = view;
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
			
			Command command = makeACommand(note);
	
			this.insertCommand(command);
			
			try // DELETE AFTER ALL TESTING
			{
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
		
		Command command = this.makeACommand(note);
		
		this.insertCommand(command);	
	}
	
	private Command makeACommand(String[] note)
	{
		return new CommandGenerator().Action(note, this);
	}

	@Override
	public void start()  // create new threads that runs the blockingqueue
	{
		new Thread(new Runnable()
		{	Command command;	
			@Override
			public void run() {
				while(stopBQueueThread != true)
				{
					try {
						command = bqueue.take();
						if(command != null)
							command.execute();
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void stop() // Stop the thread that runs the blockingqueue
	{
		stopBQueueThread = true;
	}

	@Override
	public void insertCommand(Command command) // insert new command to the blockingqueue
	{
		if(command != null)
			bqueue.add(command);
	}

	@Override
	public Model getModel() {
		return this.model;
	}

	@Override
	public View getView() {
		return this.view;
	}

	@Override
	public void exit() // stop the blocking queue thread & stop the running server thread
	{
		this.stop();
		this.stopTheCLI();
		if(server != null)
			this.server.stopServer();
	}

	public void setServer(Server server) 
	{
		this.server = server;
	}
	
	void stopTheCLI() // stop the CLI
	{
		stopCLI = true;
	}
		
}
