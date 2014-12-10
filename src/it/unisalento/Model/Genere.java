package it.unisalento.Model;

public class Genere {

	private int id;
	private String nome;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//da usare per caricare da db
	public Genere(int id, String nome){
		this.id=id;
		this.nome=nome;
	}
	
	
	//da usare per definire nuovo da interfaccia
	public Genere(String nome) {
		super();
		this.nome = nome;
	}
	
}
