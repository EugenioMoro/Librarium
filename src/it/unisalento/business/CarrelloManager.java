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
		boolean esiste=false;
		for (int i=0; i<acquisto.getLibri().size(); i++){
			if (acquisto.getLibri().get(i).getId()==l.getId()){
				l.setQuantità(l.getQuantità()+1);
				esiste=true;
				break;
			}
		}
		if(!esiste) acquisto.getLibri().add(l);
		l.setDisp(l.getDisp()-1);
		aggiornaIncasso(true, l.getCosto());
	}
	
	public void rimuovi(int index){
		aggiornaIncasso(false, acquisto.getLibri().get(index).getCosto());
		incrementaQuantità(acquisto.getLibri().get(index));
		if (acquisto.getLibri().get(index).getQuantità()>1){
			acquisto.getLibri().get(index).setQuantità(acquisto.getLibri().get(index).getQuantità()-1);
		} else{
			acquisto.getLibri().removeElementAt(index);	
		}
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
	
	private void incrementaQuantità(Libro l){
		for (int i=0; i<Session.currentSession().getSearchResults().size(); i++){
			if(Session.currentSession().getSearchResults().get(i).getId()==l.getId()){
				Session.currentSession().getSearchResults().get(i).setDisp(Session.currentSession().getSearchResults().get(i).getDisp()+1);
				break;
			}
		}
	}

}
