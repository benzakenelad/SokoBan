package model.data;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class MyXMLLevelLoader implements LevelLoader {

	@Override
	public Level loadLevel(InputStream in) throws Exception {
		BufferedInputStream bos = new BufferedInputStream(in);
		XMLDecoder input = new XMLDecoder(bos);
		
		Level lvl = (Level) input.readObject();
		
		in.close();
		bos.close();
		input.close();
		
		return lvl;
	}

}
