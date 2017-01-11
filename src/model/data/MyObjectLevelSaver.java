package model.data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MyObjectLevelSaver implements LevelSaver {

	@Override
	public void saveLevel(String fileName, Level lvl) throws IOException {
		if(lvl == null)
			return;
		
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		
		fileOut = new FileOutputStream(fileName);
		out = new ObjectOutputStream(fileOut);
		
		out.writeObject(lvl);
		out.close();
		fileOut.close();
	}

}
