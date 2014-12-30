package it.unisalento.business;

import it.unisalento.DataAccessObjects.AcquistoDAO;
import it.unisalento.Model.Acquisto;
import it.unisalento.Model.Libro;

public class CarrelloManager {
	
	private static CarrelloManager instance;
	
	private Acquisto acquisto;
	
	public static CarrelloManager getInstance(){
		if (instance==null){
			instance=new CarrelloManager();
			instance.acquisto= new Acquisto();
		}
		return instance;
	}
	
	
	public void aggiungi(Libro l){
		acquisto.getLibri().add(l);
		aggiornaIncasso(true, l.getCosto());
	}
	
	public void rimuovi(int index){
		aggiornaIncasso(false, acquisto.getLibri().get(index).getCosto());
		acquisto.getLibri().removeElementAt(index);
	}
	
	private void aggiornaIncasso(boolean incremento, float costo){

		if (incremento)
			acquisto.setIncasso(acquisto.getIncasso()+costo);
		else acquisto.setIncasso(acquisto.getIncasso()-costo);
	}
	
	public void svuota(){
		acquisto= new Acquisto();
	}
	
	public void vendi(int cliente_ID){
		acquisto.setCliente_id(cliente_ID);
		AcquistoDAO.getInstance().inserisci(acquisto);
		svuota();
	}


	public Acquisto getAcquisto() {
		return acquisto;
	}

}
