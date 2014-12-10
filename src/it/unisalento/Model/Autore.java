package it.unisalento.Model;

public class Autore {

	private int id;
	private String  nome;
	private String  cognome;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome=nome;
	}
	public String getNome() {
		return nome;
	}
	
	public void setCognome(String cognome) {
		this.cognome=cognome;
	}
	public String getCognome() {
		return cognome;
	}
	
	//costruttore da usare in interfaccia
	public Autore(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}
	
	//costruttore da usare per caricare autore da database
	public Autore(int id, String nome, String cognome) {
		
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
	}
	
	
	
}
