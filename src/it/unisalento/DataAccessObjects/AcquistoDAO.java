package it.unisalento.DataAccessObjects;

import java.util.Iterator;
import java.util.Vector;

import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Acquisto;
import it.unisalento.Model.Libro;

public class AcquistoDAO {

	private static AcquistoDAO instance;
	
	public static AcquistoDAO getInstance(){
		if (instance==null) 
			instance=new AcquistoDAO();
		return instance;
	}
	
	public boolean inserisci(Acquisto a){
		boolean flag=true;
		
		if(!DbConnection.getInstance().eseguiAggiornamento("insert into acquisto (incasso, cliente_utente_ID) values ('"+a.getIncasso()+"', '"+a.getCliente_id()+"')")) flag=false;
		
		a.setId(Integer.parseInt(DbConnection.getInstance().eseguiQuery("select ID_acquisto from acquisto").lastElement()[0]));
		
		for(int i=0; i<a.getLibri().size(); i++){
			if(!DbConnection.getInstance().eseguiAggiornamento("insert into acquisto_has_libro (acquisto_ID_acquisto, libro_ID_libro) values ('"+a.getId()+"', '"+a.getLibri().get(i).getId()+"')")) flag=false;
		}
		
		return flag;
	}
	
	
	public Vector<Acquisto> caricaById(int cliente_id){
		
		Vector<Acquisto> acquisti=new Vector<Acquisto>();
		Vector<String[]> resA=DbConnection.getInstance().eseguiQuery("select * from acquisto where cliente_utente_ID='"+cliente_id+"'");
		acquisti.setSize(resA.size());
		
		
		
		for(int i=0; i<resA.size(); i++){
			
			Vector<String[]> resL=DbConnection.getInstance().eseguiQuery("select libro_ID_libro from acquisto_has_libro where acquisto_ID_acquisto='"+resA.get(i)[0]+"' ");
			acquisti.get(i)=new Acquisto(Integer.parseInt(resA.get(i)[0]),)
			
		}
		
		//Vector<String[]> resL=DbConnection.getInstance().eseguiQuery("select libro_ID_libro from acquisto_has_libro where acquisto_ID_acquisto='' ");
		
	}
	
	
}
