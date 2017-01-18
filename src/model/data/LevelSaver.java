package model.data;

import java.io.OutputStream;

public interface LevelSaver {
	public void saveLevel(OutputStream out, Level lvl) throws Exception;
}
