package it.unisalento.Model;



public class Cliente extends Utente {
	
	private boolean sesso;
	private String data_nascita;
	private String email;
	private String telefono;
	
	
	
	public Cliente(String username, String password){
		super(username, password);
	}

	public Cliente(){
		
	}

	public boolean isSesso() {
		return sesso;
	}



	public void setSesso(boolean sesso) {
		this.sesso = sesso;
	}



	public String getData_nascita() {
		return data_nascita;
	}



	public void setData_nascita(String data_nascita) {
		this.data_nascita = data_nascita;
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
