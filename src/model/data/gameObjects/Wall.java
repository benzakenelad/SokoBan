package model.data.gameObjects;

import java.io.Serializable;

public class Wall extends GameObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "#";
	}
}
