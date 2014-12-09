package it.unisalento.Model;

public class Richiesta {
	
	private int ID_richiesta;
	private int ID_utente;
	private String data_richiesta;
	private String data_arrivo;
	private boolean flag_inoltro;
	private boolean flag_arrivo;

	


	public String getDataRichiesta() {
		return data_richiesta;
	}
	public void setDataRichiesta(String data_richiesta) {
		this.data_richiesta = data_richiesta;
	}
	public String getDataArrivo() {
		return data_arrivo;
	}
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
	public void setDataArrivo(String data_arrivo) {
		this.data_arrivo = data_arrivo;
	}
	public boolean getFlagArrivo() {
		return flag_arrivo;
	}
	public void setFlagArrivo(boolean flag_arrivo) {
		this.flag_arrivo = flag_arrivo;
	}
	public boolean getFlagInoltro() {
		return flag_inoltro;
	}
	public void setFlagInoltro(boolean flag_inoltro) {
		this.flag_inoltro = flag_inoltro;
	}
}
	