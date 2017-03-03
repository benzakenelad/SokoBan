package EampleExamEladSolution;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.Timer;

public class CommandScheduler{
	
	private PriorityBlockingQueue<Command> bqueue = new PriorityBlockingQueue<Command>(1024); // blocking queue
	private volatile boolean stopBQueueThread = false; 
	private Thread startThread;
	Timer timer = new Timer();
	
	public void insertCommand(Command c){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {Thread.sleep(c.timeToExecute);} catch (InterruptedException e) {}
				bqueue.add(c);
			}
		}).start();
	}
	public void start(){
		
		startThread = new Thread(new Runnable()
		{	
			Command command;	
			@Override
			public void run() {
				while(stopBQueueThread != true)
				{
					try 
					{
						command = bqueue.take();
						
						if(command != null)
						{	
							command.execute();
						}
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		});
		startThread.start();
	
	}
	
	
	public void stop(){
		stopBQueueThread = true;
	}
}
