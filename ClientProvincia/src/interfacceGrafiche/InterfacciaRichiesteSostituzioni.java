package interfacceGrafiche;

import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import controller.ControllerRichiesteSostituzioni;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Mezzo;
import model.Report;
import model.Tipo;

public class InterfacciaRichiesteSostituzioni extends HBox {
	
	private ControllerRichiesteSostituzioni controllerReport = null;
	private Button salva = null;
	private DatePicker data = null;
	private TextField ora = null;
	private TextField litri = null;
	private TextField km = null;
	private ListView<Mezzo> mezzi = null;
	private TextField desc = null;
	private ComboBox<Tipo> tipo = null;
	
	public InterfacciaRichiesteSostituzioni(ControllerRichiesteSostituzioni controllerReport) {
		this.controllerReport=controllerReport;
		salva = new Button("Salva report");
		mezzi = new ListView<Mezzo>();
		tipo = new ComboBox<Tipo>();
		mezzi.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		mezzi.setItems(FXCollections.observableArrayList(controllerReport.getMezzi()));
		tipo.setItems(FXCollections.observableArrayList(Tipo.values()));
		salva.setOnAction(this::salva);
		initGUI();
	}


	private void initGUI() {
		this.setWidth(400);
		this.setHeight(500);
		this.getChildren().add(setTopHbox());
		this.getChildren().add(setMainVbox());
	}
	
	private Node setTopHbox() {
		HBox res = new HBox();
		Label l = new Label("CREAZIONE REPORT");
		l.setTextFill(Color.valueOf("#101010"));
		res.setStyle("-fx-background-color: lightgrey; -fx-border-color: darkgrey;");
		res.setAlignment(Pos.CENTER);
		res.setPrefWidth(300);
		res.setPrefHeight(35);
		res.getChildren().add(l);
		return res;
	}


	private Node setMainVbox() {
		VBox main = new VBox();
		main.setAlignment(Pos.TOP_LEFT);
		main.setPrefWidth(300);
		main.setPrefHeight(465);
		this.setMargin(main, new Insets(0,30,0,30));
		main.getChildren().addAll(firstHbox(),secondHbox(),thirdHbox(),bottomVbox());
		return main;
	}


	private Node firstHbox() {
		HBox res = new HBox();
		res.setPrefWidth(240);
		res.setPrefHeight(46);
		res.setAlignment(Pos.CENTER_LEFT);
		Label l1 = new Label("Data:  ");
		data = new DatePicker();
		data.setPrefWidth(120);
		data.setPrefHeight(25);
		data.setValue(LocalDate.now());
		Label l2 = new Label("Ora:  ");
		l2.setPadding(new Insets(0,0,0,10));
		ora = new TextField();
		ora.setPrefWidth(50);
		ora.setPrefHeight(25);
		ora.setPromptText("12:00");
		res.getChildren().addAll(l1,data,l2,ora);
		return res;
	}


	private Node secondHbox() {
		HBox res=  new HBox();
		res.setPrefWidth(240);
		res.setPrefHeight(38);
		res.setAlignment(Pos.CENTER_LEFT);
		Label l = new Label("Litri carburante consumati:  ");
		litri = new TextField();
		litri.setPromptText("Litri");
		litri.setPrefWidth(50);
		litri.setPrefHeight(25);
		res.getChildren().addAll(l,litri);
		return res;
	}


	private Node thirdHbox() {
		HBox res = new HBox();
		res.setPrefWidth(240);
		res.setPrefHeight(38);
		res.setAlignment(Pos.CENTER_LEFT);
		Label l = new Label("Totale km effettuati:  ");
		km = new TextField();
		km.setPromptText("Km");
		km.setPrefWidth(50);
		km.setPrefHeight(25);
		res.getChildren().addAll(l,km);
		return res;
	}


	private Node bottomVbox() {
		VBox res = new VBox();
		res.setAlignment(Pos.TOP_LEFT);
		res.setPrefWidth(240);
		res.setPrefHeight(300);
		Label l1 = new Label("Mezzi utilizzati  ");
		mezzi.setPrefWidth(240);
		mezzi.setPrefHeight(120);
		Label l2 = new Label("Descrizione:  ");
		desc= new TextField();
		desc.setAlignment(Pos.TOP_LEFT);
		desc.setPrefWidth(240);
		desc.setPrefHeight(120);
		desc.setPromptText("Scrivi qua una descrizione...");
		res.getChildren().addAll(l1,mezzi,setTipo(),l2,desc,setBottom());
		return res;
	}


	private Node setTipo() {
		HBox res = new HBox();
		res.setAlignment(Pos.TOP_LEFT);
		res.setPrefWidth(240);
		res.setPrefHeight(60);
		Label l = new Label("Tipo uscita:  ");
		tipo.getSelectionModel().select(0);
		res.getChildren().addAll(l,tipo);
		return res;
	}


	private Node setBottom() {
		HBox res = new HBox();
		res.setPadding(new Insets(20,0,0,0));
		res.setAlignment(Pos.BOTTOM_CENTER);
		res.setPrefWidth(240);
		res.setPrefHeight(40);
		res.getChildren().add(salva);
		return res;
	}

	public void salva(Event e) {
		try {
			if(checkParametri()) {
				LocalDateTime dataOra = LocalDateTime.of(data.getValue(),LocalTime.parse(ora.getText(),DateTimeFormatter.ISO_LOCAL_TIME));
				int litri = Integer.parseInt(this.litri.getText());
				int km = Integer.parseInt(this.km.getText());
				List<Mezzo> mezzi = new ArrayList<Mezzo>(this.mezzi.getSelectionModel().getSelectedItems());
				String desc = this.desc.getText();
				Tipo tipo = this.tipo.getSelectionModel().getSelectedItem();
				try {
					Report r = new Report(km,litri,desc,tipo,dataOra.format(DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm")),"",mezzi);
					System.out.println("Report che mando in creazione al server: " + r);
					controllerReport.creaReport(r);
				} catch (ClassNotFoundException | IOException | InterruptedException e1) {
					e1.printStackTrace();
					alert("Errore creazione report");
					return;
				}
			}
			else {
				alert("Parametri errati");
				return;
			}
		}catch (Exception e3) {
			e3.printStackTrace();
			alert("Parametri errati");
			return;
		}
		Stage s = (Stage)this.getScene().getWindow();
		s.close();
	}

	private void alert(String p) {
		Alert alert = new Alert(AlertType.ERROR,p);
		alert.showAndWait();
	}

	private boolean checkParametri () throws Exception{
		LocalDate localDate = null;
		LocalTime localTime = null;
		int litri=-1,km=-1;
		List<Mezzo> mezzi = null;
		String desc = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		if(data.getValue()!=null && formatter.format(data.getValue())!=null) {
			localDate = data.getValue();
		}
		formatter = DateTimeFormatter.ISO_LOCAL_TIME;
		if(ora.getText()!=null && formatter.parse(ora.getText())!=null) {
			localTime = LocalTime.parse(ora.getText(),formatter);
		}
		if(this.litri.getText()!=null) {
			try {
			litri = Integer.parseInt(this.litri.getText());
			} catch(NumberFormatException e) {
				e.printStackTrace();
				return false;
			}
		}
		if(this.km.getText()!=null) {
			try {
			km = Integer.parseInt(this.km.getText());
			} catch(NumberFormatException e) {
				e.printStackTrace();
				return false;
			}
		}
		if(this.mezzi.getSelectionModel().getSelectedItems()!=null)
			mezzi = this.mezzi.getSelectionModel().getSelectedItems();
		if(this.desc.getText()!=null)
			desc = this.desc.getText();
		if(desc!=null && mezzi!=null && km>0 && litri>0 &&  localDate!=null &&localTime!=null)
			return true;
		return false;
	}
	
	
}
