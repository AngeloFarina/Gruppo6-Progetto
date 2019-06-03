package interfacceGrafiche;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class InterfacciaVigile extends BorderPane {
	private static final Insets PADDING = new Insets(10);
	
	
	private TextArea textArea = new TextArea();
	private TextArea output = new TextArea();
	private Button salva = new Button("IN MANUTENZIONE");
	private Button calcola = new Button("DISPONIBILE");
	
	public InterfacciaVigile(){
		initGui();
	}

	private void initGui() {
		setStyle("-fx-background: white;");
		setTop(createTop());
		setLeft(createLeft());
		setBottom(createBottom());
		setData();
		this.setPadding(PADDING);
	}

	private void setData() {
		salva.setOnAction(this::salva);
		calcola.setOnAction(this::calcola);
	}


	private Node createBottom() {
		HBox hbox = new HBox();
		hbox.setSpacing(10);
		hbox.getChildren().addAll(calcola,salva);
		hbox.setPadding(PADDING);
		hbox.setAlignment(Pos.BOTTOM_LEFT);
		return hbox;
	}

	private Node createLeft() {
		output.setText("JEEP        AA123AA     \nAUTOPOMPA        BB111BB    ");
		return output;
	}

	private Node createTop() {
		VBox vbox = new VBox();
		
		vbox.getChildren().add(new Label("LISTA MEZZI"));
		vbox.setSpacing(10);
		vbox.setPadding(PADDING);
		vbox.setAlignment(Pos.BASELINE_CENTER);
		return vbox;
	}
	
	private void salva(ActionEvent event){
	
	}


private void calcola(ActionEvent event){
	}
	
}
