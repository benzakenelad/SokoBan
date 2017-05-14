package model.data;

import java.io.Serializable;

public class Target extends GameObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// data members
	private GameObject onMe = null;
	private boolean onMeflag = false;
	private boolean boxOnMeFlag = false;

	
	// getters and setters
	public GameObject getOnMe() {
		return onMe;
	}
	
	public void setOnMe(GameObject onMe) throws Exception // set a game object on the target
	{ 
		this.onMe = onMe;
		
		if(onMe != null)
		{
			setOnMeFlag(true);
			if(onMe.toString() == "@")
				setBoxOnMeFlag(true);
			else
				setBoxOnMeFlag(false);
		}else
		{
			setBoxOnMeFlag(false);
			setOnMeFlag(false);
		}
	}
	
	public boolean gotGameObjectOnMe() {
		return onMeflag;
	}
	
	private void setOnMeFlag(boolean onMeflag) {
		this.onMeflag = onMeflag;
	}
	
	public boolean gotBoxOnMe() {
		return boxOnMeFlag;
	}

	private void setBoxOnMeFlag(boolean boxOnMeFlag) {
		this.boxOnMeFlag = boxOnMeFlag;
	}
	
	// methods
	@Override
	public String toString() 
	{
		if(this.onMeflag == true)
		{
			if(this.getOnMe().toString().compareTo("A") == 0)
				return "B";
			else
				return "$";
		}
		else
			return "o";
	}
	@Override
	public String toStringXRay() // see the actual object (if there is box on target toStringXRay will return "o")
	{
		return "o";
	}
}
