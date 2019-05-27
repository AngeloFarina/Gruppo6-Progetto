package controller;

import java.util.List;

import interfacce.IVisualizzaMezzi;
import model.Caserma;
import model.Mezzo;

public class VisualizzaMezziCasermaController extends Controller implements IVisualizzaMezzi {

	public VisualizzaMezziCasermaController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
	}

	@Override
	public List<Mezzo> visualizzaMezzi(Caserma c) {
		List<Mezzo> mezzi = null;
		return mezzi;
	}
	
}
