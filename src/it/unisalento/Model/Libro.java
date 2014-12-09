package it.unisalento.Model;


public class Libro {
	
	private int id;
	private boolean flag_ordine;
	private String titolo;
	private float costo;
	private String isbn;
	private int numPagine;
	private boolean flag_disponibilita;
	
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
	
	public int getNumpagine() {
		return numPagine;
	}
	public void setNumpagine(int numPagine) {
		this.numPagine = numPagine;
	}
	
	public boolean getFlagdisp() {
		return flag_disponibilita;
	}
	public void setFlagdisp(boolean flag_disponibilita) {
		this.flag_disponibilita = flag_disponibilita;
	}
	
	public boolean getFlag_ordine() {
		return flag_ordine;
	}
	public void setFlag_ordine(boolean flag_ordine) {
		this.flag_ordine = flag_ordine;
	}

}
