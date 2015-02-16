package it.unisalento.business;

import it.unisalento.DataAccessObjects.LibroDAO;
import it.unisalento.Model.Autore;
import it.unisalento.Model.Libro;

import java.util.Vector;


public class NuovoLibroManager {
	
	private static NuovoLibroManager instance;

	
	public static NuovoLibroManager getInstance(){
		if(instance==null)
			instance=new NuovoLibroManager();
		return instance;
	}
	
	
	public void salva(){
		if (LibroDAO.getInstance().inserisciLibro(Session.currentSession().getLibroTemp()))
			Session.currentSession().getTuttiLibri().add(Session.currentSession().getLibroTemp());
	}

	public void inizializza(){
		Session.currentSession().setLibroTemp(new Libro());
		Session.currentSession().getLibroTemp().setAutori(new Vector<Autore>());	
	}

	

}
