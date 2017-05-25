package model.data.gameObjects;

import java.io.Serializable;

public abstract class GameObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// data members
	private Position pos = null;

	// c'tors
	public GameObject(Position pos) {
		this.pos = new Position(pos);
	}
	public GameObject() {
	}
	
	// getters and setters
	public Position getPos() {
		return pos;
	}
	public void setPos(Position pos) {
		this.pos = pos;
	}

	// methods
	@Override
	public abstract String toString();

}
