package it.unisalento.DataAccessObjects;

import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Cliente;
import it.unisalento.Model.Libro;
import it.unisalento.Model.Richiesta;

import java.util.Vector;

public class RichiestaDAO extends metodiComuni {

	
	
	boolean flag=true;
//Singleton
private static RichiestaDAO instance;
	
	public static RichiestaDAO getInstance() {
		if(instance == null)
			   instance = new RichiestaDAO();
		   return instance;	
	}
 
		
	

//Carica  libro e autore
	
	public boolean ordinaLibro(Libro l, Cliente c) 
	{
		//if (!DbConnection.getInstance().eseguiAggiornamento("UPDATE libro SET  flag_ordine=1 WHERE ID_libro='"+l.getId()+"'")) flag=false;
		
		if (!DbConnection.getInstance().eseguiAggiornamento("INSERT INTO richiesta (cliente_utente_ID, libro_ID_libro) VALUES ('"+c.getId()+"','"+l.getId()+"')"))
			flag=false;
		return flag;
	}

//Storici richieste
	//richiesta effettuata ma non inoltrata
	public Vector<Richiesta> RichiesteEffettuate() {
		Vector<Richiesta> richieste=new Vector<Richiesta>();
		Vector<String[]> res=DbConnection.getInstance().eseguiQuery("SELECT ID_richiesta, cliente_utente_ID, libro_ID_libro, data_richiesta FROM richiesta WHERE flag_inoltro=0");
		
		richieste.setSize(res.size());
		
		for(int i=0; i<res.size(); i++){
			
			richieste.set(i, new Richiesta(Integer.parseInt(res.get(i)[0]), Integer.parseInt(res.get(i)[1]),Integer.parseInt(res.get(i)[2]) , stringToDate(res.get(i)[3])));
			
		}
		return richieste;
	}
	//richiesta inoltrata ma no arrivata
	public Vector<Richiesta> RichiesteInoltrate() {
		Vector<Richiesta> richieste=new Vector<Richiesta>();
		Vector<String[]> res=DbConnection.getInstance().eseguiQuery("SELECT ID_richiesta, cliente_utente_ID, libro_ID_libro, data_richiesta FROM richiesta WHERE flag_inoltro=1 AND flag_arrivo=0");
		
		richieste.setSize(res.size());
		
		for(int i=0; i<res.size(); i++){
			
			richieste.set(i, new Richiesta(Integer.parseInt(res.get(i)[0]), Integer.parseInt(res.get(i)[1]),Integer.parseInt(res.get(i)[2]) , stringToDate(res.get(i)[3])));
			}
		return richieste;
	}
	
	
	//richiesta arrivata
	public Vector<Richiesta> RichiesteArrivate() {
		Vector<Richiesta> richieste=new Vector<Richiesta>();
		Vector<String[]> res=DbConnection.getInstance().eseguiQuery("SELECT ID_richiesta, cliente_utente_ID, libro_ID_libro, data_richiesta, data_arrivo FROM richiesta WHERE flag_arrivo=1");
		richieste.setSize(res.size());
		
		for(int i=0; i<res.size(); i++){
			
			richieste.set(i,  new Richiesta(Integer.parseInt(res.get(i)[0]), Integer.parseInt(res.get(i)[1]), Integer.parseInt(res.get(i)[2]), stringToDate(res.get(i)[3]), stringToDate(res.get(i)[4]) ));
			}
		return richieste;
	}
	
	
	//tutte le richieste
	public Vector<Richiesta> RichiesteStorico() {
		
		Vector<Richiesta> richieste=new Vector<Richiesta>();
		Vector<String[]> res=DbConnection.getInstance().eseguiQuery("SELECT ID_richiesta, cliente_utente_ID, libro_ID_libro, data_richiesta, data_arrivo, flag_inoltro, flag_arrivo FROM richiesta");
		java.sql.Date d = null;
		
		richieste.setSize(res.size());
		
		for(int i=0; i<res.size(); i++){
			if (intToBoolean(Integer.parseInt(res.get(i)[6]))) {
			richieste.set(i, new Richiesta(Integer.parseInt(res.get(i)[0]), Integer.parseInt(res.get(i)[1]),Integer.parseInt(res.get(i)[2]) , stringToDate(res.get(i)[3]), stringToDate(res.get(i)[4]), intToBoolean(Integer.parseInt(res.get(i)[5])), intToBoolean(Integer.parseInt(res.get(i)[6]))));
			}
			else 
				richieste.set(i, new Richiesta(Integer.parseInt(res.get(i)[0]), Integer.parseInt(res.get(i)[1]),Integer.parseInt(res.get(i)[2]) , stringToDate(res.get(i)[3]), d, intToBoolean(Integer.parseInt(res.get(i)[5])), intToBoolean(Integer.parseInt(res.get(i)[6]))));
		}
		return richieste;
	
	}
	
	//set flag inoltro
	public boolean SetInoltro(Richiesta r) {
		if (!DbConnection.getInstance().eseguiAggiornamento("UPDATE richiesta SET flag_inoltro=1 WHERE ID_richiesta='"+r.getID_richiesta()+"'")) 
			flag=false;
		return flag;
		}
	//set flag arrivo
	public boolean SetArrivo(Richiesta r) {
		if (!DbConnection.getInstance().eseguiAggiornamento("UPDATE richiesta SET flag_arrivo=1, data_arrivo=NOW() WHERE ID_richiesta='"+r.getID_richiesta()+"'")) 
			flag=false;
		return flag;
		}
	
	
	}

