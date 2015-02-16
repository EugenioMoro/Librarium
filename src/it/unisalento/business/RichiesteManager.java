package it.unisalento.business;

import it.unisalento.DataAccessObjects.RichiestaDAO;
import it.unisalento.Model.Richiesta;

import java.util.Vector;

public class RichiesteManager {
	
	private static RichiesteManager instance;
	private int NuoveRichesteCount;
	
	public static RichiesteManager getInstance(){
		if (instance==null)
			instance=new RichiesteManager();
		return instance;
	}
	
	public void setStorico(){
		Session.currentSession().setRichieste(RichiestaDAO.getInstance().RichiesteStorico());
		NuoveRichesteCount=nuoveRichiesteCount();
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
	
	private int nuoveRichiesteCount(){
		int count=0;
		for (int i=0; i<Session.currentSession().getRichieste().size(); i++){
			if(Session.currentSession().getRichieste().get(i).getData_richiesta().after(Session.currentSession().getU().getData_ultimo_accesso()) ||
					Session.currentSession().getRichieste().get(i).getData_richiesta().equals(Session.currentSession().getU().getData_ultimo_accesso())){
				count++;
			}
		}
		return count;
	}
	
	public void setCountTo0(){
		NuoveRichesteCount=0;
	}

	public int getNuoveRichesteCount() {
		return NuoveRichesteCount;
	}

	
}
