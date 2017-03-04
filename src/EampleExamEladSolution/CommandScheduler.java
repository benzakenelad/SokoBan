package EampleExamEladSolution;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;

public class CommandScheduler {
	private PriorityQueue<Command> pq = null;
	private Thread thread;
	private volatile boolean stopPQ = false;
	Timer timer = null;

	public CommandScheduler() {
		timer = new Timer();
		pq = new PriorityQueue<Command>(new Comparator<Command>() {

			@Override
			public int compare(Command o1, Command o2) {
				if (o1.timeToExecute == o2.timeToExecute)
					return o1.priority - o2.priority;
				else
					return (int) (o1.timeToExecute - o2.timeToExecute);
			}
		});
	}

	public void insertCommand(Command c) {
		pq.add(c);
		if (thread != null)
			thread.interrupt();
	}

	public void start() {
		thread = new Thread(new Runnable() {

			@Override
			public void run() {

				while (stopPQ == false) {
					if (!pq.isEmpty()) {
						Command command = pq.poll();

						timer.schedule(new TimerTask() {

							@Override
							public void run() {
								command.execute();

							}
						}, command.timeToExecute);
					} else
						try {
							Thread.sleep(60 * 100 * 100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}

			}
		});
		thread.start();

	}

	public void stop() {
		stopPQ = true;
		thread.interrupt();
		timer.cancel();
	}
}