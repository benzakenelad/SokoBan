package model.policy;

import model.data.Character;
import model.data.Direction;
import model.data.GameObject;
import model.data.Level;
import model.data.Position;
import model.data.Target;

public class MySokobanPolicy extends Policy {

	public void check(Level lvl, Direction dir) throws Exception 
	{ 
		Character player = lvl.getCC();
		Position source = new Position(player.getPos());
		boolean playerWasOnTarget = player.isOnTarget();
		
		if(moveThePlayerIfHeCan(lvl, dir) == true) // the player moved (here we clear the slot that the player was on)
			if(playerWasOnTarget == true) // in case the player was on target
			{
				Target t = (Target)lvl.getGameObjectByPosition(source);
				t.setOnMe(null);
			}
			else // in case the player was not on target
				lvl.makeSlotNullByPosition(source);
		
		if(lvl.getGameObjectByPosition(player.getPos()).toStringXRay() == "o") // if the player went on target
			player.setOnTarget(true);
		else
			player.setOnTarget(false);
		
	}
	
	private boolean moveThePlayerIfHeCan(Level lvl, Direction dir) throws Exception
	{
		Character player = lvl.getCC();
		Position sourcePos = null, destPos = null, afterDestPos = null;
		sourcePos = new Position(player.getPos());
		destPos = PositionCalculator(sourcePos, dir);
			
		GameObject destObj = lvl.getGameObjectByPosition(destPos);
		
		if(destObj != null)
			if(destObj.toString() == "#") // case we have wall in front of us
				return false;
		
		afterDestPos = PositionCalculator(destPos, dir);
		GameObject afterDestObj = lvl.getGameObjectByPosition(afterDestPos);
			
		if(destObj != null)
			if(destObj.toString() == "@") // case we have one box in front of us
				if(afterDestObj != null)
					if(afterDestObj.toString() == "@" || afterDestObj.toString() == "#") // in case we have 2 boxes in front of us, or box and wall in front of us
						return false;	
			
		
		// in case he can move //
		
		if(destObj == null) // if there is nothing ahead the player move
		{
			lvl.moveObjectToPosition(player, destPos);
			return true;
			
		}else if(destObj.toStringXRay() == "o") // case there is a target ahead 
		{
			Target t = (Target)destObj;
			if(t.isFlag() == false) // the target is empty so the player go on it
			{
				makePlayerOnTarget(player, t);
				return true;
			}
			else // there is a box on the target
			{
				if(afterDestObj == null) // the box is on target and can move 
				{
					GameObject go = t.getOnMe();
					lvl.moveObjectToPosition(go, afterDestPos);
					t.setOnMe(null);
					makePlayerOnTarget(player, t);
					return true;
					
				}else if(afterDestObj.toStringXRay() == "o") // if we are here the nextStepObj is an empty target
				{
					Target t2 = (Target)afterDestObj;
					GameObject go = t.getOnMe();
					go.setPos(new Position(t2.getPos()));
					t2.setOnMe(go);
					t.setOnMe(null);
					makePlayerOnTarget(player, t);
					return true;
				}
			}
		}else if(destObj.toStringXRay() == "@") // case there is a box ahead
		{
			if (afterDestObj == null) // after a box there is an empty slot
			{
				lvl.moveObjectToPosition(destObj, afterDestPos);
				lvl.makeSlotNullByPosition(destPos);
				lvl.moveObjectToPosition(player, destPos);
				return true;
				
			}else if(afterDestObj.toStringXRay() == "o") // if we are here the afterDestObj is an empty target
			{
				Target t2 = (Target)afterDestObj;
				t2.setOnMe(destObj);
				destObj.setPos(new Position(t2.getPos()));
				lvl.makeSlotNullByPosition(destPos);
				lvl.moveObjectToPosition(player, destPos);
				return true;
			}
		}
		
		throw new Exception("unrecognized move (in MySokobanPolicy)");
	}
	
	private void makePlayerOnTarget(Character player, Target t) throws Exception // move the player on the target
	{
		if(t.isFlag() == false)
		{  
			t.setOnMe(player);
		    player.setPos(new Position(t.getPos()));
		}
	}
}
