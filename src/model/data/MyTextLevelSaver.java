package model.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MyTextLevelSaver implements LevelSaver {

	@Override
	public void saveLevel(String fileName, Level lvl) throws IOException {
		if(lvl == null)
			return;
		ArrayList<String> levelDataTXT = lvl.getLevelByArrayListOfStrings();
		int size = levelDataTXT.size();
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		fw = new FileWriter(fileName);
		bw = new BufferedWriter(fw);
		
		for(int i = 0; i < size; i++)
		{		
			bw.write(levelDataTXT.get(i));
			if(i != size - 1)
				bw.newLine();		
		}
		
		bw.close();	
		fw.close();	
	}
}
