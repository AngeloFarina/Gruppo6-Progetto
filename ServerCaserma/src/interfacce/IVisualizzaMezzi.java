package interfacce;

import java.sql.SQLException;
import java.util.List;

import model.Mezzo;

public interface IVisualizzaMezzi {
	public List<Mezzo> visualizzaMezzi(String c) throws SQLException;		
}
