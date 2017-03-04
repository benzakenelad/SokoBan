package Exam;

public class Position {
	

	// Position data members
	
	public int x = 0;
	public int y = 0;

	
	// methods
	@Override
	public String toString() 
	{
		String s = new String();
		s = "(" + this.x +"," + this.y + ")";
		return s;
	}
}
