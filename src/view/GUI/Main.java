package view.GUI;
	
import controller.SokobanController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.SokobanModel;


public class Main extends Application {
	private static String note;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			BorderPane root = (BorderPane)loader.load();
			
			MainWindowController view = (MainWindowController)loader.getController();
			SokobanModel model = new SokobanModel();
			SokobanController controller = new SokobanController(model, view, getNote());
			view.addObserver(controller);
			model.addObserver(controller);

			
			Scene scene = new Scene(root,900,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		String noteForController = new String("");
			
		if(args.length >= 2 && args[0].compareTo("-server") == 0)			
			noteForController = args[0] + " " + args[1];
		
		setNote(noteForController);
		
		launch(args); // application launch		
	}

	public static String getNote() {
		return note;
	}

	public static void setNote(String note) {
		Main.note = note;
	}
	
}
