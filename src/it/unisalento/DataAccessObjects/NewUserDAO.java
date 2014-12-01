package it.unisalento.DataAccessObjects;

import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Cliente;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


public final class NewUserDAO {
	
	private SimpleDateFormat sdf;
	private String data;
	private Date dt=new Date();

	private NewUserDAO instance;
	
	public NewUserDAO getInstance() {
		if(instance == null)
			   instance = new NewUserDAO();
		   return instance;	
	}
	
	private boolean registraCliente(Cliente c){
		boolean flag=true;
		
		
		String query1="INSERT INTO `librarium`.`utente` (`nome`, `cognome`, `username`, `password`, `data_registrazione`, `data_ultimo_accesso`) VALUES ('Mario', 'Rossi', 'mRossi', '123asd','NOW()' , NOW())";
		if(!DbConnection.getInstance().eseguiAggiornamento(query1)) flag=false;
		
		sdf=new SimpleDateFormat(c.getData_nascita());
		data=sdf.format(dt);
		String getIdQuery="select ID from librarium.utente where username='"+c.getUsername()+"'";
		Vector<String[]>results=DbConnection.getInstance().eseguiQuery(getIdQuery);
		String query="INSERT INTO `librarium`.`cliente` (`utente_ID`, `sesso`, `data_nascita`, `email`, `numero_telefonico`) VALUES ('"+results.get(0)[0]+"', '0','"+data+"' , 'mario.rossi@yahoo.it','0832458710')";
		if(!DbConnection.getInstance().eseguiAggiornamento(query)) flag=false;
		
		
	}
}
