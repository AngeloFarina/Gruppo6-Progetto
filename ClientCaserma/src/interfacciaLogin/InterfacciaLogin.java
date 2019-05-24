package interfacciaLogin;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InterfacciaLogin extends BorderPane{
	private TextField username;
	private PasswordField password;
	private static final Insets PADDING = new Insets(10);
	private static final double SPACING = 15;
	
	public InterfacciaLogin() {
		username = new TextField();
		password = new PasswordField();
		initGUI();
	}

	private void initGUI() {
		setPadding(PADDING);
		setCenter(setCenter());
	}

	private Node setCenter() {
		VBox v = new VBox();
		v.setPadding(PADDING);
		v.setSpacing(SPACING);
		v.getChildren().addAll(new Label("LOGIN"),username,password, new Button("  ENTRA  "));
		return v;
	}
	
	
	
	
}
