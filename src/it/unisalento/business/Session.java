package it.unisalento.business;

import it.unisalento.DataAccessObjects.Autore_DAO;
import it.unisalento.DataAccessObjects.CasaEd_DAO;
import it.unisalento.DataAccessObjects.Genere_DAO;
import it.unisalento.DataAccessObjects.LibroDAO;
import it.unisalento.Model.Autore;
import it.unisalento.Model.CasaEd;
import it.unisalento.Model.Cliente;
import it.unisalento.Model.Genere;
import it.unisalento.Model.Libro;
import it.unisalento.Model.Utente;
import it.unisalento.view.Dialogs.MessageBoxes;

import java.util.Vector;

public class Session {

	
	
	//variabili di sessione
	private static Session currentSession;
	private static Utente u;
	private static Cliente c;
	private static String tipo;
	private static Vector <Autore> autori;
	private static Vector <CasaEd> caseEd=CasaEd_DAO.getInstance().caricaCase();
	private static Vector <Genere> generi=Genere_DAO.getInstance().caricaGeneri();
	private static Vector <Libro> tuttiLibri=LibroDAO.getInstance().caricaTutti();
	private static Vector <Libro> searchResults=LibroDAO.getInstance().caricaTutti();
	public final static String AMMINISTRATIVECODE="0000";
	
	
	public static Session currentSession(){
		if (currentSession==null){
			u=new Utente();
			c=new Cliente();
			
			try{
			currentSession=new Session();
			autori=Autore_DAO.getInstance().caricaAutori();
			caseEd=CasaEd_DAO.getInstance().caricaCase();
			generi=Genere_DAO.getInstance().caricaGeneri();
			tuttiLibri=LibroDAO.getInstance().caricaTutti();
			searchResults=tuttiLibri;
			} catch(Exception e) {
				e.printStackTrace();
				MessageBoxes.errore("Errore", "Impossibile caricare dati da Database");
			}
		}
		return currentSession;
	}
	
	public void destroy(){
		currentSession=null;
	}

	public Utente getU() {
		return u;
	}

	public void setU(Utente u) {
		Session.u = u;
	}

	public Cliente getC() {
		return c;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		Session.tipo = tipo;
	}

	public void setC(Cliente c) {
		Session.c = c;
	}

	public Vector<Autore> getAutori() {
		return autori;
	}

	public void setAutori(Vector<Autore> autori) {
		Session.autori = autori;
	}

	public Vector<CasaEd> getCaseEd() {
		return caseEd;
	}

	public void setCaseEd(Vector<CasaEd> caseEd) {
		Session.caseEd = caseEd;
	}

	public Vector<Genere> getGeneri() {
		return generi;
	}

	public void setGeneri(Vector<Genere> generi) {
		Session.generi = generi;
	}

	public Vector<Libro> getTuttiLibri() {
		return tuttiLibri;
	}

	public void setTuttiLibri(Vector<Libro> libri) {
		Session.tuttiLibri = libri;
	}

	public Vector <Libro> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(Vector <Libro> searchResults) {
		Session.searchResults = searchResults;
	}
	
	public void resetSearchResults(){
		Session.searchResults=LibroDAO.getInstance().caricaTutti();
	}
	
	public void DestroyU()
	{
		Session.u=new Utente();
	}
	
	public void DestroyC(){
		Session.c=new Cliente();
	}
	
}
