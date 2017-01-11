package levels;

import java.io.Serializable;

public class Level implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// data members
	private GameObject[][] levelData = null; // store all game objects
	private String levelTitle = ""; // the level title
	private Character CC = null; // the level's character
	private int levelNumber = 0; // level number
	private int levelWidth = 0; // the level max Width
	private int levelHeight = 0; // the level height (number of lines)
	private int boxCount = 0; // box counter
	private int targetCount = 0; // target counter
	private int wallCount = 0; // wall counter
	private int emptyCellCount = 0; // empty cells counter
	private int score = 0; // score
	
	
	// c'tors
	public Level() {
	}
	
	
	
	// getters & setters
	
	public String getLevelTitle() {
		return levelTitle;
	}
	public void setLevelTitle(String levelTitle) {
		this.levelTitle = levelTitle;
	}
	public int getLevelNumber() {
		return levelNumber;
	}
	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}
	public int getLevelWidth() {
		return levelWidth;
	}
	public void setLevelWidth(int levelWidth) {
		this.levelWidth = levelWidth;
	}
	public int getLevelHeight() {
		return levelHeight;
	}
	public void setLevelHeight(int levelHeight) {
		this.levelHeight = levelHeight;
	}
	public int getBoxCount() {
		return boxCount;
	}
	public void setBoxCount(int boxCount) {
		this.boxCount = boxCount;
	}
	public int getTargetCount() {
		return targetCount;
	}
	public void setTargetCount(int targetCount) {
		this.targetCount = targetCount;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getWallCount() {
		return wallCount;
	}
	public void setWallCount(int wallCount) {
		this.wallCount = wallCount;
	}
	public int getEmptyCellCount() {
		return emptyCellCount;
	}
	public void setEmptyCellCount(int emptyCellCount) {
		this.emptyCellCount = emptyCellCount;
	}
	public GameObject[][] getLevelData() {
		return levelData;
	}
	public void setLevelData(GameObject[][] levelData) {
		this.levelData = levelData;
	}
	public Character getCC() {
		return CC;
	}
	public void setCC(Character cC) {
		CC = cC;
	}
	
	public GameObject getGameObjectByPosition(Position p) throws Exception // return an object by his position
	{
		if(p == null)
			throw new Exception("null position");
		int x = p.getX(), y = p.getY();
		return levelData[x][y];
	}
	public void moveObjectToPosition(GameObject go, Position dest) throws Exception // move an object to a new position
	{
		if(go != null)
		{
			int x = dest.getX(), y = dest.getY();
			if(levelData[x][y] == null)
			{
				go.setPos(new Position(x,y));
				levelData[x][y] = go;	
			}
		}
	}
	public void makeSlotNullByPosition(Position p) // make the Position arrayData slot null
	{
		int x = p.getX(), y = p.getY();
		levelData[x][y] = null;
	}
	public boolean levelCompletionCheck() // check's if the level completed successfully
	{
		int height = 0, width = 0;
		height = this.getLevelHeight();
		for(int i = 0; i < height; i++)
		{
			width = levelData[i].length;
			for(int j = 0; j < width; j++)
			{
				if(levelData[i][j] != null)
				{
					if(levelData[i][j].toString2() == "o")
					{
						Target t = (Target) levelData[i][j];
						if(t.isFinishMoveFlag() == false)
							return false;
					}
				}
			}
		}
		return true;
	}
}
