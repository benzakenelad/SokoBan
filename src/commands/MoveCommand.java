	package commands;

import model.data.Direction;
import model.data.Level;
import model.data.Move1Step;
import model.policy.MySokobanPolicy;

public class MoveCommand implements Command {

	public void execute(Level lvl, String note) {
		if(lvl == null)
			return;
		Direction dir = null;
		if(note.compareTo("left") == 0 || note.compareTo("up") == 0 || note.compareTo("right") == 0 || note.compareTo("down") == 0)
 			dir = Direction.valueOf(note);		
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
