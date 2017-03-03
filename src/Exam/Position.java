package Exam;

public class Position {
	

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

	public Position()  // default c'tor
	{
		this.x = 0;
		this.y = 0;
	}

	// getters and setters
	public int getX() {
		return x;
	}
	
	public void setX(int x){
		if(x >= 0)
			this.x = x;
		else
			this.x = 0;
	}

	public int getY() {
		return y;
	}

	public void setY(int y){
		if (y >= 0)
			this.y = y;
		else
			this.y = 0;
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
