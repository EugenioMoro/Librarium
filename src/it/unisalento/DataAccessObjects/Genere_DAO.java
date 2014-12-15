package it.unisalento.DataAccessObjects;

import java.util.Vector;

import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Genere;

public class Genere_DAO {

	private static Genere_DAO instance;
	
	public static Genere_DAO getInstance(){
		if (instance == null)
			instance=new Genere_DAO();
		return instance;
	}
	
	public boolean inserisciGenere(Genere g){
		return DbConnection.getInstance().eseguiAggiornamento("insert into genere (nome_genere) values ('"+g.getNome()+"')");
	}
	
	public boolean elimina(Genere g){
		return 	DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`genere` WHERE `ID_genere`='"+g.getId()+"'");
	}
	
	public Vector<Genere> caricaGeneri(){
		Vector<String[]> res=DbConnection.getInstance().eseguiQuery("select * from genere");
		Vector<Genere> generi=new Vector<Genere>();
		generi.setSize(res.size());
		
		for(int i=0; i<res.size(); i++)
			generi.add(i, new Genere(Integer.parseInt(res.get(i)[0]), res.get(i)[1]));
		
		return generi;
	}
}