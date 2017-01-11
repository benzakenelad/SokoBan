package levels;

import java.io.Serializable;

public class GameObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// data members
	private Position pos = new Position();

	// c'tors
	public GameObject(Position pos) {
		this.pos = pos;
	}
	public GameObject() {
		pos.setX(0);
		pos.setY(0);
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
	public String toString2()
	{
		return "GameObject";
	}
	
		
	
}
