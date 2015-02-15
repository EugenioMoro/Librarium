package it.unisalento.business;

import it.unisalento.DataAccessObjects.AcquistoDAO;
import it.unisalento.DataAccessObjects.Autore_DAO;
import it.unisalento.DataAccessObjects.CasaEd_DAO;
import it.unisalento.DataAccessObjects.Genere_DAO;
import it.unisalento.DataAccessObjects.LibroDAO;
import it.unisalento.DataAccessObjects.RichiestaDAO;
import it.unisalento.Model.Acquisto;
import it.unisalento.Model.Autore;
import it.unisalento.Model.CasaEd;
import it.unisalento.Model.Cliente;
import it.unisalento.Model.Genere;
import it.unisalento.Model.Libro;
import it.unisalento.Model.Richiesta;
import it.unisalento.Model.Utente;
import it.unisalento.view.Dialogs.MessageBoxes;

import java.util.Vector;

public class Session {

	
	
	//variabili di sessione
	private static Session currentSession;
	private Utente u;
	private Cliente c;
	private String tipo;
	private Vector <Autore> autori;
	private Vector <CasaEd> caseEd=CasaEd_DAO.getInstance().caricaCase();
	private Vector <Genere> generi=Genere_DAO.getInstance().caricaGeneri();
	private Vector <Libro> tuttiLibri=LibroDAO.getInstance().caricaTutti();
	private Vector <Libro> searchResults=LibroDAO.getInstance().caricaTutti();
	private Vector <Richiesta> richieste=RichiestaDAO.getInstance().RichiesteStorico();
	private Vector <Acquisto> acquisti;
	private Libro libroTemp;
	public final static String AMMINISTRATIVECODE="0000";
	
	
	public static Session currentSession(){
		if (currentSession==null){
			currentSession=new Session();
			}
		return currentSession;
	}
	
	public Session(){
		u=new Utente();
		c=new Cliente();
		
		try{
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
	
	public void destroy(){
		currentSession=null;
	}

	public Utente getU() {
		return u;
	}

	public void setU(Utente u) {
		this.u = u;
	}

	public Cliente getC() {
		return c;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setC(Cliente c) {
		this.c = c;
	}

	public Vector<Autore> getAutori() {
		return autori;
	}

	public void setAutori(Vector<Autore> autori) {
		this.autori = autori;
	}

	public Vector<CasaEd> getCaseEd() {
		return caseEd;
	}

	public void setCaseEd(Vector<CasaEd> caseEd) {
		this.caseEd = caseEd;
	}

	public Vector<Genere> getGeneri() {
		return generi;
	}

	public void setGeneri(Vector<Genere> generi) {
		this.generi = generi;
	}

	public Vector<Libro> getTuttiLibri() {
		return tuttiLibri;
	}

	public void setTuttiLibri(Vector<Libro> libri) {
		this.tuttiLibri = libri;
	}

	public Vector <Libro> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(Vector <Libro> searchResults) {
		this.searchResults = searchResults;
	}
	
	public void resetSearchResults(){
		this.searchResults=LibroDAO.getInstance().caricaTutti();
	}
	
	public void DestroyU()
	{
		this.u=new Utente();
	}
	
	public void DestroyC(){
		this.c=new Cliente();
	}

	public Vector<Richiesta> getRichieste() {
		return richieste;
	}

	public void setRichieste(Vector<Richiesta> richieste) {
		this.richieste = richieste;
	}
	
	public void aggiornaRichieste(){
		this.richieste=RichiestaDAO.getInstance().RichiesteStorico();
	}

	public Vector<Acquisto> getAcquisti() {
		return acquisti;
	}

	public void setAcquisti(Vector<Acquisto> acquisti) {
		this.acquisti = acquisti;
	}
	
	public void aggiornaAcquisti(){
		acquisti=AcquistoDAO.getInstance().caricaStorico();
	}

	public Libro getLibroTemp() {
		return libroTemp;
	}

	public void setLibroTemp(Libro libroTemp) {
		this.libroTemp = libroTemp;
	}


	
}
