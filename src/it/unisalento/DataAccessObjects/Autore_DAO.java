package it.unisalento.DataAccessObjects;

import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Autore;

public class Autore_DAO {

	private static Autore_DAO instance;
	
	public static Autore_DAO getInstance(){
		if (instance==null) 
			instance=new Autore_DAO();
		return instance;
	}
	
	public boolean inserisciAutore(Autore a){
		return DbConnection.getInstance().eseguiAggiornamento("insert into autore (nome, cognome) values ('"+a.getNome()+"', '"+a.getCognome()+"')");
		}
	
	public boolean elemina(Autore a){
		return 	DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`autore` WHERE `ID_autore`='"+a.getId()+"'");

	}
	
	}
