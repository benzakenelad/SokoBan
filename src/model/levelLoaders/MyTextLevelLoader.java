package model.levelLoaders;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.data.GernerateGameObject;
import model.data.Level;
import model.data.gameObjects.Box;
import model.data.gameObjects.Character;
import model.data.gameObjects.GameObject;
import model.data.gameObjects.Position;
import model.data.gameObjects.Target;

public class MyTextLevelLoader implements LevelLoader {

	@Override
	public Level loadLevel(InputStream in) throws Exception {
		
		ArrayList<String> levelDataTXT = new ArrayList<String>();
		Level lvl = new Level();

		
		BufferedReader input = new BufferedReader(new InputStreamReader(in));
		
		// read from text file to ArrayList<String>
		String s = new String();
		while(s != null)
		{
			s = input.readLine();
			if(s != null)
				levelDataTXT.add(s);
		}
		
		
		// Temporary objects declaration
		GameObject go = null;
		int height = levelDataTXT.size();
		GameObject[][] levelData = new GameObject[height][];
		GernerateGameObject cgo = new GernerateGameObject();
		GameObject[] lineData = null;
		boolean flag = true;
		
		
		ArrayList<Target> targetsArr = lvl.getTargetsArray();
		ArrayList<Box> boxesArr = lvl.getBoxesArray();
		// levelData objects building
		for(int i = 0; i < height; i++)
		{	
			int length = levelDataTXT.get(i).length();
			lineData = new GameObject[length];
			for(int j = 0; j < length; j++)
			{
				go = cgo.GenerateObject(levelDataTXT.get(i).charAt(j), new Position(i,j));			

				if(go == null)
					continue;
				
				if(go instanceof Character && flag) // flag make sure we have only one character(player)
				{
					lvl.setPlayer((Character)go);
					flag = false;
				}
				
				lineData[j] = go;
				if(go instanceof Target)
					targetsArr.add((Target) go);
				if(go instanceof Box)
					boxesArr.add((Box) go);
			}
			levelData[i] = lineData;
			lineData = null;
			}
		
		
		
		
		
		// level details set
		lvl.setLevelData(levelData);
		levelDataFill(lvl, levelDataTXT);
		
		return lvl;
	}
	
	private void levelDataFill(Level lvl, ArrayList<String> arr)
	{
		int levelWidth = -1;
		int levelHeight = 0;
		int boxCount = 0;
		int targetCount = 0;
		int wallCount = 0;
		int emptyCellCount = 0;
		
		levelHeight = arr.size();

		for(int i = 0; i < levelHeight; i++)
			if(levelWidth < arr.get(i).length())
				levelWidth = arr.get(i).length();
		
		// counting objects
		for(int i = 0; i < levelHeight; i++)
		{
			int length = arr.get(i).length();
			for(int j = 0; j < length; j++)
			{
				char c = arr.get(i).charAt(j);
				switch(c){
				case '#':    // case its wall
					wallCount++;
					break;
				case '@':    // case its box
					boxCount++;
					break;
				case 'o':    // case its target
					targetCount++;
					break;
				case ' ':
					emptyCellCount++;
					break;
				default: 
					break;
				}
			}
		}

		// level data setting
		lvl.setLevelMaxHeight(levelHeight);
		lvl.setLevelMaxWidth(levelWidth);
		lvl.setEmptyCellCount(emptyCellCount);
		lvl.setTargetCount(targetCount);
		lvl.setWallCount(wallCount);
		lvl.setBoxCount(boxCount);
	
	}
	
}
