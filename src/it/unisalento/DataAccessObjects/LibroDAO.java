package it.unisalento.DataAccessObjects;

import java.util.Iterator;
import java.util.Vector;

import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Autore;
import it.unisalento.Model.CasaEd;
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
	
	public Vector<Libro> caricaPerGenere(Genere g) {
		
		Vector<Libro> libri = new Vector<Libro>();
		Vector<String[]> resL = DbConnection.getInstance().eseguiQuery("SELECT * FROM libro WHERE genere_ID_genere = '"+g.getId()+"'");
		Vector<String[]> resA;
		libri.setSize(resL.size());
		
		for(int i=0; i<resL.size(); i++)
		{
			//livello singolo libro
			
			resA = DbConnection.getInstance().eseguiQuery("SELECT autore_ID_autore FROM libro_has_autore WHERE libro_ID_libro = '"+Integer.parseInt(resL.get(i)[0])+"'");
			Vector<Autore> autori = new Vector<Autore>();
			autori.setSize(resA.size());
			for (int j=0; j<resA.size(); j++){
				//livello singolo autore
				Vector<String[]> temp=DbConnection.getInstance().eseguiQuery("select * from autore where ID_autore='"+Integer.parseInt(resA.get(j)[0])+"' ");
				autori.set(j, new Autore(Integer.parseInt(temp.get(0)[0]), temp.get(0)[1], temp.get(0)[2]));
				
			}
			Libro l=new Libro();
			l.setId(Integer.parseInt(resL.get(i)[0]));
			l.setTitolo(resL.get(i)[1]);
			l.setGenere(new Genere(Integer.parseInt(resL.get(i)[2]), DbConnection.getInstance().eseguiQuery("select nome_genere from genere where ID_genere='"+resL.get(i)[2]+"'").get(0)[0]));
			l.setCasaEd(new CasaEd(Integer.parseInt(resL.get(i)[3]), DbConnection.getInstance().eseguiQuery("select nome_casa_editrice from casa_editrice where ID_casa_editrice='"+resL.get(i)[3]+"'").get(0)[0]));
			l.setCosto(Float.parseFloat(resL.get(i)[4]));
			l.setDisp(Integer.parseInt(resL.get(i)[5]));
			l.setIsbn(resL.get(i)[7]);
			l.setPagine(Integer.parseInt(resL.get(i)[8]));
			l.setAutori(autori);
			libri.set(i, l);
		}
		return libri;
		
	}
	
	public Vector<Libro> caricaTutti(){
		Vector<Libro> libri=new Vector<Libro>();
		Vector<String[]> resL = DbConnection.getInstance().eseguiQuery("SELECT * FROM libro");
		Vector<String[]> resA;
		libri.setSize(resL.size());
		
		for(int i=0; i<resL.size(); i++)
		{
			//livello singolo libro
			
			resA = DbConnection.getInstance().eseguiQuery("SELECT autore_ID_autore FROM libro_has_autore WHERE libro_ID_libro = '"+Integer.parseInt(resL.get(i)[0])+"'");
			Vector<Autore> autori = new Vector<Autore>();
			autori.setSize(resA.size());
			for (int j=0; j<resA.size(); j++){
				
				//livello singolo autore
				
				Vector<String[]> temp=DbConnection.getInstance().eseguiQuery("select * from autore where ID_autore='"+Integer.parseInt(resA.get(j)[0])+"' ");
				autori.set(j, new Autore(Integer.parseInt(temp.get(0)[0]), temp.get(0)[1], temp.get(0)[2]));
				
			}
			Libro l=new Libro();
			l.setId(Integer.parseInt(resL.get(i)[0]));
			l.setTitolo(resL.get(i)[1]);
			l.setGenere(new Genere(Integer.parseInt(resL.get(i)[2]), DbConnection.getInstance().eseguiQuery("select nome_genere from genere where ID_genere='"+resL.get(i)[2]+"'").get(0)[0]));
			l.setCasaEd(new CasaEd(Integer.parseInt(resL.get(i)[3]), DbConnection.getInstance().eseguiQuery("select nome_casa_editrice from casa_editrice where ID_casa_editrice='"+resL.get(i)[3]+"'").get(0)[0]));
			l.setCosto(Float.parseFloat(resL.get(i)[4]));
			l.setDisp(Integer.parseInt(resL.get(i)[5]));
			l.setIsbn(resL.get(i)[7]);
			l.setPagine(Integer.parseInt(resL.get(i)[8]));
			l.setAutori(autori);
			libri.set(i, l);
		}
		return libri;
		}
			
	public Vector<Libro> caricaPerId(Vector<Integer> ids){
		Vector<Libro> libri=new Vector<Libro>();
		Vector<String[]> resL;
		Vector<String[]> resA;
		libri.setSize(ids.size());
		
		for(int i=0; i<ids.size(); i++)
		{
			//livello singolo libro
			resL=DbConnection.getInstance().eseguiQuery("select * from libro where ID_libro='"+ids.get(i)+"'");
			
			resA = DbConnection.getInstance().eseguiQuery("SELECT autore_ID_autore FROM libro_has_autore WHERE libro_ID_libro = '"+ids.get(i)+"'");
			Vector<Autore> autori = new Vector<Autore>();
			autori.setSize(resA.size());
			for (int j=0; j<resA.size(); j++){
				
				//livello singolo autore
				
				Vector<String[]> temp=DbConnection.getInstance().eseguiQuery("select * from autore where ID_autore='"+Integer.parseInt(resA.get(j)[0])+"' ");
				autori.set(j, new Autore(Integer.parseInt(temp.get(0)[0]), temp.get(0)[1], temp.get(0)[2]));
				
			}
			Libro l=new Libro();
			l.setId(Integer.parseInt(resL.get(0)[0]));
			l.setTitolo(resL.get(0)[1]);
			l.setGenere(new Genere(Integer.parseInt(resL.get(0)[2]), DbConnection.getInstance().eseguiQuery("select nome_genere from genere where ID_genere='"+resL.get(0)[2]+"'").get(0)[0]));
			l.setCasaEd(new CasaEd(Integer.parseInt(resL.get(0)[3]), DbConnection.getInstance().eseguiQuery("select nome_casa_editrice from casa_editrice where ID_casa_editrice='"+resL.get(0)[3]+"'").get(0)[0]));
			l.setCosto(Float.parseFloat(resL.get(0)[4]));
			l.setDisp(Integer.parseInt(resL.get(0)[5]));
			l.setIsbn(resL.get(0)[7]);
			l.setPagine(Integer.parseInt(resL.get(0)[8]));
			l.setAutori(autori);
			libri.set(i, l);
			}
		
		return libri;
	}

	public Vector<Libro> ricerca(String titolo){
		Vector<Libro> libri=new Vector<Libro>();
		Vector<String[]> resL = DbConnection.getInstance().eseguiQuery("SELECT * FROM libro where titolo like '%"+titolo+"%'");
		Vector<String[]> resA;
		libri.setSize(resL.size());

		if (!resL.isEmpty()){

			for(int i=0; i<resL.size(); i++)
			{
				//livello singolo libro

				resA = DbConnection.getInstance().eseguiQuery("SELECT autore_ID_autore FROM libro_has_autore WHERE libro_ID_libro = '"+Integer.parseInt(resL.get(i)[0])+"'");
				Vector<Autore> autori = new Vector<Autore>();
				autori.setSize(resA.size());
				for (int j=0; j<resA.size(); j++){

					//livello singolo autore

					Vector<String[]> temp=DbConnection.getInstance().eseguiQuery("select * from autore where ID_autore='"+Integer.parseInt(resA.get(j)[0])+"' ");
					autori.set(j, new Autore(Integer.parseInt(temp.get(0)[0]), temp.get(0)[1], temp.get(0)[2]));

				}
				Libro l=new Libro();
				l.setId(Integer.parseInt(resL.get(i)[0]));
				l.setTitolo(resL.get(i)[1]);
				l.setGenere(new Genere(Integer.parseInt(resL.get(i)[2]), DbConnection.getInstance().eseguiQuery("select nome_genere from genere where ID_genere='"+resL.get(i)[2]+"'").get(0)[0]));
				l.setCasaEd(new CasaEd(Integer.parseInt(resL.get(i)[3]), DbConnection.getInstance().eseguiQuery("select nome_casa_editrice from casa_editrice where ID_casa_editrice='"+resL.get(i)[3]+"'").get(0)[0]));
				l.setCosto(Float.parseFloat(resL.get(i)[4]));
				l.setDisp(Integer.parseInt(resL.get(i)[5]));
				l.setIsbn(resL.get(i)[7]);
				l.setPagine(Integer.parseInt(resL.get(i)[8]));
				l.setAutori(autori);
				libri.set(i, l);
			}
		}
		return libri;
	}
	
	public void modificaTitolo(int id, String titolo) {
		DbConnection.getInstance().eseguiAggiornamento("UPDATE libro SET titolo = '"+titolo+"' WHERE ID_libro = '"+id+"'");
	}
	
	public void modificaCasa(int idLibro, int idCasa){
		DbConnection.getInstance().eseguiAggiornamento("UPDATE libro SET casa_editrice_ID_casa_editrice = '"+idCasa+"' WHERE ID_libro = '"+idLibro+"'");
	}
	
	public void modificaGenere(int idLibro, int idGenere){
		DbConnection.getInstance().eseguiAggiornamento("UPDATE libro SET genere_ID_genere = '"+idGenere+"' WHERE ID_libro = '"+idLibro+"'");
	}
	
	public void modificaCosto(int id, int costo){
		DbConnection.getInstance().eseguiAggiornamento("UPDATE libro SET costo = '"+costo+"' WHERE ID_libro = '"+id+"'");
	}
	
	public void modificaDisp(int disp, int id){
		DbConnection.getInstance().eseguiAggiornamento("UPDATE libro SET disponibilita = '"+disp+"' WHERE ID_libro = '"+id+"'");
	}
	
	public void FlagOrdineTrue(int id){
		DbConnection.getInstance().eseguiAggiornamento("UPDATE libro SET flag_ordine = '1' WHERE ID_libro = '"+id+"'");
	}
	
	public void modificaISBN(String ISBN, int id){
		DbConnection.getInstance().eseguiAggiornamento("UPDATE libro SET ISBN = '"+ISBN+"' WHERE ID_libro = '"+id+"'");
	}
	
	public void modificaPagine(int pagine, int id){
		DbConnection.getInstance().eseguiAggiornamento("UPDATE libro SET numero_pagine = '"+pagine+"' WHERE ID_libro = '"+id+"'");
	}
	
}
	
