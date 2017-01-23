package view.GUI;

import java.io.File;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.data.Level;
import view.LevelGraphicDisplay;

public  class MainWindowController extends Observable implements View, Initializable
{
	// Data Members
	@FXML
	LevelGraphicDisplay levelGraphicDisplay;
	@FXML
	Text steps;
	@FXML	
	Text timer;
	 
	
	//Data Members
	private boolean resetTimerFlag = true;
	private int count = 0;
	private StringProperty counter;
	Timer t = null;
	
	public MainWindowController() {

		//Music initialization
		String musicFile = "./resources/song.mp3";   
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
	
	
	public void saveFile()
	{
		FileChooser fc = new FileChooser();
		fc.setTitle("Save SokoBan's Level File");
		fc.setInitialDirectory(new File("./Levels"));
		File chosen = fc.showSaveDialog(null);
		if(chosen != null)
		{
			setChanged();
			notifyObservers("save " + chosen.getPath());
		}
	}
	
	public void openFile() 
	{
		FileChooser fc = new FileChooser();
		fc.setTitle("Open SokoBan's Level File");
		fc.setInitialDirectory(new File("./Levels"));
		File chosen = fc.showOpenDialog(null);
		if(chosen != null)
		{
			resetTimerFlag = true;
			setChanged();
			notifyObservers("load " + chosen.getPath());
		}
	}
	@Override
	public void Display(Level lvl) {
		if(lvl == null)
		{
			levelGraphicDisplay.finishDraw();
			return;
		}
		try {
			levelGraphicDisplay.setMaxHeight(lvl.getLevelHeight());
			levelGraphicDisplay.setMaxWidth(lvl.getLevelWidth());
		} catch (Exception e) {	e.printStackTrace();}
		levelGraphicDisplay.setLevelData(lvl.getLevelByArrayListOfStrings()); 
		steps.setText("Steps: " + Integer.toString(lvl.getSteps()));
		
		if(resetTimerFlag == true)
		{
			this.resetTimer();
			resetTimerFlag = false;
		}
	}
	
	public void safeExit()
	{
		setChanged();
		notifyObservers("exit");
		Platform.exit();
		System.exit(0);
	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// time initialize
		counter = new SimpleStringProperty();
		
		t=new  Timer();
		t.scheduleAtFixedRate(new TimerTask() {
				
			@Override
			public void run() {
				counter.set("Timer: "+(count++));
			}
		}, 0, 1000);
	
		
		//key function initialization
		levelGraphicDisplay.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->levelGraphicDisplay.requestFocus());
		levelGraphicDisplay.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					setChanged();
					notifyObservers("move up");
					break;
				case DOWN:
					setChanged();
					notifyObservers("move down");
					break;
				case LEFT:
					setChanged();
					notifyObservers("move left");
					break;
				case RIGHT:
					setChanged();
					notifyObservers("move right");
					break;
				default:
					break;
				}
				levelGraphicDisplay.requestFocus();
			}
		});
	}
	
	public void resetTimer()
	{
		count=0;
		try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}
		timer.textProperty().bind(counter);
		timer.setVisible(true);
	}
}
