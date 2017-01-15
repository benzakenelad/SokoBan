package controller;

import java.util.Observable;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

import model.Model;
import view.View;


public class MyController implements Controller {
	private Model model = null;
	private View view = null;
	private ArrayBlockingQueue<Command> bqueue = new ArrayBlockingQueue<Command>(1024);
	private Thread startThread = null;
	
	public MyController(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	public void CLI()
	{
		CommandGenerator comGen = new CommandGenerator();
		String input = new String();
		String note[];
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter 'menu' for menu display.");
		
		while(true)
		{
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			input = scan.nextLine();
			input.toLowerCase();
			note = input.split(" ");
			SokoCommand command = comGen.Action(note);
			if(command != null)
			{
				command.setView(view);
				command.setModel(model);
				bqueue.add(command);
				
			}
		}
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		String s = (String)arg1;
		String note[] = s.split(" ");
		
		SokoCommand command = new CommandGenerator().Action(note);
		
		if(command != null)
		{
			command.setModel(model);
			command.setView(view);		
		}
		else
			return;
		
		bqueue.add(command);
		
		
		
		if(model == arg0)
		{
			
		}
		if(view == arg0)
		{
			
		}
		
	}

	@Override
	public void start() {
		startThread = new Thread(new Runnable()
		{			
			@Override
			public void run() {
				while(true)
				{
					try {
						bqueue.take().execute();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		startThread.start();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void stop() {
		if(startThread != null)
			startThread.stop();
	}
	
	
	
}
