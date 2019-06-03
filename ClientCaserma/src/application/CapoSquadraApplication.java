package application;


import java.sql.SQLException;

import controller.ControllerClientCaserma;
import interfacciaCaserma.InterfacciaCapoSquadra;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CapoSquadraApplication extends Application {

	public void start(Stage stage) {
		stage.setTitle("Gestione Mezzi Vigili del Fuoco");
		InterfacciaCapoSquadra root = null;
		try {
			root = new InterfacciaCapoSquadra(new ControllerClientCaserma("BO001","Mario Rossi"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		root.setPrefSize(1280, 720);
		Scene scene = new Scene(root, 1280,720,Color.WHITE);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String args[]) {
		launch(args);
		System.out.println("bella ragazzi");
	}
	
}
