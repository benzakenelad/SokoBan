package model.policy;

import model.data.Character;
import model.data.Direction;
import model.data.GameObject;
import model.data.Level;
import model.data.Position;
import model.data.Target;

public class MySokobanPolicy extends Policy {

	public void check(Level lvl, /*Position source, Position dest, */ Direction dir) throws Exception { 
		Position source = null, dest = null;
		source = new Position(lvl.getCC().getPos());
		dest = PositionCalculator(source, dir);
		
		if(lvl.getGameObjectByPosition(dest) != null)
			if(lvl.getGameObjectByPosition(dest).toString2() == "#") // if there is a wall ahead
				return;
		
		Character player = lvl.getCC();
		GameObject nearObject = lvl.getGameObjectByPosition(dest);
		Position nextStepPos = null;
		nextStepPos = PositionCalculator(dest, dir);
		GameObject nextStepObj = lvl.getGameObjectByPosition(nextStepPos);
		
		if (player.isOnTarget() == false) // the player not on target
		{
			if(nearObject == null) // if there is nothing ahead the player move
			{
				lvl.moveObjectToPosition(player, dest);
				lvl.makeSlotNullByPosition(source);
				return;
				
			} else if(nearObject.toString2() == "#") // case there is a wall ahead, the player can't move
			{
				return;
				
			}else if(nearObject.toString2() == "o") // case there is a target ahead : 
			{
				Target t = (Target)nearObject;
				if(t.isFlag() == false) // the target is empty
				{
					makePlayerOnTarget(player, t);
					lvl.makeSlotNullByPosition(source);
					return;
				}
				else // there is a box on the target
				{
					if(nextStepObj == null) // the box can move
					{
						GameObject go = t.getOnMe();
						lvl.moveObjectToPosition(go, nextStepPos);
						t.setFlag(false);
						t.setOnMe(null);
						makePlayerOnTarget(player, t);
						lvl.makeSlotNullByPosition(source);
						return;
						
					}else if(nextStepObj.toString() == "#" || nextStepObj.toString() == "@")// the box can't move
					{
						return;
					}
					else if(nextStepObj.toString2() == "o") // if we are here the nextStepObj is an empty target
					{
						Target t2 = (Target) nextStepObj;
						GameObject movingObj = t.getOnMe();
						movingObj.setPos(new Position(t2.getPos()));
						t2.setOnMe(movingObj);
						t2.setFlag(true);
						t.setFlag(false);
						t.setOnMe(null);
						makePlayerOnTarget(player, t);
						lvl.makeSlotNullByPosition(source);
						return;
					}
				}
			}else if(nearObject.toString2() == "@") // case there is a box ahead
			{
				if (nextStepObj == null) // after a box there is an empty slot
				{
					lvl.moveObjectToPosition(nearObject, nextStepPos);
					lvl.makeSlotNullByPosition(dest);
					lvl.moveObjectToPosition(player, dest);
					lvl.makeSlotNullByPosition(source);
					return;
					
				}else if(nextStepObj.toString() == "#" || nextStepObj.toString() == "@")// the box can't move
				{
					return;
					
				}else if(nextStepObj.toString2() == "o") // if we are here the nextStepObj is an empty target
				{
					Target t = (Target) nextStepObj;
					t.setFlag(true);
					t.setOnMe(nearObject);
					nearObject.setPos(new Position(t.getPos()));
					lvl.makeSlotNullByPosition(dest);
					lvl.moveObjectToPosition(player, dest);
					lvl.makeSlotNullByPosition(source);
					return;
				}
			}
		}else // player is on target
		{
			Target t = (Target) lvl.getGameObjectByPosition(source);
			if(nearObject == null) // there is nothing ahead
			{
				lvl.moveObjectToPosition(player, dest);
				player.setOnTarget(false);
				t.setFlag(false);
				t.setOnMe(null);
				return;
				
			}else if(nearObject.toString() == "#") // case there is a wall ahead, the player can't move
			{
				return;
				
			}else if(nearObject.toString2() == "o") // in case there is a target ahead
			{
				Target t2 = (Target) nearObject;
				if(t2.isFlag() == false) // in case the target is empty
				{
					makePlayerOnTarget(player, t2);
					t.setFlag(false);
					t.setOnMe(null);
					return;
					
				}else // in case there is a box on the target
				{
					GameObject movingObj = t2.getOnMe();
					if(nextStepObj == null) // case the nextStepObj is an empty slot
					{
						lvl.moveObjectToPosition(movingObj, nextStepPos);
						t2.setOnMe(null);
						t2.setFlag(false);
						makePlayerOnTarget(player, t2);
						t.setFlag(false);
						t.setOnMe(null);
						return; 
						
					}else if(nextStepObj.toString() == "@" || nextStepObj.toString() == "#") // the box can't move
					{
						return;
						
					}else if(nextStepObj.toString2() == "o") // we know that the next step object is an empty target
					{
						Target t3 = (Target) nextStepObj;
						t3.setOnMe(movingObj);
						movingObj.setPos(new Position(t3.getPos()));
						t3.setFlag(true);
						t2.setOnMe(null);
						t2.setFlag(false);
						makePlayerOnTarget(player, t2);
						t.setOnMe(null);
						t.setFlag(false);
						return;
						
					}
				}
			}else if(nearObject.toString2() == "@") // in case there is a box ahead
			{
				if(nextStepObj == null)
				{
					lvl.moveObjectToPosition(nearObject, nextStepPos);
					lvl.makeSlotNullByPosition(dest);
					lvl.moveObjectToPosition(player, dest);
					player.setOnTarget(false);
					t.setOnMe(null);
					t.setFlag(false);
					return;
					
				}
				if(nextStepObj.toString() == "@" || nextStepObj.toString() == "#") // the box can't move
				{
					return;
					
				}else if(nextStepObj.toString2() == "o") // in case there is an empty target after the box
				{
					Target t3 = (Target) nextStepObj;
					nearObject.setPos(new Position(t3.getPos()));
					t3.setOnMe(nearObject);
					t3.setFlag(true);
					lvl.makeSlotNullByPosition(dest);
					lvl.moveObjectToPosition(player, dest);
					player.setOnTarget(false);
					t.setFlag(false);
					t.setOnMe(null);
					return;
					
				}
			}
		}
	}
	
	private void makePlayerOnTarget(Character player, Target t) throws Exception // move the player on the target
	{
		if(t.isFlag() == false)
		{  
			t.setFlag(true);
			t.setOnMe(player);
			player.setOnTarget(true);
		    player.setPos(new Position(t.getPos()));
		}
	}
}
