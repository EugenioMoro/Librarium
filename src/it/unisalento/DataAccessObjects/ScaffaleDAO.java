package it.unisalento.DataAccessObjects;

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
		
		Vector<Scaffale> scaffali=new Vector<Scaffale>();
		scaffali.setSize(g.size());
		
		for (int i=0; i<g.size(); i++){
			scaffali.set(i, new Scaffale(LibroDAO.getInstance().caricaPerGenere(g.get(i))));
		}
		
		return scaffali;
	
			
		}
		
		
	}

