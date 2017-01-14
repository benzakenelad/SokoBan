package model.data;

import java.io.Serializable;

public class Character extends GameObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean onTarget = false;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "A";
	}
	
	public boolean isOnTarget() {
		return onTarget;
	}
	
	public void setOnTarget(boolean onTarget) {
		this.onTarget = onTarget;
	}
	
}
