package controller;

import interfacce.IVisualizzaMezzi;

public class VisualizzaMezziCasermaController extends Controller implements IVisualizzaMezzi {

	public VisualizzaMezziCasermaController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
	}

}
