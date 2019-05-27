package controller;

import interfacce.ILogin;

public class LoginController extends Controller implements ILogin{
	//Credenziali Capo Squadra
	private static final String USERNAME1="mariorossi";
	private static final String PASSWORD1="ingegnere97";
	
	//Credenziali Amministratore
	private static final String USERNAME2="giovannibianchi";
	private static final String PASSWORD2="elezioni2019";
	
	public LoginController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString,pathFileOp,pathFileMsg);
	}
	
	@Override
	public String verificaCredenziali(String username, String password) {
		if(!username.equals(USERNAME1) && !username.equals(USERNAME2))
			return null;
		if( (username.equals(USERNAME1) && !password.equals(PASSWORD1)) 
				|| (username.equals(USERNAME2) && !password.equals(PASSWORD2)) )
			return null;
		if(username.equals(USERNAME1)) { //Capo Squadra
			StringBuilder sb  = new StringBuilder();
			sb.append("<utente>\n");
			sb.append("<rolename>CapoSquadra</rolename>\n");
			sb.append("<nome>Mario</nome>n");
			sb.append("<cognome>Rossi</cognome>\n");
			sb.append("<caserma>AAA</caserma>\n");
			sb.append("</utente>");
			return sb.toString();
		}
		else {
			StringBuilder sb  = new StringBuilder();
			sb.append("<utente>\n");
			sb.append("<rolename>CapoSquadra</rolename>\n");
			sb.append("<nome>Mario</nome>n");
			sb.append("<cognome>Rossi</cognome>\n");
			sb.append("<caserma>AAA</caserma>\n");
			sb.append("</utente>");
			return sb.toString();
		}
	}
}
