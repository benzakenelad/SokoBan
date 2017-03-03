package EampleExamEladSolution;

public abstract class Command implements Comparable<Command>{
	
	public final int priority;
	public final long timeToExecute;
	
	public Command(int priority,long timeToExecute) {
		this.priority=priority;
		this.timeToExecute=timeToExecute;
	}
	
	
	public abstract void execute();
	
	@Override
	public int compareTo(Command o) {
		return this.priority - o.priority;
	}
	
}