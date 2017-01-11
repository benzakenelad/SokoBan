package levels;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyXMLLevelSaver implements LevelSaver {

	@Override
	public void saveLevel(String fileName, Level lvl) throws IOException {
		if(lvl == null)
			return;
		XMLEncoder out = null;
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		
		fos = new FileOutputStream(fileName);	
		bos = new BufferedOutputStream(fos);
		out = new XMLEncoder(bos);
		
		out.writeObject(lvl);
		
		out.close();
		bos.close();
		fos.close();
		
	}

}
