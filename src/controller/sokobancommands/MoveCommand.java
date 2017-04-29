	package controller.sokobancommands;

import model.data.Move1Step;
import model.policy.MySokobanPolicy;

public class MoveCommand extends SokobanCommand {
	
	
	public void execute() {
		model.Move(new Move1Step(), new MySokobanPolicy(), order);
	}
}
