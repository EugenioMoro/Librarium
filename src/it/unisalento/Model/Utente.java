package it.unisalento.Model;

public class Utente {
	private String nome;
	private String cognome;
	private String username;
	private String password;
	
	public Utente(String username, String password){
		this.username=username;
		this.password=password;
	}
	
	public Utente(){
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
