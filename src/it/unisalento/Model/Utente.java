package it.unisalento.Model;

public class Utente {
	protected String nome;
	protected String cognome;
	private String username;
	private String password;
	protected String data_ultimo_accesso;
	
	public String getData_ultimo_accesso() {
		return data_ultimo_accesso;
	}

	public void setData_ultimo_accesso(String data_ultimo_accesso) {
		this.data_ultimo_accesso = data_ultimo_accesso;
	}

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
