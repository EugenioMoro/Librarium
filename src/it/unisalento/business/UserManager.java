package it.unisalento.business;

import it.unisalento.DataAccessObjects.LogInDAO;
import it.unisalento.Model.Utente;
import it.unisalento.view.MessageBoxes;

public class UserManager {

	public static boolean logIn(){ //una volta che questo metodo ritrona true la view può essere caricata con utente o cliente
		
		Utente u=Session.currentSession().getU();
		if(!LogInDAO.getInstance().checkCredenziali(u)) {
			MessageBoxes.alert("Attenzione", "Username o password errate");
			return false;
		}
		
		String tipo=LogInDAO.getInstance().checkTipo(u);
		Session.currentSession().setTipo(tipo);
		
		switch (tipo){
		case "Cliente":
			Session.currentSession().setC(LogInDAO.getInstance().caricaCliente(u));
			break;
		default: Session.currentSession().setU(LogInDAO.getInstance().caricaUtente(u));
		}
		
		
		return true;
	}
	
}
