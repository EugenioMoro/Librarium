package it.unisalento.Model;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Tessera {
	
	private String codice;
	private int punti;
	
	private SecureRandom random = new SecureRandom();//per generare nuovo codice random
	
	
	
	private String nuovoCodice(){  //per generare nuovo codice random
		return new BigInteger(130, random).toString(32).substring(0, 6); //genera un intero random, fa il cast in stringa e restituisce la substringa lunga 6
	}
	
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public int getPunti() {
		return punti;
	}
	public void setPunti(int punti) {
		this.punti = punti;
	}

	public Tessera (String codice, int punti){
		this.codice=codice;
		this.punti=punti;
	}
	
	public Tessera(){
		this.codice=nuovoCodice();
		this.punti=0;
	}

}

