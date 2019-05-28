package application;


import interfacciaCaserma.InterfacciaCapoSquadra;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CapoSquadraApplication extends Application {

	public void start(Stage stage) {
		stage.setTitle("Gestione Mezzi Vigili del Fuoco");
		InterfacciaCapoSquadra root= new InterfacciaCapoSquadra();

		Scene scene = new Scene(root, 1280,720);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String args[]) {
		launch(args);
	}
	
}
