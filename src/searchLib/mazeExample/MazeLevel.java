package searchLib.mazeExample;

import searchLib.Position;

public class MazeLevel {

	private char[][] levelData;
	private Position player;
	private Position winPosition;
	private int highet;
	private int width;
	
	public MazeLevel() {		
	}

	public char[][] getLevelData() {
		return levelData;
	}

	public void setLevelData(char[][] levelData) {
		this.levelData = levelData;
	}

	public Position getPlayer() {
		return player;
	}

	public void setPlayer(Position player) {
		this.player = player;
	}

	public Position getWinPosition() {
		return winPosition;
	}

	public void setWinPosition(Position winPosition) {
		this.winPosition = winPosition;
	}

	public int getHighet() {
		return highet;
	}

	public void setHighet(int highet) {
		this.highet = highet;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public void print(){
		for(int i = 0; i < this.highet;i++){
			for(int j = 0; j < this.width; j++)
				System.out.print(levelData[i][j]);
			System.out.println();
		}
	}
	
	
	
}
