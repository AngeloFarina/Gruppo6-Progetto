package interfacce;

import java.sql.SQLException;
import java.util.List;

import model.Manutenzione;

public interface IStoricoManutenzioni {
	public List<Manutenzione> listaManutenzioni() throws SQLException;
}
