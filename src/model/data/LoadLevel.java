package model.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class LoadLevel{
	private HashMap<String, LevelLoader> hm = new HashMap<String, LevelLoader>();
	
	public LoadLevel() {
		hm.put("txt", new MyTextLevelLoader());
		hm.put("obj", new MyObjectLevelLoader());
		hm.put("xml", new MyXMLLevelLoader());
	}
	
	public Level Action(String fileName) throws FileNotFoundException, IOException 
	{
		String s = new String("");
		int ind = fileName.indexOf('.', 0);
		if(ind == -1)
			return null;
		s = fileName.substring(ind + 1, fileName.length()); // find the file suffix
		
		Level l = null;
		LevelLoader ll = null;
		
		ll = hm.get(s);
		
		if(ll != null)
			l = ll.loadLevel(new FileInputStream(fileName));
			
		
		return l;
	}
}
