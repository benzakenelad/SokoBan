package model.data;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MyTextLevelSaver implements LevelSaver {

	@Override
	public void saveLevel(OutputStream out, Level lvl) throws Exception{
		ArrayList<String> levelDataTXT = lvl.getLevelByArrayListOfStrings();
		int size = levelDataTXT.size();
		
		PrintWriter output = new PrintWriter(out, true);
		
		for(int i = 0; i < size; i++)
			output.println(levelDataTXT.get(i));
		
		out.close();
		output.close();
	}
}
