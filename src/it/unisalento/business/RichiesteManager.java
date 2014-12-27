package it.unisalento.business;

import it.unisalento.DataAccessObjects.RichiestaDAO;
import it.unisalento.Model.Richiesta;

import java.util.Vector;

public class RichiesteManager {
	
	private static RichiesteManager instance;
	
	public static RichiesteManager getInstance(){
		if (instance==null)
			instance=new RichiesteManager();
		return instance;
	}
	
	public void setStorico(){
		Session.currentSession().setRichieste(RichiestaDAO.getInstance().RichiesteStorico());
	}
	
	public void setStoricoCliente(){
		Vector<Richiesta> res= RichiestaDAO.getInstance().RichiesteStorico();
		
		for (int i=res.size()-1; i>=0; i--){
			if (res.get(i).getID_cliente()!=Session.currentSession().getC().getId()) res.removeElementAt(i);
		}
		Session.currentSession().setRichieste(res);
	}
	
	public void setInoltrate(){
		Session.currentSession().setRichieste(RichiestaDAO.getInstance().RichiesteInoltrate());
	}
	
}
