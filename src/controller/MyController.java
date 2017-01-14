package controller;

import java.util.Observable;
import java.util.concurrent.PriorityBlockingQueue;

import model.Model;
import view.View;


public class MyController implements Controller {
	Model model = null;
	View view = null;
	PriorityBlockingQueue<Command> queue = new PriorityBlockingQueue<Command>();
	
	public MyController(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		String s = (String)arg1;
		String noteArr[] = s.split(" ");
		Command command = new CreateACommand().Action(noteArr[0]);
		if(command == null)
			return; // not suppose to be
		else
		{
			command.setLevel(model.getLvl());
			if(noteArr.length >= 2)
				command.setOrder(noteArr[1]);
		}
		
		
		
		if(model == arg0)
		{
			
		}
		if(view == arg0)
		{
			
		}
		
	}

	@Override
	public void start() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					queue.take().execute();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();
		
	}
	
	
	
}
