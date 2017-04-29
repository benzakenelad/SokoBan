package model.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MyTextLevelLoader implements LevelLoader {

	@Override
	public Level loadLevel(InputStream in) throws Exception {
		
		ArrayList<String> levelDataTXT = new ArrayList<String>();
		Level lvl = new Level();
		String s = new String();
		
		BufferedReader input = new BufferedReader(new InputStreamReader(in));
		
		// read from text file to ArrayList<String>
		while(s != null)
		{
			s = input.readLine();
			if(s != null)
				levelDataTXT.add(s);
		}
		
		
		// Temporary objects declaration
		Position p = new Position();
		GameObject go;
		int height = levelDataTXT.size();
		GameObject[][] levelData = new GameObject[height][];
		GernerateGameObject cgo = new GernerateGameObject();
		GameObject[] lineData = null;
		char ch;
		boolean flag = true;
		
		
		// levelData objects building
		for(int i = 0; i < height; i++)
		{	
			int length = levelDataTXT.get(i).length();
			lineData = new GameObject[length];
			for(int j = 0; j < length; j++)
			{
				p.setX(i);
				p.setY(j);
				ch = levelDataTXT.get(i).charAt(j);
				go = cgo.GenerateObject(ch);			
				
				if(go == null)
					continue;
				
				go.setPos(new Position(p));
				if(ch == 'A' && flag) // flag make sure we have only one character(player)
				{
					lvl.setCC((Character)go);
					flag = false;
				}
				lineData[j] = go;
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
		lvl.setLevelHeight(levelHeight);
		lvl.setLevelWidth(levelWidth);
		lvl.setEmptyCellCount(emptyCellCount);
		lvl.setTargetCount(targetCount);
		lvl.setWallCount(wallCount);
		lvl.setBoxCount(boxCount);
	
	}
}
