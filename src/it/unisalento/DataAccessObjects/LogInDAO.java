package it.unisalento.DataAccessObjects;
import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Cliente;
import it.unisalento.Model.Utente;
import it.unisalento.business.EmailSender;

import java.util.Vector;

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
			newU.setData_ultimo_accesso(stringToDate(results.get(0)[2]));
			newU.setId(Integer.parseInt(results.get(0)[3]));
			newU.setUsername(u.getUsername());
			DbConnection.getInstance().eseguiAggiornamento("update utente set data_ultimo_accesso=NOW() where ID='"+newU.getId()+"'");

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
		
		public Cliente dettagliClientePerID(int id){
			Vector<String[]> results=DbConnection.getInstance().eseguiQuery("SELECT nome, cognome, email, numero_telefonico FROM utente inner JOIN cliente ON utente.ID="+id+" and cliente.utente_ID="+id+";");
			Cliente c=new Cliente();
			c.setNome(results.get(0)[0]);
			c.setCognome(results.get(0)[1]);
			c.setEmail(results.get(0)[2]);
			c.setTelefono(results.get(0)[3]);
			return c;
		}
		
		public void cambiaPassword(Cliente c, String p){
			DbConnection.getInstance().eseguiAggiornamento("update utente set password='"+p+"' where ID='"+c.getId()+"'");
			c.setPassword(p);
		}
		
		public void cambiaEmail(Cliente c, String e){
			DbConnection.getInstance().eseguiAggiornamento("update cliente set email='"+e+"' where utente_ID='"+c.getId()+"'");
			c.setEmail(e);
		}
		
		public void cambiaTelefono(Cliente c, String t){
			DbConnection.getInstance().eseguiAggiornamento("update cliente set numero_telefonico='"+t+"' where utente_ID='"+c.getId()+"'");
			c.setTelefono(t);
		}
	

		public void passDimenticata(String email) {
			
			
			Vector<String[]> id = DbConnection.getInstance().eseguiQuery("SELECT utente_ID FROM cliente WHERE email = '"+email+"'");
			String destinatario = DbConnection.getInstance().eseguiQuery("SELECT nome FROM cliente WHERE email = '"+email+"'").toString();
		EmailSender.getInstance().InviaEmail("OGGETTO: Password Dimenticata", destinatario, email,
					"\nQuesta eMail e' stata inviata da "+DbConnection.getInstance().eseguiQuery("SELECT nomeLibreria FROM dati_libreria")+" per una richiesta di password dimenticata. Se non e' stato Lei a fare tale "
					+ "richiesta La preghiamo di ignorare questo messaggio.\n\nNome: "+DbConnection.getInstance().eseguiQuery("SELECT nome FROM utente WHERE ID = '"+id+"'")+"\nCognome:"+DbConnection.getInstance().eseguiQuery("SELECT nome FROM utente WHERE ID = '"+id+"'")+"\nUsername:"
					+DbConnection.getInstance().eseguiQuery("SELECT username FROM utente WHERE ID = '"+id+"'")+"\nPassword:"+DbConnection.getInstance().eseguiQuery("SELECT password FROM utente WHERE ID = '"+id+"'")+"\n\n\nGrazie di aver scelto Librarium!\n\n\n\n\t\t\t\tDeveloped by E. Moro, G. Marra");
			 
			}
		
		
	
}


