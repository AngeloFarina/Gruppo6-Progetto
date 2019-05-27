package controller;

import interfacce.IStoricoManutenzioni;
import model.Mezzo;
import model.RichiestaSostituzione;

public class StoricoManutenzioniController extends Controller implements IStoricoManutenzioni {

	public StoricoManutenzioniController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effettuaSostizione(Mezzo m, RichiestaSostituzione r) {
		// TODO Auto-generated method stub

	}

}
