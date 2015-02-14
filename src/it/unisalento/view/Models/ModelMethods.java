package it.unisalento.view.Models;

import it.unisalento.DataAccessObjects.LibroDAO;
import it.unisalento.DataAccessObjects.RichiestaDAO;
import it.unisalento.business.CarrelloManager;
import it.unisalento.business.ControlliCoerenza;
import it.unisalento.business.Session;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Panels.CarrelloJPanJTab;
import it.unisalento.view.Panels.LibriJPanJTab;

public class ModelMethods {
	
public static void OrdinaLibro(int row, int col){
		

		if(Session.currentSession().getC().getUsername()!=null){
			if (Session.currentSession().getSearchResults().get(row).getDisp()==0){
				RichiestaDAO.getInstance().ordinaLibro(Session.currentSession().getSearchResults().get(row), Session.currentSession().getC());
				MessageBoxes.alert("Richiesta d'ordine", "La richiesta per il libro "+Session.currentSession().getSearchResults().get(row).getTitolo()+" è stata inoltrata\nRiceverai una email quando il libro sarà nuovamente disponibile");
			} else {
				MessageBoxes.alert("Attenzione", "Il libro è disponibile in libreria");
			}
		}
		else{
			MessageBoxes.alert("Attenzione", "Devi prima fare il log in");
		}
	}


public static void aggiungiCarrello(int row){
	if (Session.currentSession().getSearchResults().get(row).getDisp()>0){
		CarrelloManager.getInstance().aggiungi(Session.currentSession().getSearchResults().get(row));
		LibriJPanJTab.refresh();
		CarrelloJPanJTab.refresh();
	} else MessageBoxes.alert("Attenzione", "Libro non disponibile");
}

public static boolean modificaTitolo(int id, String newT){
	if (ControlliCoerenza.checkTitolo(newT)) { 
		LibroDAO.getInstance().modificaTitolo(id, newT);
		return true;
	}
	return false;
}

public static void modificaGenere(int idLibro, int idGenere){
	LibroDAO.getInstance().modificaGenere(idLibro, idGenere);
}

public static void modificaCasa(int idLibro, int idCasa){
	LibroDAO.getInstance().modificaCasa(idLibro, idCasa);
}

public static boolean modificaCosto(int idLibro, String costo){
	if (ControlliCoerenza.checkCosto(costo)){
		LibroDAO.getInstance().modificaCosto(idLibro, Integer.parseInt(costo));
		return true;
	}
	return false;
}

public static boolean modificaDisp(int id, String disp){
	if (ControlliCoerenza.checkDisp(disp)){
		LibroDAO.getInstance().modificaDisp(Integer.parseInt(disp), id);
		return true;
	}
	return false;
}

public static boolean modificaISBN(int id, String isbn){
	if (ControlliCoerenza.checkISBN(isbn)){
		LibroDAO.getInstance().modificaISBN(isbn, id);
		return true;
	}
	return false;
}

public static boolean modificaPagine(int id, String p){
	if (ControlliCoerenza.checkPagine(p)){
		LibroDAO.getInstance().modificaPagine(Integer.parseInt(p), id);
		return true;
	}
	return false;
}


}
