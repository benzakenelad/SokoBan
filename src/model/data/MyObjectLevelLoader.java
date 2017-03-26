package model.data;

import java.io.InputStream;
import java.io.ObjectInputStream;

public class MyObjectLevelLoader implements LevelLoader {

	@Override
	public Level loadLevel(InputStream in) throws Exception {
		Level lvl = null;		
		
		ObjectInputStream input = new ObjectInputStream(in);
		try {
			lvl = (Level)input.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		input.close();
		in.close();		
		
		return lvl;
	}

}
