package model.data;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyTextLevelLoader implements LevelLoader {

	@Override
	public Level loadLevel(InputStream in) throws IOException {
		
		ArrayList<String> levelDataTXT = new ArrayList<String>();
		Level l = new Level();
		String s = new String();
		
		// read from text file to ArrayList<String>
		
		BufferedInputStream reader = new BufferedInputStream(in);		
		int c = 0;	
		while(c != -1)
		{
			c = 0;
			while(c != 10 && c != -1)
			{	
				c = reader.read();	
				if(c != 10 && c != -1 && c != 13)
					s += (char)c;
			}
			levelDataTXT.add(new String(s));
			s = "";
		}
		reader.close();
		in.close();
		
		// Temporary objects declaration
		Position p = new Position();
		GameObject go = new GameObject();
		int height = levelDataTXT.size();
		GameObject[][] levelData = new GameObject[height][];
		CreateGameObject cgo = new CreateGameObject();
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
				go = cgo.GenerateGerneralObject(ch);			
				
				if(go == null)
					continue;
				
				go.setPos(new Position(p));
				if(ch == 'A' && flag) // flag make sure we have only one character(player)
				{
					l.setCC((Character)go);
					flag = false;
				}
				lineData[j] = go;
			}
			levelData[i] = lineData;
			lineData = null;
			}
		
		// level details set
		l.setLevelData(levelData);
		levelDataFill(l, levelDataTXT);
		
		
		return l;
	}
	
	public void levelDataFill(Level l, ArrayList<String> arr)
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
		
		for(int i = 0; i < levelHeight; i++)
		{
			int length = arr.get(i).length();
			for(int j = 0; j < length; j++)
			{
				char c = arr.get(i).charAt(j);
				switch(c){
				case '#':
					wallCount++;
					break;
				case '@':
					boxCount++;
					break;
				case 'o':
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

		l.setLevelHeight(levelHeight);
		l.setLevelWidth(levelWidth);
		l.setEmptyCellCount(emptyCellCount);
		l.setTargetCount(targetCount);
		l.setWallCount(wallCount);
		l.setBoxCount(boxCount);
	
	}
}
