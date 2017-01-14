	package controller;

import model.data.Direction;
import model.data.Move1Step;
import model.policy.MySokobanPolicy;

public class MoveCommand extends Command {
	
	
	public void execute() {
		if(lvl == null)
			return;
		Direction dir = null;
		if(order.compareTo("left") == 0 || order.compareTo("up") == 0 || order.compareTo("right") == 0 || order.compareTo("down") == 0)
 			dir = Direction.valueOf(order);		
		else
			return;
		
		try {
			new Move1Step().Action(lvl, new MySokobanPolicy(), dir); // depend what policy we want
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
