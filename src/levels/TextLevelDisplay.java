package levels;

import java.util.ArrayList;

public class TextLevelDisplay implements LevelDisplay{

	@Override
	public void Display(Level lvl) {
		if(lvl == null)
			return;
		ArrayList<String> levelDataTXT = new LevelToArrayListOfStrings().cast(lvl);
		for(String s: levelDataTXT)
			System.out.println(s);
	}

}
