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
	
	public boolean nuovoAutore(Autore a){
		boolean flag=true;
		
		//inserisce dati
		if(!DbConnection.getInstance().eseguiAggiornamento("insert into autore (nome, cognome) values ('"+a.getNome()+"', '"+a.getCognome()+"')")) flag=false;
		
		//recupera id e completa a
		a.setId(Integer.parseInt(DbConnection.getInstance().eseguiQuery("select max(ID_autore) from autore").lastElement()[0]));
		
		return flag;
	}
	
	public boolean elemina(Autore a){
		return 	DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`autore` WHERE `ID_autore`='"+a.getId()+"'");

		}
	
	public Vector<Autore> caricaAutori(){
		
		Vector<String[]> res=DbConnection.getInstance().eseguiQuery("select * from autore");
		Vector<Autore> autori=new Vector<Autore>();
		autori.setSize(res.size());
		
		for (int i=0; i<res.size(); i++)
			autori.set(i, new Autore(Integer.parseInt(res.get(i)[0]), res.get(i)[1], res.get(i)[2]));
			
		return autori;
		
	}
	
	public void modificaNome(int id, String n){
		DbConnection.getInstance().eseguiAggiornamento("UPDATE autore SET nome = '"+n+"' WHERE ID_autore = '"+id+"'");
	}
	
	public void modificaCognome(int id, String c){
		DbConnection.getInstance().eseguiAggiornamento("UPDATE autore SET cognome = '"+c+"' WHERE ID_autore = '"+id+"'");
	}
	
	public void aggiungiALibro(int idAutore, int idLibro){
		DbConnection.getInstance().eseguiAggiornamento("insert into libro_has_autore (libro_ID_libro, autore_ID_autore) values ('"+idLibro+"', '"+idAutore+"')");
	}
	
	public void rimuoviDaLibro(int idLibro, int idAutore){
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM libro_has_autore WHERE libro_ID_libro='"+idLibro+"' and autore_ID_autore ='"+idAutore+"';");
	}
	
	}
