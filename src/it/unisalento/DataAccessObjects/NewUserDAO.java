package it.unisalento.DataAccessObjects;

import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Cliente;
import it.unisalento.Model.Utente;






import it.unisalento.business.EmailSender;

import java.security.GeneralSecurityException;
import java.util.Vector;



public final class NewUserDAO extends metodiComuni {
	
	/*private SimpleDateFormat sdf;
	private String data;
	private Date dt=new Date();*/
	private int sesso;

	private static NewUserDAO instance;
	
	public static NewUserDAO getInstance() {
		if(instance == null)
			   instance = new NewUserDAO();
		   return instance;	
	}
 
	public boolean isNewUsername(String username){
		if(Integer.parseInt(DbConnection.getInstance().eseguiQuery("select count(*) from utente where username='"+username+"'").get(0)[0])==0)
			return true;
		return false;
	}
	
	public boolean isNewEmail(String email){
		if(Integer.parseInt(DbConnection.getInstance().eseguiQuery("select count(*) from cliente where email='"+email+"'").get(0)[0])==0)
			return true;
		return false;
	}
	
	
	
	public boolean registraCliente(Cliente c){
		boolean flag=true;
		
		//Registrazione livello utente TODO da sostituire con metodo registra utente
		String query="INSERT INTO `librarium`.`utente` (`nome`, `cognome`, `username`, `password`, `data_registrazione`, `data_ultimo_accesso`) VALUES ('"+c.getNome()+"', '"+c.getCognome()+"', '"+c.getUsername()+"', '"+c.getPassword()+"',NOW() , NOW())";
		if(!DbConnection.getInstance().eseguiAggiornamento(query)) flag=false;
		
		//Registrazione livello cliente
		sesso=0;
		if (c.isSesso()) sesso=1; 
		//sdf=new SimpleDateFormat(c.getData_nascita());
		//data=sdf.format(dt);//Data contiene una stringa con la data formattata per mysql
		query="INSERT INTO `librarium`.`cliente` (`utente_ID`, `sesso`, `data_nascita`, `email`, `numero_telefonico`) VALUES ('"+getUsernameId(c)+"', '"+sesso+"','"+c.getData_nascita()+"' , '"+c.getEmail()+"','"+c.getTelefono()+"')";
		if(!DbConnection.getInstance().eseguiAggiornamento(query)) flag=false;
		
		Vector<String[]> res=DbConnection.getInstance().eseguiQuery("select ID from utente where username='"+c.getUsername()+"'");
		
		c.setId(Integer.parseInt(res.get(0)[0]));
		
		return flag;
	}
		
	private boolean registraUtente(Utente u){
			boolean flag=true;
			
			
			String query="INSERT INTO `librarium`.`utente` (`nome`, `cognome`, `username`, `password`, `data_registrazione`, `data_ultimo_accesso`) VALUES ('"+u.getNome()+"', '"+u.getCognome()+"', '"+u.getUsername()+"', '"+u.getPassword()+"',NOW() , NOW())";
			if(!DbConnection.getInstance().eseguiAggiornamento(query)) flag=false;
			
			
			Vector<String[]> res=DbConnection.getInstance().eseguiQuery("select ID from utente where username='"+u.getUsername()+"'");
			
			u.setId(Integer.parseInt(res.get(0)[0]));
			
			return flag;
			}
		
	public boolean registraAddVendite(Utente u){
			boolean flag=true;
			
			flag=registraUtente(u);
			
			String query="INSERT INTO `addetto_vendite` (`utente_ID`) VALUES ('"+getUsernameId(u)+"')";
			if(!DbConnection.getInstance().eseguiAggiornamento(query)) flag=false;
			
			return flag;
	}

	public boolean registraAddScaffali(Utente u){
		boolean flag=true;
		
		flag=registraUtente(u);
		
		String query="INSERT INTO `addetto_scaffali` (`utente_ID`) VALUES ('"+getUsernameId(u)+"')";
		if(!DbConnection.getInstance().eseguiAggiornamento(query)) flag=false;
		
		
		return flag;
	}

	public void mailBenvenuto(Cliente c) throws GeneralSecurityException {
		
		
		Vector<String[]> id = DbConnection.getInstance().eseguiQuery("SELECT utente_ID FROM cliente WHERE email = '"+c.getEmail()+"'");
		String destinatario = DbConnection.getInstance().eseguiQuery("SELECT nome FROM cliente WHERE email = '"+c.getEmail()+"'").toString();
		EmailSender.getInstance().InviaEmail("OGGETTO: Password Dimenticata", destinatario, c.getEmail(),
				"\nQuesta eMail e' stata inviata da "+DbConnection.getInstance().eseguiQuery("SELECT nomeLibreria FROM dati_libreria")+" per darle il benvenuto nella nostra libreria."
				+ "\nLe sue credeziali sono:\n\nNome: "+DbConnection.getInstance().eseguiQuery("SELECT nome FROM utente WHERE ID = '"+id+"'")+"\nCognome:"+DbConnection.getInstance().eseguiQuery("SELECT nome FROM utente WHERE ID = '"+id+"'")+"\nUsername:"
				+DbConnection.getInstance().eseguiQuery("SELECT username FROM utente WHERE ID = '"+id+"'")+"\nPassword:"+DbConnection.getInstance().eseguiQuery("SELECT password FROM utente WHERE ID = '"+id+"'")+"\n\n\nGrazie di aver scelto Librarium!\n\n\n\n\t\t\t\tDeveloped by E. Moro, G. Marra");
		 
		}
	
	
}

