package EampleExamEladSolution;

public class ConcreteCommand extends Command {

	public ConcreteCommand(int priority, long timeToExecute) {
		super(priority, timeToExecute);
	}

	@Override
	public void execute() {
		System.out.println("Command ; timeToExecute : " + timeToExecute + " ; priority : " + priority + " ");

	}

}
