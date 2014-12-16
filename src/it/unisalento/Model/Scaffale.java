package it.unisalento.Model;


import java.util.ArrayList;
import java.util.Vector;

import it.unisalento.Model.Genere;
import it.unisalento.Model.Libro;


public class Scaffale {
	

	private ArrayList<Libro> libri;

	public ArrayList<Libro> getScaffale() {
		return libri;
	}

	public void setScaffale(ArrayList<Libro> libri) {
		this.libri = libri;
	}

	public Scaffale (ArrayList<Libro> libri) {
		this.libri = libri;
	}
	
	
	
}
