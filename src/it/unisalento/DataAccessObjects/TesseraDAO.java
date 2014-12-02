package it.unisalento.DataAccessObjects;


import java.util.Vector;

import it.unisalento.Model.Cliente;
import it.unisalento.Model.Tessera;
import it.unisalento.DbConnection.*;



public class TesseraDAO {

	
	
	public static TesseraDAO instance;
	
	public static TesseraDAO getInstance(){
		if(instance==null)
			instance=new TesseraDAO();
		return instance;
	}
	
	
	


	public Tessera caricaTessera(Cliente c){
		
		Vector<String[]> ID=DbConnection.getInstance().eseguiQuery("select ID from utente where username='"+c.getUsername()+"'");
		Vector<String[]> results=DbConnection.getInstance().eseguiQuery("select codice_tessera, punti from tessera where cliente_utente_ID='"+ID.get(0)[0]+"'");
		
		Tessera newT=new Tessera(results.get(0)[0], Integer.parseInt(results.get(0)[1]));
		return newT;
		
	}

	public boolean nuovaTessera(Tessera t, Cliente c){
		
		String query="insert into tessera (cliente_utente_ID, codice_tessera, punti) values ('"+Integer.parseInt(DbConnection.getInstance().eseguiQuery("select ID from utente where username='"+c.getUsername()+"'").get(0)[0])+"', '"+t.getCodice()+"', '"+t.getPunti()+"')";
		return DbConnection.getInstance().eseguiAggiornamento(query);
	}
	
}
