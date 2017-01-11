package model.data;

import java.io.IOException;
import java.util.HashMap;

public class SaveLevel {
	private HashMap<String, LevelSaver> hm = new HashMap<String, LevelSaver>();
	
	public SaveLevel() {
		hm.put("txt", new MyTextLevelSaver());
		hm.put("obj", new MyObjectLevelSaver());
		hm.put("xml", new MyXMLLevelSaver());
	}
	public void Action(Level lvl, String fileName) throws IOException
	{
		String s = new String("");
		int ind = fileName.indexOf('.', 0);
		if(ind == -1)
			return;
		s = fileName.substring(ind + 1, fileName.length()); // find the file suffix
		
		LevelSaver ls = null;
		ls = hm.get(s);
		if(ls != null) // invalid suffix
		{
			ls.saveLevel(fileName, lvl);
			System.out.println("The level has been successfully saved");
		}
		else
			System.out.println("Illegel file name, The level was not saved");
		
	}
}
