package it.unisalento.business;

import it.unisalento.DataAccessObjects.LibroDAO;
import it.unisalento.Model.Autore;
import it.unisalento.Model.CasaEd;
import it.unisalento.Model.Genere;
import it.unisalento.Model.Libro;

import java.util.Vector;

public class SearchEngine {
	
	private static SearchEngine instance;
	
	
	public static SearchEngine getInstance(){
		if (instance==null) 
			instance=new SearchEngine();
		return instance;
	}
	
	
	//----------------------IMPORTANTE-------------------------------------------//
	//In una ricerca a cascata invocare SEMPRE prima di tutto ricerca per titolo
	//Prima di effettuare una nuova ricerca invocare SEMPRE Session.currentSession().SetSearchDefault()
	
	public boolean perTitolo(String titolo){
		
		Vector<Libro> results=LibroDAO.getInstance().ricerca(titolo);
		if (results.isEmpty()) return false;
		
		Session.currentSession().setSearchResults(results);
		return true;
	}

	public boolean perAutore(Autore a){ //Mi sto rincoglionendo spero che funzioni
		
		Vector<Libro> results=Session.currentSession().getSearchResults();
		
		for(int i=results.size()-1; i>=0; i--){
			Libro l=results.get(i);
			boolean ok=false;
			for (int j=0; j<l.getAutori().size(); j++){
				if (l.getAutori().get(j).getId()==a.getId()){
					ok=true;
					break;
				}	
			}
			
			if (!ok) results.removeElementAt(i);
		}
		
		
		if (results.isEmpty()) return false;
		
		Session.currentSession().setSearchResults(results);
		return true;
	}

	public boolean perGenere(Genere g){
		
		Vector<Libro> results=Session.currentSession().getSearchResults();
		
		for (int i=results.size()-1; i>=0; i--){
			if (results.get(i).getGenere().getId()!=g.getId()) results.removeElementAt(i);
		}
		if(results.isEmpty()) return false;
		
		Session.currentSession().setSearchResults(results);
		return true;
		
		
	}

	public boolean perCasa(CasaEd e){
		Vector<Libro> results=Session.currentSession().getSearchResults();
		
		for (int i=results.size()-1; i>=0; i--)
			if (results.get(i).getCasaEd().getId()!=e.getId()) results.removeElementAt(i);
		
		if (results.isEmpty()) return false;
		
		Session.currentSession().setSearchResults(results);
		return true;
		
	}
	
}
