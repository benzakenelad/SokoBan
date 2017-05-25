package model.data.gameObjects;

import java.io.Serializable;

public class Character extends GameObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean onTarget = false;

	public boolean isOnTarget() {
		return onTarget;
	}

	public void setOnTarget(boolean onTarget) {
		this.onTarget = onTarget;
	}

	@Override
	public String toString() {
		return "A";
	}
}
