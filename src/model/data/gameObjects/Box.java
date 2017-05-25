package model.data.gameObjects;

import java.io.Serializable;

public class Box extends GameObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean onTarget = false;

	@Override
	public String toString() {
		return "@";
	}

	public boolean isOnTarget() {
		return onTarget;
	}

	public void setOnTarget(boolean onTarget) {
		this.onTarget = onTarget;
	}

}
