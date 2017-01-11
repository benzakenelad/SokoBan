package levels;

import java.io.Serializable;



public class Position implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;

	
	public Position(int x, int y) throws Exception{
		if(x >= 0 && y >= 0)
		{
		   this.x = x;
		   this.y = y;
		}
		else
			throw new Exception("Invalid Poistion Parameters");
	}
	
	public Position(Position p)
	{
		this.x = p.getX();
		this.y = p.getY();
		
	}
	
	public Position()
	{
		this.x = -1;
		this.y = -1;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
