package searchLib.mazeExample;

public class Position {
	private int row;
	private int col;
	
	
	
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	
	@Override
	public int hashCode() {
		return Integer.toString(col*97 + row*31).hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		Position pos = (Position) obj;
		return this.col == pos.col && this.row == pos.row;	
	}
	
	@Override
	public String toString() {
		return "row : " + row + "  col : " + col;
	}
		
}
