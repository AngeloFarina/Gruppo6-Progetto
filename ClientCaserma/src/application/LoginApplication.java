package application;

import interfacciaLogin.InterfacciaLogin;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginApplication extends Application{

	public void start(Stage stage) throws Exception {
		stage.setTitle("Login");
		InterfacciaLogin login = new InterfacciaLogin();
		stage.setResizable(false);
		stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth()/3);
		stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight()/4);
		stage.setX(Screen.getPrimary().getVisualBounds().getMaxX()/3);
		stage.setY(Screen.getPrimary().getVisualBounds().getMaxY()/3);
		stage.setScene(new Scene(login));
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
