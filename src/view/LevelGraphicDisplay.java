package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class LevelGraphicDisplay extends Canvas
{
	// Data Members
	private ArrayList<String> levelData = null;
	double maxWidth = 0;
	double maxHeight = 0;
	
	// Properties
	private StringProperty playerFileName;
	private StringProperty wallFileName;
    private StringProperty floorFileName;
    private StringProperty boxFileName;
	private StringProperty targetFileName;
	private StringProperty boxOnTargetFileName;
	private StringProperty playerOnTargetFileName;
	private StringProperty congratulationsFileName;

	// C'TOR
	public LevelGraphicDisplay() {
		playerFileName = new SimpleStringProperty();
		wallFileName = new SimpleStringProperty();
		floorFileName = new SimpleStringProperty();
		boxFileName = new SimpleStringProperty();
		targetFileName = new SimpleStringProperty();
		boxFileName = new SimpleStringProperty();
		boxOnTargetFileName = new SimpleStringProperty();
		playerOnTargetFileName = new SimpleStringProperty();
		congratulationsFileName = new SimpleStringProperty();
	}
	
	public void finishDraw()
	{
		Image congra = null;
		try {
			congra = new Image(new FileInputStream(congratulationsFileName.get()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GraphicsContext gc = getGraphicsContext2D();
		if(congra != null)
			gc.drawImage(congra, 0, 0, getWidth(), getHeight());
	}
	
	public void redraw()
	{
		if(levelData == null)
			return;
		
		double levelWidth = getWidth();
		double levelHeight = getHeight();
		double itemWidth = levelWidth / this.maxWidth;
		double itemHeight = levelHeight / this.maxHeight;
		
		GraphicsContext gc = getGraphicsContext2D();
		
		
		Image wall = null;
		Image player = null;
		Image floor = null;
		Image box = null;
		Image target = null;
		Image playerontarget = null;
		Image boxontarget = null;
		try {
			wall = new Image(new FileInputStream(wallFileName.get()));
			player = new Image(new FileInputStream(playerFileName.get()));
			floor = new Image(new FileInputStream(floorFileName.get()));
			box = new Image(new FileInputStream(boxFileName.get()));
			target = new Image(new FileInputStream(targetFileName.get()));
			playerontarget = new Image(new FileInputStream(playerOnTargetFileName.get()));
			boxontarget = new Image(new FileInputStream(boxOnTargetFileName.get()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < levelData.size(); i++)
			for(int j = 0; j < levelData.get(i).length(); j++)
			{
				switch (levelData.get(i).charAt(j)) {
				case '#':
					gc.drawImage(wall, j * itemWidth , i * itemHeight , itemWidth, itemHeight);
					break;
				case ' ':
					gc.drawImage(floor, j * itemWidth , i * itemHeight , itemWidth, itemHeight);
					break;
				case '@':
					gc.drawImage(box, j * itemWidth , i * itemHeight , itemWidth, itemHeight);
					break;
				case 'A':
					gc.drawImage(player, j * itemWidth , i * itemHeight , itemWidth, itemHeight);
					break;
				case 'o':
					gc.drawImage(target, j * itemWidth , i * itemHeight , itemWidth, itemHeight);
					break;
				case 'B':
					gc.drawImage(playerontarget, j * itemWidth , i * itemHeight , itemWidth, itemHeight);
					break;
				case '$':
					gc.drawImage(boxontarget, j * itemWidth , i * itemHeight , itemWidth, itemHeight);
					break;
				default:
					
					break;
				}	
			}	
	}
	
	public void clear()
	{
		this.getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
	}
	
	// Getters and Setters
	public void setLevelData(ArrayList<String> levelData)
	{
		this.levelData = levelData;
		redraw();
	}
	
	public void setMaxWidth(int maxWidth) throws Exception {
		if(maxWidth > 0)
			this.maxWidth = (double)maxWidth;
		else
			throw new Exception("None positive width exception");
	}

	public void setMaxHeight(int maxHeight) throws Exception {
		if(maxHeight > 0)
			this.maxHeight = (double)maxHeight;
		else
			throw new Exception("None positive height exception.");
	}
	

	public String getPlayerFileName() {
		return playerFileName.get();
	}

	public void setPlayerFileName(String playerFileName) {
		this.playerFileName.set(playerFileName);
	}

	public String getWallFileName() {
		return wallFileName.get();
	}

	public void setWallFileName(String wallFileName) {
		this.wallFileName.set(wallFileName);
	}

	public String getFloorFileName() {
		return floorFileName.get();
	}

	public void setFloorFileName(String floorFileName) {
		this.floorFileName.set(floorFileName);
	}

	public String getBoxFileName() {
		return boxFileName.get();
	}

	public void setBoxFileName(String boxFileName) {
		this.boxFileName.set(boxFileName);
	}

	public String getTargetFileName() {
		return targetFileName.get();
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName.set(targetFileName);
	}

	public String getBoxOnTargetFileName() {
		return boxOnTargetFileName.get();
	}

	public void setBoxOnTargetFileName(String boxOnTargetFileName) {
		this.boxOnTargetFileName.set(boxOnTargetFileName);
	}

	public String getPlayerOnTargetFileName() {
		return playerOnTargetFileName.get();
	}

	public void setPlayerOnTargetFileName(String playerOnTargetFileName) {
		this.playerOnTargetFileName.set(playerOnTargetFileName);
	}

	public String getCongratulationsFileName() {
		return congratulationsFileName.get();
	}

	public void setCongratulationsFileName(String congratulationsFileName) {
		this.congratulationsFileName.set(congratulationsFileName);
	}


}
