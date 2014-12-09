package it.unisalento.DataAccessObjects;

import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Cliente;
import it.unisalento.Model.Libro;
import it.unisalento.Model.Richiesta;
import it.unisalento.Model.Utente;
import it.unisalento.Model.Autore;

import java.util.Scanner;
import java.util.Vector;

public class RichiestaDAO {

	
	
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
		if (!DbConnection.getInstance().eseguiAggiornamento("UPDATE libro SET  flag_ordine=1 WHERE ID_libro='"+l.getId()+"'")) flag=false;
		
		if (!DbConnection.getInstance().eseguiAggiornamento("INSERT INTO 'richiesta' ('cliente_utente_ID', 'libro_ID_libro') VALUES ('"+c.getId()+"','"+l.getId()+"')"))
			flag=false;
		return flag;
	}

//Storici richieste
	//richiesta effettuata ma non inoltrata
	public Vector<String[]> RichiesteEffettuate() {
		return DbConnection.getInstance().eseguiQuery("SELECT ID_richiesta, cliente_utente_ID, libro_ID_libro, data_richiesta FROM richiesta WHERE flag_inoltro=0");
	}
	//richiesta inoltrata ma no arrivata
	public Vector<String[]> RichiesteInoltrate() {
		return DbConnection.getInstance().eseguiQuery("SELECT ID_richiesta, cliente_utente_ID, libro_ID_libro, data_richiesta FROM richiesta WHERE flag_inoltro=1 AND flag_arrivo=0");
	}
	//richiesta arrivata
	public Vector<String[]> RichiesteArrivate() {
		return DbConnection.getInstance().eseguiQuery("SELECT ID_richiesta, cliente_utente_ID, libro_ID_libro, data_richiesta, data_arrivo FROM richiesta WHERE flag_arrivo=1");
	}
	//tutte le richieste
	public Vector<String[]> RichiesteStorico() {
		return DbConnection.getInstance().eseguiQuery("SELECT ID_richiesta, cliente_utente_ID, libro_ID_libro, data_richiesta, data_arrivo FROM richiesta");
	}
	
	//set flag inoltro
	public boolean SetInoltro(Richiesta r) {
		if (!DbConnection.getInstance().eseguiAggiornamento("UPDATE richiesta SET flag_inoltro=1 WHERE ID_richiesta='"+r.getID_richiesta()+"'")) 
			flag=false;
		return flag;
		}
	//set flag arrivo
	public boolean SetArrivo(Richiesta r) {
		if (!DbConnection.getInstance().eseguiAggiornamento("UPDATE richiesta SET flag_arrivo=1 WHERE ID_richiesta='"+r.getID_richiesta()+"'")) 
			flag=false;
		return flag;
		}
	
	
	}

