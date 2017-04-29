package searchLib.mazeExample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import searchLib.Position;

public class levelTXTLoad {

	public MazeLevel load(InputStream in) throws IOException {

		ArrayList<String> stringsData = new ArrayList<String>(); // holder
		BufferedReader input = new BufferedReader(new InputStreamReader(in)); // data input stream

		String str;
		while (input.ready()) { // data reading
			str = input.readLine();
			if (str != null)
				stringsData.add(str);
		}

		int highet = stringsData.size();

		char[][] levelData = new char[highet][];

		for (int i = 0; i < highet; i++) // data cast to char[][]
			levelData[i] = stringsData.get(i).toCharArray();
		
		MazeLevel maze = new MazeLevel();
		
		maze.setLevelData(levelData);
		
		for(int i = 0; i < levelData.length; i++)
			for(int j = 0; j < levelData[i].length; j++)
			{
				if(levelData[i][j] == 'A')
					maze.setPlayer(new Position(i, j));
				if(levelData[i][j] == 'G')
					maze.setWinPosition(new Position(i, j));
			}
		maze.setWidth(levelData[0].length);
		maze.setHighet(levelData.length);
		
		return maze;
	}
}
