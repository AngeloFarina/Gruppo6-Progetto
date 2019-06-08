package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfacce.IGestioneMezzi;
import model.Assegnazione;
import model.Caserma;
import model.Mezzo;
import model.RichiestaSostituzione;
import model.Stato;

public class GestioneMezziProvinciaController extends Controller implements IGestioneMezzi {
	
	private ModificaMezzi modMezzi;
	private GestioneSostituzione gestSost;
	
	public GestioneMezziProvinciaController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
		modMezzi = new ModificaMezzi(getConnection());
		gestSost = new GestioneSostituzione(getConnection());
	}
	
	public boolean modificaMezzo(String tipoRichiesta, Mezzo m, String c) {
		switch (tipoRichiesta) {
		case "elimina":
			modMezzi.eliminaMezzo(m);
			break;
		case "aggiungi":
			modMezzi.aggiungiMezzo(m, c);
			break;
		case "modifica":
			modMezzi.modificaMezzo(m,c);
			break;
		default:
			return false;
		}
		return true;
	}
	
	public boolean effettuaSostituzione(Mezzo m, RichiestaSostituzione r) {
		gestSost.effettuaSostituzione(m, r);
		return true;
	}
	
	public static void main(String[] args) throws SQLException {
		GestioneMezziProvinciaController controller = new GestioneMezziProvinciaController("", "", "");
		//controller.modificaMezzo("aggiungi", new Mezzo("PROVAPO", "PROVA", 2090, "GUCCI", "GANG", Stato.DISPONIBILE, Assegnazione.PROPRIO), new Caserma("BO001", "Bologna", 4500, new ArrayList<Mezzo>()));
		//controller.modificaMezzo("modifica", new Mezzo("BG799EI", "MODIFICA", 2090, "MODIFICA", "MODIFICA", Stato.DISPONIBILE, Assegnazione.PROPRIO), new Caserma("BO001", "Bologna", 4500, new ArrayList<Mezzo>()));
		VisualizzaMezziProvinciaController c = new VisualizzaMezziProvinciaController("", "", "");
		List<Mezzo> l =c.visualizzaMezzi("BO001");
		for (Mezzo mezzo : l) {
			System.out.println(mezzo.toString());
		}
	}
}
