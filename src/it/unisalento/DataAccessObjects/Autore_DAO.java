package it.unisalento.DataAccessObjects;

import java.util.Vector;

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
	
	public Vector<Autore> caricaAutori(){
		
		Vector<String[]> res=DbConnection.getInstance().eseguiQuery("select * from autore");
		Vector<Autore> autori=new Vector<Autore>();
		autori.setSize(res.size());
		
		for (int i=0; i<res.size(); i++)
			autori.add(i, new Autore(Integer.parseInt(res.get(i)[0]), res.get(i)[1], res.get(i)[2]));
			
		return autori;
		
	}
	
	}