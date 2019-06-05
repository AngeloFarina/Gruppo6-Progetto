package application;

import interfacceGrafiche.InterfacciaLogin;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application{

	public void start(Stage stage) throws Exception {
		stage.setTitle("Login");
		InterfacciaLogin login = new InterfacciaLogin();
		stage.setResizable(false);
		stage.setScene(new Scene(login));
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
