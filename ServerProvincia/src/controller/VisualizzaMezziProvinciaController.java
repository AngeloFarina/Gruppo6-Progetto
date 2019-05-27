package controller;

import interfacce.IVisualizzaMezzi;

public class VisualizzaMezziProvinciaController extends Controller implements IVisualizzaMezzi{

	public VisualizzaMezziProvinciaController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
	}

}
