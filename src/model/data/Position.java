package model.data;

import java.io.Serializable;



public class Position implements Serializable{
	
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
		this.setX(x);
		this.setY(y);
	}
	
	public Position(Position p) // copy c'tor
	{
		this.setX(p.getX());
		this.setY(p.getY());		
	}

	public Position()  // default c'tor
	{
	}

	// getters and setters
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		if(x < 0)
			try {
				throw new Exception("Invalid Position Parameters");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if (y < 0)
			try {
				throw new Exception("Invalid Position Parameters");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		this.y = y;
	}
	
	// methods
	@Override
	public String toString() 
	{
		String s = new String();
		s = "(" + this.x +"," + this.y + ")";
		return s;
	}
}
