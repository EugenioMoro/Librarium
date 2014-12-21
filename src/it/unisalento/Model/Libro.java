package it.unisalento.Model;

import java.util.Vector;




public class Libro {
	
	private int id;
	private boolean richiesto;
	private String titolo;
	private float costo;
	private String isbn;
	private int Pagine;
	private int disp;



	private Genere genere;
	private Vector<Autore> autori;
	private CasaEd casaEd;
	
	

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Vector<Autore> getAutori() {
		return autori;
	}
	public void setAutori(Vector<Autore> autori) {
		this.autori = autori;
	}
	
	public CasaEd getCasaEd() {
		return casaEd;
	}
	public void setCasaEd(CasaEd casaEd) {
		this.casaEd = casaEd;
	}
	
	public Genere getGenere() {
		return genere;
	}
	public void setGenere(Genere genere) {
		this.genere = genere;
	}
	
	public int getPagine() {
		return Pagine;
	}
	public void setPagine(int Pagine) {
		this.Pagine = Pagine;
	}
	
	public int getDisp() {
		return disp;
	}
	public void setDisp(int disp) {
		this.disp = disp;
	}
	
	public boolean isRichiesto() {
		return richiesto;
	}
	public void setRichiesto(boolean richiesto) {
		this.richiesto = richiesto;
	}
	
	public String autoriToString(){
		if(autori.size()==1) return autori.get(0).getCognome()+", "+autori.get(0).getNome().substring(0, 1)+".";
		
		String ret;
		ret=autori.get(0).getCognome()+", "+autori.get(0).getNome().substring(0, 1);
		for (int i=1; i<autori.size(); i++)
			ret=ret+". - "+autori.get(i).getCognome()+", "+autori.get(i).getNome().substring(0, 1)+".";
		
		return ret;
			
	}
	
	
	//costruttore con id da usare per caricare da database
	public Libro(int id, boolean richiesto, String titolo, float costo,
			String isbn, int Pagine, int disp, Genere genere,
			Vector<Autore> autori, CasaEd casaEd) {
		super();
		this.id = id;
		this.richiesto = richiesto;
		this.titolo = titolo;
		this.costo = costo;
		this.isbn = isbn;
		this.Pagine = Pagine;
		this.disp = disp;
		this.genere = genere;
		this.autori = autori;
		this.casaEd = casaEd;
	}
	
	
	//costruttore senza id da usare da interfaccia
	public Libro(boolean richiesto, String titolo, float costo,
			String isbn, int Pagine, int disp, Genere genere,
			Vector<Autore> autori, CasaEd casaEd) {
		
		
		this.richiesto = richiesto;
		this.titolo = titolo;
		this.costo = costo;
		this.isbn = isbn;
		this.Pagine = Pagine;
		this.disp = disp;
		this.genere = genere;
		this.autori = autori;
		this.casaEd = casaEd;
	}
	public Libro() {
		
	}

}
