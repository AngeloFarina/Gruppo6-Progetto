package controller;

import interfacce.IGestioneMezzi;
import model.Caserma;
import model.Mezzo;
import model.RichiestaSostituzione;

public class GestioneMezziProvinciaController extends Controller implements IGestioneMezzi {
	
	private ModificaMezzi modMezzi;
	private GestioneSostituzione gestSost;
	
	public GestioneMezziProvinciaController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
		modMezzi = new ModificaMezzi(getConnection());
		gestSost = new GestioneSostituzione(getConnection());
	}
	
	public boolean modificaMezzo(String tipoRichiesta, Mezzo m, Caserma c) {
		switch (tipoRichiesta) {
		case "elimina":
			modMezzi.eliminaMezzo(m);
			break;
		case "aggiungi":
			modMezzi.aggiungiMezzo(m, c);
			break;
		case "modifica":
			modMezzi.modificaMezzo(m);
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
}
