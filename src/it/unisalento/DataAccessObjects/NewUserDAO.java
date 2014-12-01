package it.unisalento.DataAccessObjects;

import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Cliente;
import it.unisalento.Model.Utente;


import java.text.SimpleDateFormat;
import java.util.Date;



public final class NewUserDAO extends metodiComuni {
	
	private SimpleDateFormat sdf;
	private String data;
	private Date dt=new Date();
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
	
	public int porcdio(String email){
		return Integer.parseInt(DbConnection.getInstance().eseguiQuery("select count(*) from cliente where email='"+email+"'").get(0)[0]);
	}
	
	
	public boolean registraCliente(Cliente c){
		boolean flag=true;
		
		//Registrazione livello utente
		String query="INSERT INTO `librarium`.`utente` (`nome`, `cognome`, `username`, `password`, `data_registrazione`, `data_ultimo_accesso`) VALUES ('"+c.getNome()+"', '"+c.getCognome()+"', '"+c.getUsername()+"', '"+c.getPassword()+"',NOW() , NOW())";
		if(!DbConnection.getInstance().eseguiAggiornamento(query)) flag=false;
		
		//Registrazione livello cliente
		sesso=0;
		if (c.isSesso()) sesso=1; 
		sdf=new SimpleDateFormat(c.getData_nascita());
		data=sdf.format(dt);//Data contiene una stringa con la data formattata per mysql
		query="INSERT INTO `librarium`.`cliente` (`utente_ID`, `sesso`, `data_nascita`, `email`, `numero_telefonico`) VALUES ('"+getUsernameId(c)+"', '"+sesso+"','"+data+"' , '"+c.getEmail()+"','"+c.getTelefono()+"')";
		if(!DbConnection.getInstance().eseguiAggiornamento(query)) flag=false;
		
		return flag;
	}
		
	private boolean registraUtente(Utente u){
			boolean flag=true;
			
			String query="INSERT INTO `librarium`.`utente` (`nome`, `cognome`, `username`, `password`, `data_registrazione`, `data_ultimo_accesso`) VALUES ('"+u.getNome()+"', '"+u.getCognome()+"', '"+u.getUsername()+"', '"+u.getPassword()+"',NOW() , NOW())";
			if(!DbConnection.getInstance().eseguiAggiornamento(query)) flag=false;
			
			
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

}

