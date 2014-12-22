package it.unisalento.Model;





public class Cliente extends Utente {
	
	private boolean sesso;
	private java.util.Date data_nascita;
	private String email;
	private String telefono;
	
	
	
	public Cliente(int id, String username, String password, String nome, String cognome,  String data_ultimo_accesso){
		super(username, password);
		this.nome=nome;
		this.cognome=cognome;
		this.data_ultimo_accesso=data_ultimo_accesso;
		this.id=id;
		}

	public Cliente(){
		
	}

	public boolean isSesso() {
		return sesso;
	}



	public void setSesso(boolean sesso) {
		this.sesso = sesso;
	}



	public java.util.Date getData_nascita() {
		return data_nascita;
	}



	public void setData_nascita(java.util.Date date) {
		this.data_nascita = date;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	

}
