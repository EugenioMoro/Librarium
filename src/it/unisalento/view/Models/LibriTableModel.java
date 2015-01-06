package it.unisalento.view.Models;

import it.unisalento.DataAccessObjects.RichiestaDAO;
import it.unisalento.business.CarrelloManager;
import it.unisalento.business.Session;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Frames.VenditeView;
import it.unisalento.view.Panels.CarrelloJPanJTab;
import it.unisalento.view.Panels.LibriJPanJTab;

import java.awt.event.ActionEvent;






import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class LibriTableModel extends AbstractTableModel {
	
	public final static String NEUTRALOPT="neutral";
	public final static String CLIENTEOPT="cliente";
	public final static String VENDITEOPT="vendite";
	public final static String SCAFFALIOPT="scaffali";
	private String option;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNamesCLIENTE={"Titolo", "Autori", "Casa Editrice", "Genere", "Costo", "Pagine", "ISBN", "Disponibilità", "Ordina"};
	private String[] columnNamesNEUTRAL={"Titolo", "Autori", "Casa Editrice", "Genere", "Costo", "Pagine", "ISBN", "Disponibilità"};
	private String[] columnNamesVENDITE={"Titolo", "Autori", "Casa Editrice", "Genere", "Costo", "Pagine", "ISBN", "Disponibilità", "Carrello"};
	private String[] columnNamesSCAFFALI={"Titolo", "Autori", "Casa Editrice", "Genere", "Costo", "Pagine", "ISBN", "Disponibilità", "Modifica"};



	
	public LibriTableModel(String option){
		this.option=option;
	}
	
	public void OrdinaLibro(int row, int col){
		

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

	
	@Override
    public boolean isCellEditable(int row, int col) {
        if (col==8) return true;
        return false;
    }
	
	
	@Override
	    public Class<?> getColumnClass(int col) {  
	     if (col==7 || col==5) return Integer.class; 
	     if (col==4) return Float.class;
		 
		 if (col != 8) {
	            return String.class;
	        } else {
	            return ButtonColumn.class;
	        }
	    }
	
	
	@Override
	public int getColumnCount() {
		
		/*
		 * Colonne in ordine: [0]Titolo-[1]Autori-[2]Casa Editrice-[3]Genere-[4]Costo-[5]Pagine-[6]ISBN-[7]Disponibilità-[8]SpecificButton*/
		if (option.equals(NEUTRALOPT)) return 8;
		return 9;
	}

	@Override
	public int getRowCount() {
		return Session.currentSession().getSearchResults().size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		switch (col){
		case 0: return Session.currentSession().getSearchResults().get(row).getTitolo();
		case 1: return Session.currentSession().getSearchResults().get(row).autoriToString();
		case 2: return Session.currentSession().getSearchResults().get(row).getCasaEd().getNome();
		case 3: return Session.currentSession().getSearchResults().get(row).getGenere().getNome();
		case 4: return Session.currentSession().getSearchResults().get(row).getCosto();
		case 5: return Session.currentSession().getSearchResults().get(row).getPagine();
		case 6: return Session.currentSession().getSearchResults().get(row).getIsbn();
		case 7: return Session.currentSession().getSearchResults().get(row).getDisp();
		case 8: {
			switch (option){
			case LibriTableModel.CLIENTEOPT: return "Ordina";
		    case LibriTableModel.SCAFFALIOPT: return "Modifica";
		    case LibriTableModel.VENDITEOPT: return "Carrello";
			}
		}
		
		}
		return null;
	}
	
	@Override
	public String getColumnName(int col) {
	    switch (option){
	    case LibriTableModel.CLIENTEOPT: return columnNamesCLIENTE[col];
	    case LibriTableModel.NEUTRALOPT: return columnNamesNEUTRAL[col];
	    case LibriTableModel.SCAFFALIOPT: return columnNamesSCAFFALI[col];
	    case LibriTableModel.VENDITEOPT: return columnNamesVENDITE[col];
	    default: return null;
	    }
	}

	private static Action ordina = new AbstractAction() { //definisco l'azione da intraprendere al premere del bottone
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//i commenti da ora in poi sono interpretazioni mie
			JTable table = (JTable) e.getSource(); //collega la tabella che ha generato l'evento in modo da poterci lavorare in questo metodo
			int row = Integer.valueOf(e.getActionCommand()); //getActionCommand dipende dal component della view generante, suppongo che con una jTable restituisca in string la row dove è stato generato l'evento
			LibriTableModel model = (LibriTableModel) table.getModel(); //Ok, questo è meno chiaro di tutti, non capisco perchè non usare il costruttore del model anzichè fare il cast dal getModel della tabella, per poter riutilizzare il codice?
			model.OrdinaLibro(row, 0); //Qui è chiaro, invoca il metodo definito nella model
		}
	};
	
private static Action modificaLibro = new AbstractAction() { //definisco l'azione da intraprendere al premere del bottone
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//i commenti da ora in poi sono interpretazioni mie
			JTable table = (JTable) e.getSource(); //collega la tabella che ha generato l'evento in modo da poterci lavorare in questo metodo
			int row = Integer.valueOf(e.getActionCommand()); //getActionCommand dipende dal component della view generante, suppongo che con una jTable restituisca in string la row dove è stato generato l'evento
			LibriTableModel model = (LibriTableModel) table.getModel(); //Ok, questo è meno chiaro di tutti, non capisco perchè non usare il costruttore del model anzichè fare il cast dal getModel della tabella, per poter riutilizzare il codice?
			model.modificaLibro(row); //Qui è chiaro, invoca il metodo definito nella model
		}
	};
	
private static Action carrello = new AbstractAction() { //definisco l'azione da intraprendere al premere del bottone
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//i commenti da ora in poi sono interpretazioni mie
			JTable table = (JTable) e.getSource(); //collega la tabella che ha generato l'evento in modo da poterci lavorare in questo metodo
			int row = Integer.valueOf(e.getActionCommand()); //getActionCommand dipende dal component della view generante, suppongo che con una jTable restituisca in string la row dove è stato generato l'evento
			LibriTableModel model = (LibriTableModel) table.getModel(); //Ok, questo è meno chiaro di tutti, non capisco perchè non usare il costruttore del model anzichè fare il cast dal getModel della tabella, per poter riutilizzare il codice?
			model.aggiungiCarrello(row); //Qui è chiaro, invoca il metodo definito nella model
			VenditeView.aggiornaLabels();
		}
	};
	
	
	public void modificaLibro(int row){
		MessageBoxes.alert("TODO", "Da implementare");
	}

	public void aggiungiCarrello(int row){
		if (Session.currentSession().getSearchResults().get(row).getDisp()>0){
			CarrelloManager.getInstance().aggiungi(Session.currentSession().getSearchResults().get(row));
			LibriJPanJTab.refresh();
			CarrelloJPanJTab.refresh();
		} else MessageBoxes.alert("Attenzione", "Libro non disponibile");
	}
	
	public static Action getOrdinaAction(){
		return ordina;
	}
	
	public static Action getModificaAction(){
		return modificaLibro;
	}
	
	public static Action getCarrelloAction(){
		return carrello;
	}
	
}
