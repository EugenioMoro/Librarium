package it.unisalento.Model;

import java.sql.Date;
import java.util.Vector;

public class Acquisto {
	
	private int id;
	private int cliente_id;
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
	public int getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}
	//costruttore per caricare da db
	public Acquisto(int id, Date data, float incasso, Vector<Libro> libri, int cliente_id) {
		this.id = id;
		this.data = data;
		this.incasso = incasso;
		this.libri = new Vector<Libro>();
		this.cliente_id=cliente_id;
	}
	
	//costruttore per nuovo da interfaccia
	public Acquisto(float incasso, Vector<Libro> libri, int cliente_id) {
		this.incasso = incasso;
		this.libri = new Vector<Libro>();
		this.cliente_id=cliente_id;
	}
	

	public Acquisto(){
		this.libri=new Vector<Libro>();
	}
	
	

	
	
	
	
}
