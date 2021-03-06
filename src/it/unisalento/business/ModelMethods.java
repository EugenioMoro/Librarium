package it.unisalento.business;

import it.unisalento.DataAccessObjects.Autore_DAO;
import it.unisalento.DataAccessObjects.CasaEd_DAO;
import it.unisalento.DataAccessObjects.Genere_DAO;
import it.unisalento.DataAccessObjects.LibroDAO;
import it.unisalento.DataAccessObjects.RichiestaDAO;
import it.unisalento.Model.Autore;
import it.unisalento.Model.CasaEd;
import it.unisalento.Model.Genere;
import it.unisalento.Model.Libro;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Panels.CarrelloJPanJTab;
import it.unisalento.view.Panels.LibriJPanJTab;

public class ModelMethods {

	public static void OrdinaLibro(int row, int col){


		if(Session.currentSession().getC().getUsername()!=null){
			if (Session.currentSession().getSearchResults().get(row).getDisp()==0){
				RichiestaDAO.getInstance().ordinaLibro(Session.currentSession().getSearchResults().get(row), Session.currentSession().getC());
				MessageBoxes.alert("Richiesta d'ordine", "La richiesta per il libro "+Session.currentSession().getSearchResults().get(row).getTitolo()+" � stata inoltrata\nRiceverai una email quando il libro sar� nuovamente disponibile");
			} else {
				MessageBoxes.alert("Attenzione", "Il libro � disponibile in libreria");
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

	//i metodi per modificare i dati di un libro operano a livello database e livello oggetti 

	public static boolean modificaTitolo(Libro l, String newT){
		if (ControlliCoerenza.checkTitolo(newT, true)) { 
			LibroDAO.getInstance().modificaTitolo(l.getId(), newT);
			l.setTitolo(newT);
			return true;
		}
		return false;
	}

	public static void modificaGenere(Libro l, Genere g){
		LibroDAO.getInstance().modificaGenere(l.getId(), g.getId());
		l.setGenere(g);
	}

	public static void modificaCasa(Libro l, CasaEd c){
		LibroDAO.getInstance().modificaCasa(l.getId(), c.getId());
		l.setCasaEd(c);
	}

	public static boolean modificaCosto(Libro l, String costo){
		if (ControlliCoerenza.checkCosto(costo, true)){
			LibroDAO.getInstance().modificaCosto(l.getId(), Float.parseFloat(costo));
			l.setCosto(Float.parseFloat(costo));
			return true;
		}
		return false;
	}

	public static boolean modificaDisp(Libro l, String disp){
		if (ControlliCoerenza.checkDisp(disp, true)){
			LibroDAO.getInstance().modificaDisp(Integer.parseInt(disp), l.getId());
			l.setDisp(Integer.parseInt(disp));
			return true;
		}
		return false;
	}

	public static boolean modificaISBN(Libro l, String isbn){
		if (ControlliCoerenza.checkISBN(isbn, true)){
			LibroDAO.getInstance().modificaISBN(isbn, l.getId());
			l.setIsbn(isbn);;
			return true;
		}
		return false;
	}

	public static boolean modificaPagine(Libro l, String p){
		if (ControlliCoerenza.checkPagine(p, true)){
			LibroDAO.getInstance().modificaPagine(Integer.parseInt(p), l.getId());
			l.setPagine(Integer.parseInt(p));
			return true;
		}
		return false;
	}

	public static void aggiornaOrdine(Libro l){
		LibroDAO.getInstance().setOrdine(l.getId(), !l.isRichiesto());
		l.setRichiesto(!l.isRichiesto());
	}
	
	public static boolean eliminaAutore(Autore a){
		if (Autore_DAO.getInstance().elemina(a)){
			for(int i=0; i<Session.currentSession().getAutori().size(); i++){
				if (Session.currentSession().getAutori().get(i).getId()==a.getId()){
					Session.currentSession().getAutori().remove(i);
					break;
				}
			}
			return true;
		}
		return false;
	}
	
	public static boolean eliminaGenere(Genere g){
		if (Genere_DAO.getInstance().elimina(g)){
			for (int i=0; i<Session.currentSession().getGeneri().size(); i++){
				if(Session.currentSession().getGeneri().get(i).getId()==g.getId()){
					Session.currentSession().getGeneri().remove(i);
				}
			}
			return true;
		}
		return false;
	}
	
	public static boolean eliminaCasa(CasaEd c){
		if (CasaEd_DAO.getInstance().elimina(c)){
			for (int i=0; i<Session.currentSession().getCaseEd().size(); i++){
				if(Session.currentSession().getCaseEd().get(i).getId()==c.getId()){
					Session.currentSession().getCaseEd().remove(i);
				}
			}
			return true;
		}
		return false;
	}


}
