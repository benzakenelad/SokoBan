package levels;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyXMLLevelLoader implements LevelLoader {

	@Override
	public Level loadLevel(InputStream in) throws IOException {
		BufferedInputStream bos = new BufferedInputStream(in);
		@SuppressWarnings("resource")
		XMLDecoder input = new XMLDecoder(bos);
		Level l = null;
		l = (Level) input.readObject();
		bos.close();
		in.close();
		
		return l;
	}

}
