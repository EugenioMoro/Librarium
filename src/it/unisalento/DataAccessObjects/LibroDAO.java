package it.unisalento.DataAccessObjects;

import java.util.Iterator;
import java.util.Vector;

import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Autore;
import it.unisalento.Model.Genere;
import it.unisalento.Model.Libro;

public class LibroDAO extends metodiComuni{

	private static LibroDAO instance;
	
	public static LibroDAO getInstance(){
		if (instance==null)
			instance=new LibroDAO();
		return instance;
	}
	
	   private int lastID_libro(){
		   Vector<String[]> res=DbConnection.getInstance().eseguiQuery("select ID_libro from libro");
		   return Integer.parseInt(res.lastElement()[0]);
	   }
	
	public boolean inserisciLibro(Libro l){
		boolean flag=true;
		
		//cast da booleano ad intero
		int richiesto=l.isRichiesto() ? 1 : 0;
		
		if(!DbConnection.getInstance().eseguiAggiornamento("insert into libro (`titolo`, `genere_ID_genere`, `casa_editrice_ID_casa_editrice`, `costo`, `disponibilita`, `flag_ordine`, `ISBN`, `numero_pagine`) values ('"+l.getTitolo()+"', '"+l.getGenere().getId()+"', '"+l.getCasaEd().getId()+"', '"+l.getCosto()+"', '"+l.getDisp()+"', '"+richiesto+"', '"+l.getIsbn()+"', '"+l.getPagine()+"')")) flag=false;
		
		l.setId(lastID_libro());
		
		flag=inserisciAutori(l);
		
		
		return flag;
	}
	
	private boolean inserisciAutori(Libro l){
		
		boolean flag=true;
		
		if (l.getAutori().size()==1){
			if(!DbConnection.getInstance().eseguiAggiornamento("insert into libro_has_autore (libro_ID_libro, autore_ID_autore) values ('"+l.getId()+"', '"+l.getAutori().get(0).getId()+"')")) flag=false;
		}
			else{
				Iterator<Autore> i=l.getAutori().iterator();
				
				while (i.hasNext()){
					if(!DbConnection.getInstance().eseguiAggiornamento("insert into libro_has_autore (libro_ID_libro, autore_ID_autore) values ('"+l.getId()+"', '"+i.next().getId()+"')")) flag=false;
				}
			}
		
		
		return flag;
		
	}
	
	public Vector<Libro> CaricaPerGenere(Genere g) {
		Vector<Libro> libri = new Vector<Libro>();
		
		Vector<String[]> res = DbConnection.getInstance().eseguiQuery("SELECT * FROM libro WHERE genere_ID_genere = '"+g.getId()+"'");
		Vector<String[]> resL;
		
		libri.setSize(res.size());
		
		for(int i=0; i<res.size(); i++)
		{
			resL = DbConnection.getInstance().eseguiQuery("SELECT autore_ID_autore FROM libro_has_autore WHERE libro_ID_libro = '"+Integer.parseInt(res.get(i)[0])+"'");
			
			Vector<Autore> autori = new Vector<Autore>();
			autori.setSize(resL.size());
			DbConnection.getInstance().eseguiQuery("SELECT ")
			for (int j=0; j<resL.size(); j++)
			{
				autori.add(new Autore(Integer.parseInt(DbConnection.getInstance().eDseguiQuery("SELECT ")), nome, cognome));
			}
		}
		
		
	}
	
}