package view.GUI;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.data.Level;
import view.LevelGraphicDisplay;

public class MainWindowController extends Observable implements View, Initializable {
	// Data Members
	@FXML
	private LevelGraphicDisplay levelGraphicDisplay;
	@FXML
	private Text steps;
	@FXML
	private Text timer;

	private RecordsTableWindowController recordsTableWindowController;
	private Double counter = new Double(0); // time count
	private StringProperty countString; // timer string
	private Timer timerThread = null; // timer
	private MediaPlayer mediaPlayer = null; // media player
	private boolean levelCompleted = false;;

	// initialization
	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {

		// start the background music
		startMusic();

		// draw the welcome image
		levelGraphicDisplay.welcomeDraw();

		// time initialize
		countString = new SimpleStringProperty("Timer : 0");
		timer.textProperty().bind(countString);
		timer.setVisible(true);

		// key function initialization
		levelGraphicDisplay.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> levelGraphicDisplay.requestFocus());
		levelGraphicDisplay.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					if(levelCompleted == false)
					{
					setChanged();
					notifyObservers("move up");
					}
					break;
				case DOWN:
					if(levelCompleted == false)
					{
					setChanged();
					notifyObservers("move down");
					}
					break;
				case LEFT:
					if(levelCompleted == false)
					{
					setChanged();
					notifyObservers("move left");
					}
					break;
				case RIGHT:
					if(levelCompleted == false)
					{
					setChanged();
					notifyObservers("move right");
					}
					break;
				case ESCAPE:
					close();
					break;
				case O:
					openFile();
					break;
				case S:
					saveFile();
				default:
					break;
				}
				levelGraphicDisplay.requestFocus();
			}
		});
	}

	// Record Table Window display/hide
	public void displayRecordsTable() {
		recordsTableWindowController.displayRecordsTable();
	}

	public void hideRecordsTable() {
		recordsTableWindowController.hideRecordsTable();
	}

	// Methods
	@Override
	public void Display(Level lvl) {
		if(lvl == null || levelCompleted == true)
			return;

		this.setStepsText("Steps: " + Integer.toString(lvl.getSteps())); // update the steps string
		
		if (lvl.isLevelFinishedFlag() == true)
		{
			levelCompleted = true;
			this.finishAct(lvl);	
			return;
		}

		try {
			levelGraphicDisplay.setMaxHeight(lvl.getLevelHeight());
			levelGraphicDisplay.setMaxWidth(lvl.getLevelWidth());
		} catch (Exception e) {
			e.printStackTrace();
		}

		levelGraphicDisplay.setLevelData(lvl.getLevelByArrayListOfStrings()); // set the new level data
	}

	public void resetTimer() // reset the game timer
	{
		counter = 0.0;
		timerThread = new Timer();
		timerThread.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				counter += 0.1;

				counter = BigDecimal.valueOf(counter).setScale(3, RoundingMode.HALF_UP).doubleValue();

				countString.set("Timer: " + counter);
			}
		}, 0, 100);

	}

	private void startMusic() // start the background music
	{
		// Music initialization
		String musicFile = "./resources/song.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}

	public void saveFile() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Save SokoBan's Level File");
		fc.setInitialDirectory(new File("./Levels"));
		File chosen = fc.showSaveDialog(null);
		if (chosen != null) {
			setChanged();
			notifyObservers("save " + chosen.getPath());
		}
	}

	public void openFile() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Open SokoBan's Level File");
		fc.setInitialDirectory(new File("./Levels"));
		File chosen = fc.showOpenDialog(null);
		if (chosen != null) {
			System.out.println(chosen.getPath()); // print the path
			resetTimer();
			setChanged();
			notifyObservers("load Levels/" + chosen.getName());
		}
		levelCompleted = false;
	}

	public void finishAct(Level lvl) {
		lvl.setFinishTime(counter.doubleValue());
		timerThread.cancel();
		levelGraphicDisplay.finishDraw();

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Record List");
				alert.setHeaderText(null);
				alert.setContentText("Would you like to enroll the recrod list?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					recordsTableWindowController.addArecord(lvl.getID(), lvl.getSteps(), lvl.getFinishTime());
				} else {
					return;
				}
			}
		});

	}

	public void close() {
		setChanged();
		notifyObservers("exit");
	}

	public void safeExit(int value) {
		mediaPlayer.stop();
		recordsTableWindowController.close();
		if (timerThread != null)
			timerThread.cancel();
		Platform.exit();
	}

	private void setStepsText(String text) {
		steps.setText(text);
	}

	public void setRecordsTableWindowController(RecordsTableWindowController recordsTableWindowController) {
		this.recordsTableWindowController = recordsTableWindowController;
	}

}
