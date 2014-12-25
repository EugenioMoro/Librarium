package it.unisalento.DataAccessObjects;
import java.util.Vector;

import it.unisalento.DbConnection.*;
import it.unisalento.Model.*;

public class LogInDAO extends metodiComuni{
			
		private static LogInDAO instance;

		
		
		public static LogInDAO getInstance(){
			if (instance==null)
				instance=new LogInDAO();
			return instance;
		}
	
		public boolean checkCredenziali(Utente u){
			if (Integer.parseInt(DbConnection.getInstance().eseguiQuery("select count(*) from utente where username='"+u.getUsername()+"' and password='"+u.getPassword()+"'").get(0)[0])!=0)
				return true;
			return false;
		}
		
		public String checkTipo(Utente u){ //scelgo di ritornare una stringa in modo di rendere il codice di livello superiore più leggibile
			if (Integer.parseInt(DbConnection.getInstance().eseguiQuery("select count(*) from cliente where utente_ID='"+getUsernameId(u)+"'").get(0)[0])==1)
				return "Cliente";
			if (Integer.parseInt(DbConnection.getInstance().eseguiQuery("select count(*) from addetto_vendite where utente_ID='"+getUsernameId(u)+"'").get(0)[0])==1)
				return "Vendite";
			return "Scaffali";
		}
		
		public Utente caricaUtente(Utente u){
			Utente newU=new Utente(u.getUsername(), u.getPassword());
		
			Vector<String[]> results=DbConnection.getInstance().eseguiQuery("select nome, cognome, data_ultimo_accesso, ID from utente where username='"+u.getUsername()+"'");
			newU.setNome(results.get(0)[0]);
			newU.setCognome(results.get(0)[1]);
			newU.setId(Integer.parseInt(results.get(0)[3]));
			newU.setUsername(u.getUsername());

			return newU;
		}
			
		public Cliente caricaCliente(Utente u){  //1)carica utente generico 2) costruisce cliente e carica dati rimanenti 
			Utente newU=caricaUtente(u);
			boolean sesso=true;
			
			
			Cliente newC=new Cliente(newU.getId(), newU.getUsername(), newU.getPassword(), newU.getNome(), newU.getCognome(), newU.getData_ultimo_accesso() );
			
			Vector<String[]> results=DbConnection.getInstance().eseguiQuery("select sesso, data_nascita, email, numero_telefonico from cliente where utente_ID='"+getUsernameId(newU)+"'");
			if(Integer.parseInt(results.get(0)[0])==0) sesso=false;
			newC.setSesso(sesso);
			newC.setData_nascita(stringToDate(results.get(0)[1]));
			newC.setEmail(results.get(0)[2]);
			newC.setTelefono(results.get(0)[3]);
			
			return newC;
		}
	

}
