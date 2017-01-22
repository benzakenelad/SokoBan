package controller.general;

import java.util.concurrent.ArrayBlockingQueue;

public class Controller{
	private ArrayBlockingQueue<Command> bqueue = new ArrayBlockingQueue<Command>(2048);
	private volatile boolean stopBQueueThread = false;
	
	
	
	public void insertCommand(Command command)  // insert new command to the blocking queue
	{
		
		if(command != null)
		{
			try { Thread.sleep(10);} catch (InterruptedException e1) {} // DELETE AFTER ALL TESTING
			bqueue.add(command);
		}
	}
	
	public void start() // create new threads that runs the blocking queue
	{
		new Thread(new Runnable()
		{	
			Command command;	
			@Override
			public void run() {
				while(stopBQueueThread != true)
				{
					try {
						command = bqueue.take();
						if(command != null)
							command.execute();
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public void stop() // Stop the thread that runs the blocking queue
	{
		stopBQueueThread = true;
	}
	
	
}
