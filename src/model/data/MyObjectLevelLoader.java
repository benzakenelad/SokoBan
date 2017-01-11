package model.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class MyObjectLevelLoader implements LevelLoader {

	@Override
	public Level loadLevel(InputStream in) throws IOException {
		Level l = null;		
		
		ObjectInputStream input = new ObjectInputStream(in);
		try {
			l = (Level)input.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		input.close();
		in.close();		
		
		return l;
	}

}
