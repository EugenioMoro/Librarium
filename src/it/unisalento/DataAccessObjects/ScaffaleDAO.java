package it.unisalento.DataAccessObjects;

import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Genere;
import it.unisalento.Model.Scaffale;

import java.util.Vector;

public class ScaffaleDAO {
	
	private static ScaffaleDAO instance;
	
	public static ScaffaleDAO getInstance(){
		if(instance==null) instance=new ScaffaleDAO();
		return instance;
	}
	
	
	public Vector<Scaffale> caricaScaffali(Vector<Genere> g){
		
		Vector<String[]> res;
		
		Vector<Scaffale> scaffali = new Vector<Scaffale>();
		scaffali.setSize(g.size());
		
		for (int i=0; i<g.size(); i++) {
			res=DbConnection.getInstance().eseguiQuery("SELECT ");
			
		}
		
		
	}
}
