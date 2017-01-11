package model.data;

import java.io.IOException;

public interface LevelSaver {
	public void saveLevel(String fileName, Level lvl) throws IOException;
}
