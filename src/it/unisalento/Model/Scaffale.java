package it.unisalento.Model;

import java.util.Vector;

import it.unisalento.Model.Libro;


public class Scaffale {
	

	private Vector<Libro> libri;

	public Vector<Libro> getScaffale() {
		return libri;
	}

	public void setScaffale(Vector<Libro> libri) {
		this.libri = libri;
	}

	public Scaffale (Vector<Libro> libri) {
		this.libri = libri;
	}
	
	
	
}
