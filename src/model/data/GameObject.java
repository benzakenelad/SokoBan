package model.data;

import java.io.Serializable;

public class GameObject implements Serializable{

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
		pos = new Position(0,0);
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
	public String toString() {
		// TODO Auto-generated method stub
		return "GameObject";
	}
	public String toStringXRay() // see the actual object (if there is box on target toStringXRay will return "o")
	{
		return this.toString();
	}
	
		
	
}
