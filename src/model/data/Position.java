package model.data;

import java.io.Serializable;

public class Position implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Position data members

	private int x = 0;
	private int y = 0;

	// c'tors
	public Position(int x, int y) // c'tor
	{
		this.x = x;
		this.y = y;
	}

	public Position(Position p) // copy c'tor
	{
		this.x = p.getX();
		this.y = p.getY();
	}

	public Position() // default c'tor
	{
		this.x = 0;
		this.y = 0;
	}

	// getters and setters
	public int getX() {
		return x;
	}

	public void setX(int x) throws Exception {
		if (x >= 0)
			this.x = x;
		else
			throw new Exception("Invalid position parameters.");
	}

	public int getY() {
		return y;
	}

	public void setY(int y) throws Exception {
		if (y >= 0)
			this.y = y;
		else
			throw new Exception("Invalid position parameters.");
	}
}
