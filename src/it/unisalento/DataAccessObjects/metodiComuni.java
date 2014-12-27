package it.unisalento.DataAccessObjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Utente;

public  class metodiComuni { //questa classe che contiene i metodi comuni ed utili per le altre DAO deve essere ereditata

	
	
	public int getUsernameId(Utente u){ //restituisce ID associato all'username dell'utente passato
		String getIdQuery="select ID from librarium.utente where username='"+u.getUsername()+"'";
		return Integer.parseInt(DbConnection.getInstance().eseguiQuery(getIdQuery).get(0)[0]); //cast da stringa ad intero e restituisce
	}
	

	public java.sql.Date stringToDate(String dateInString){
		SimpleDateFormat formatter;
		if (dateInString.length()>11)
			formatter= new SimpleDateFormat("yyyy-MM-dd H:m:s");
		else formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		try {
	 
			date = formatter.parse(dateInString);
			
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return new java.sql.Date(date.getTime());
	}
	
	
	public boolean intToBoolean(int i) {
		
		
		 if (i==1) return true;
		 return false;
		
	}

}
