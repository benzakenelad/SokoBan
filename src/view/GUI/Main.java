package view.GUI;
	
import controller.sokoban.SokobanController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.SokobanModel;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			BorderPane root = (BorderPane)loader.load();
			
			MainWindowController view = (MainWindowController)loader.getController();
			SokobanModel model = new SokobanModel();
			SokobanController controller = new SokobanController(model, view);
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
		launch(args);		
	}
	
}
