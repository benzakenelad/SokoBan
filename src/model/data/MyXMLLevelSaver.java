package model.data;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.OutputStream;

public class MyXMLLevelSaver implements LevelSaver {

	@Override
	public void saveLevel(OutputStream out, Level lvl) throws Exception {
		
		
		BufferedOutputStream bos = new BufferedOutputStream(out);
		XMLEncoder output = new XMLEncoder(bos);
		
		output.writeObject(lvl);
		
		out.close();
		output.close();
	}

}
