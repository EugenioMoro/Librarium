package it.unisalento.DataAccessObjects;

import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Utente;

public  class metodiComuni { //questa classe che contiene i metodi comuni ed utili per le altre DAO deve essere ereditata

	
	
	public int getUsernameId(Utente u){ //restituisce ID associato all'username dell'utente passato
		String getIdQuery="select ID from librarium.utente where username='"+u.getUsername()+"'";
		return Integer.parseInt(DbConnection.getInstance().eseguiQuery(getIdQuery).get(0)[0]); //cast da stringa ad intero e restituisce
	}
	



}
