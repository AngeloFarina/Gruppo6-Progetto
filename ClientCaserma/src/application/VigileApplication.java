package application;

import java.sql.SQLException;

import controller.ControllerClientCaserma;
import interfacceGrafiche.InterfacciaVigile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class VigileApplication extends Application {

	public void start(Stage stage) {
		stage.setTitle("Gestione Mezzi Vigili del Fuoco");
		InterfacciaVigile root = null;
		try {
			root = new InterfacciaVigile(new ControllerClientCaserma("BO001","Antonio Corradi"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root, 1000, 500, Color.ALICEBLUE);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String []args) {
		launch(args);
	}
	
}
