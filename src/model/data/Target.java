package model.data;

import java.io.Serializable;

public class Target extends GameObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// data members
	private GameObject onMe = null;
	private boolean flag = false;
	private boolean finishMoveFlag = false;

	
	// getters and setters
	public GameObject getOnMe() {
		return onMe;
	}
	
	public void setOnMe(GameObject onMe) throws Exception { // set a game object on the target
		this.onMe = onMe;
		
		if(onMe != null)
		{
			setFlag(true);
			if(onMe.toStringXRay() == "@")
				setFinishMoveFlag(true);
			else
				setFinishMoveFlag(false);
		}else
		{
			setFinishMoveFlag(false);
			setFlag(false);
		}
	}
	
	public boolean isFlag() {
		return flag;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public boolean isFinishMoveFlag() {
		return finishMoveFlag;
	}

	public void setFinishMoveFlag(boolean finishMoveFlag) {
		this.finishMoveFlag = finishMoveFlag;
	}
	
	// methods
	@Override
	public String toString() {
		if(this.flag == true)
			return onMe.toString();
		else
			return "o";
	}
	@Override
	public String toStringXRay() // see the actual object (if there is box on target toStringXRay will return "o")
	{
		return "o";
	}



}
