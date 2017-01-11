package levels;

import java.util.ArrayList;

public class LevelToArrayListOfStrings {
	public ArrayList<String> cast(Level lvl) // cast a Level object to ArrayList<String> by the convention 
	{
		if(lvl == null)
			return null;
		ArrayList<String> levelDataTXT = new ArrayList<String>();
		GameObject[][] levelData = lvl.getLevelData();
		int height = lvl.getLevelHeight();
		String s = new String("");
		
		for(int i = 0; i < height; i++)
		{
			int length = levelData[i].length;
			for(int j = 0; j < length; j++)
			{
				if(levelData[i][j] != null)
					s += levelData[i][j].toString();
				else
					s += " ";
			}
			levelDataTXT.add(new String(s));
			s = "";
		}
		return levelDataTXT;
	}
}
