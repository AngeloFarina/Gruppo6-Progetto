package controller;

import interfacceGrafiche.InterfacciaAmministratore;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainProva extends Application {

	@Override
	public void start(Stage s) throws Exception {
		InterfacciaAmministratore a = new InterfacciaAmministratore(new ControllerClientProvincia("BO001", "Ciao"));
		Scene sc  = new Scene(a);
		s.setScene(sc);
		s.show();
	}
	
	public static void main(String args[]) {
		launch(args);
	}

}
