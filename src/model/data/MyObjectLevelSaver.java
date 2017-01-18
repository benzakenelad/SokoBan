package model.data;

import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjectLevelSaver implements LevelSaver {

	@Override
	public void saveLevel(OutputStream out, Level lvl) throws Exception {
		ObjectOutputStream output = null;
		
		output = new ObjectOutputStream(out);
		
		output.writeObject(lvl);
		
		out.close();
		output.close();
	}

}
