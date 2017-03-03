package EampleExamEliSolution;

import java.util.Comparator;
import java.util.PriorityQueue;

import java.util.Timer;
import java.util.TimerTask;

	
	public class CommandScheduler{
		
		PriorityQueue<Command> queue;
		volatile boolean stop=false;
		Thread tq;
		Timer timer;
		
		public CommandScheduler() {
			queue=new PriorityQueue<>(10, new Comparator<Command>() {
				@Override
				public int compare(Command c1, Command c2) {
					if(c1.timeToExecute==c2.timeToExecute)
						return c1.priority-c2.priority;
					else
					return (int)(c1.timeToExecute-c2.timeToExecute);
				}
			});
			
			timer=new Timer();
		}
		
		public void insertCommand(Command c){
			queue.add(c);
			if(tq!=null)
				tq.interrupt();
		}
		
		
		public void start(){
			tq=new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(!stop){
						
						while(!queue.isEmpty()){							
							final Command c= queue.poll();
							timer.schedule(new TimerTask() {
								public void run() {
									c.execute();
								}
							}, c.timeToExecute);
						}
						try {
							Thread.sleep(1000*60*60);
						} catch (InterruptedException e) {
							// I was woken up!
						}
					}
				}
			});
			tq.start();
		}
		
		public void stop(){
			stop=true;
			tq.interrupt();
			timer.cancel();
		}
	}

