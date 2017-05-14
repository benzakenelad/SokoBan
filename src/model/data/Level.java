package model.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// data members
	private String ID; // level's ID
	private String levelName = null; // levelName
	private GameObject[][] levelData = null; // store all game objects
	private String levelTitle = ""; // the level title
	private Character player = null; // the level's character
	private ArrayList<Target> targetsArray = new ArrayList<Target> (); // targets holder (faster level completion check)
	private int levelNumber = 0; // level number
	private int levelMaxWidth = 0; // the level max Width
	private int levelMaxHeight = 0; // the level height (number of lines)
	private int boxCount = 0; // box counter
	private int targetCount = 0; // target counter
	private int wallCount = 0; // wall counter
	private int emptyCellCount = 0; // empty cells counter
	private int score = 0; // score
	private int numOfBoxesOnTargets = 0; // number of boxes on targets
	private int steps = 0; // player steps counter
	private double finishTime = 0; // finish time
	private boolean levelFinishedFlag = false; 
	
	//c'tor
	public Level() {ID = "Level 6";}
	
	// getters & setters
	
	public String getID() {
		return ID;
	}
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
	public int getLevelMaxWidth() {
		return levelMaxWidth;
	}
	public void setLevelMaxWidth(int levelMaxWidth) {
		this.levelMaxWidth = levelMaxWidth;
	}
	public int getLevelMaxHeight() {
		return levelMaxHeight;
	}
	public void setLevelMaxHeight(int levelMaxHeight) {
		this.levelMaxHeight = levelMaxHeight;
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
	public Character getPlayer() {
		return player;
	}
	public void setPlayer(Character player) {
		this.player = player;
	}
	public int getNumOfBoxesOnTargets() {
		return numOfBoxesOnTargets;
	}
	public void setNumOfBoxesOnTargets(int numOfBoxesOnTargets) {
		this.numOfBoxesOnTargets = numOfBoxesOnTargets;
	}
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	public boolean isLevelFinishedFlag() {
		return levelFinishedFlag;
	}
	public void setLevelFinishedFlag(boolean levelFinishedFlag) {
		this.levelFinishedFlag = levelFinishedFlag;
	}
	public double getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(double finishTime) {
		this.finishTime = finishTime;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}	
	public ArrayList<Target> getTargetsArray() {
		return targetsArray;
	}
	public void sesTargetsArray(ArrayList<Target> targetsArray) {
		this.targetsArray = targetsArray;
	}
	
	// methods
	
	public GameObject getGameObjectByPosition(Position p) throws Exception // return an object by his position
	{
		if(p != null){
			int x = p.getX();
			int y = p.getY();
			return levelData[x][y];
		}	
		return null;
	}
	public void moveObjectToPosition(GameObject gameObj, Position dest) throws Exception // move an object to a new position
	{
		if(gameObj == null)
			throw new Exception("Null game object.");
		if(dest == null)
			throw new Exception("Null position.");
		
		int x = dest.getX();
		int y = dest.getY();
		
		if(levelData[x][y] != null) // there is an object on this position
			throw new Exception("Exception : Try to move an object on an excisting object.");
		
			
		gameObj.setPos(new Position(x,y));
		levelData[x][y] = gameObj;	
	}
	public void makeSlotNullByPosition(Position p) // make the Position arrayData slot null
	{
		if(p != null){			
			int x = p.getX(), y = p.getY();
			levelData[x][y] = null;
		}
	}
	public void levelCompletionCheck() // check's if the level completed successfully
	{
		for(Target target: targetsArray)
			if(target.gotBoxOnMe() == false)
			{
				levelFinishedFlag = false;
				return;
			}
		levelFinishedFlag =  true;
	}
	public ArrayList<String> getLevelByArrayListOfStrings() // returns an ArrayList<String> object of the level data by the convention (box = @, wall = #, character = A, target = o)
	{
		ArrayList<String> levelDataTXT = new ArrayList<String>();	
		StringBuilder sb = new StringBuilder("");
		
		for(int i = 0; i < levelMaxHeight; i++)
		{
			int length = levelData[i].length;
			for(int j = 0; j < length; j++)
			{
				if(levelData[i][j] != null)
					sb.append(levelData[i][j].toString());
				else
					sb.append(" ");
			}
			levelDataTXT.add(new String(sb.toString()));
			sb.setLength(0);
		}
		return levelDataTXT;
	}

	public void movePlayerOnTarget(Character player, Target t) throws Exception // move the player on the target
	{
		if(t.gotGameObjectOnMe() == false)
		{  
			t.setOnMe(player);
		    player.setPos(new Position(t.getPos()));
		}
	}
	
	/*
	public char[][] getLevelByChar2DArray()
	{
		char[][] data = new char[this.levelMaxHeight][];
		for(int i = 0; i < this.levelMaxHeight; i++)
			data[i] = new char[this.levelMaxWidth];

		ArrayList<String> leveldata = getLevelByArrayListOfStrings();
		
		for(int i = 0; i < this.levelMaxHeight; i++)
			for(int j = 0; j < this.levelMaxWidth; j++)
				data[i][j] = leveldata.get(i).charAt(j);
			

		return data;
	}
	*/
}
