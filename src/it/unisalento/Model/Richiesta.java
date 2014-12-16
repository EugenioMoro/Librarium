package it.unisalento.Model;

import java.util.Date;

public class Richiesta {
	
	
	private int ID_richiesta;
	private int ID_utente;
	private int ID_libro;
	private Date data_richiesta;
	private Date data_arrivo;
	private boolean flag_inoltro;
	private boolean flag_arrivo;
	public int getID_richiesta() {
		return ID_richiesta;
	}
	public void setID_richiesta(int iD_richiesta) {
		ID_richiesta = iD_richiesta;
	}
	public int getID_utente() {
		return ID_utente;
	}
	public void setID_utente(int iD_utente) {
		ID_utente = iD_utente;
	}
	public int getID_libro() {
		return ID_libro;
	}
	public void setID_libro(int iD_libro) {
		ID_libro = iD_libro;
	}
	public Date getData_richiesta() {
		return data_richiesta;
	}
	public void setData_richiesta(Date data_richiesta) {
		this.data_richiesta = data_richiesta;
	}
	public Date getData_arrivo() {
		return data_arrivo;
	}
	public void setData_arrivo(Date data_arrivo) {
		this.data_arrivo = data_arrivo;
	}
	public boolean isFlag_inoltro() {
		return flag_inoltro;
	}
	public void setFlag_inoltro(boolean flag_inoltro) {
		this.flag_inoltro = flag_inoltro;
	}
	public boolean isFlag_arrivo() {
		return flag_arrivo;
	}
	public void setFlag_arrivo(boolean flag_arrivo) {
		this.flag_arrivo = flag_arrivo;
	}
	
//	Constructor for all requests
	public Richiesta(int iD_richiesta, int iD_utente, int iD_libro,
			Date data_richiesta, Date data_arrivo, boolean flag_inoltro,
			boolean flag_arrivo) {
		super();
		ID_richiesta = iD_richiesta;
		ID_utente = iD_utente;
		ID_libro = iD_libro;
		this.data_richiesta = data_richiesta;
		this.data_arrivo = data_arrivo;
		this.flag_inoltro = flag_inoltro;
		this.flag_arrivo = flag_arrivo;
	}
// 	Constructor for non allowed requests
	public Richiesta(int iD_richiesta, int iD_utente, int iD_libro,
			Date data_richiesta) {
		ID_richiesta = iD_richiesta;
		ID_utente = iD_utente;
		ID_libro = iD_libro;
		this.data_richiesta = data_richiesta;
	}
//	Constructor for allowed but not arrived requests
	public Richiesta(int iD_richiesta, int iD_utente, int iD_libro,
			Date data_richiesta, Date data_arrivo) {
		ID_richiesta = iD_richiesta;
		ID_utente = iD_utente;
		ID_libro = iD_libro;
		this.data_richiesta = data_richiesta;
		this.data_arrivo = data_arrivo;
	}
	

}
	