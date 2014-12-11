package it.unisalento.Model;

import java.sql.Date;
import java.util.Vector;

public class Acquisto {
	
	private int id;
	private Date data;
	private float incasso;
	private Vector<Libro> libri;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public float getIncasso() {
		return incasso;
	}
	public void setIncasso(float incasso) {
		this.incasso = incasso;
	}
	
	public Vector<Libro> getLibri() {
		return libri;
	}
	public void setLibri(Vector<Libro> libri) {
		this.libri = libri;
	}
	//costruttore per caricare da db
	public Acquisto(int id, Date data, float incasso, Vector<Libro> libri) {
		this.id = id;
		this.data = data;
		this.incasso = incasso;
		this.libri = libri;
	}
	
	//costruttore per nuovo da interfaccia
	public Acquisto(float incasso, Vector<Libro> libri) {
		this.incasso = incasso;
		this.libri = libri;
	}
	
	

	
	
	
	
}
