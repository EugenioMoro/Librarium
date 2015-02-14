package it.unisalento.view.Models;

import it.unisalento.business.Session;
import it.unisalento.view.Frames.VenditeView;

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
	private String[] columnNamesSCAFFALI={"Titolo", "Autori", "Casa Editrice", "Genere", "Costo", "Pagine", "ISBN", "Disponibilità"};




	public LibriTableModel(String option){
		this.option=option;
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
		if (option.equals(NEUTRALOPT)||option.equalsIgnoreCase(SCAFFALIOPT)) return 8;
		return 9;
	}

	@Override
	public int getRowCount() {
		return Session.currentSession().getSearchResults().size();
	}

	@Override
	public Object getValueAt(int row, int col) {
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

	private static Action ordina = new AbstractAction() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {

			
			//JTable table = (JTable) e.getSource();
			int row = Integer.valueOf(e.getActionCommand());
			//LibriTableModel model = (LibriTableModel) table.getModel();
			ModelMethods.OrdinaLibro(row, 0);
		}
	};

	private static Action carrello = new AbstractAction() { //definisco l'azione da intraprendere al premere del bottone

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {


			//JTable table = (JTable) e.getSource();
			int row = Integer.valueOf(e.getActionCommand());
			//LibriTableModel model = (LibriTableModel) table.getModel();
			ModelMethods.aggiungiCarrello(row);
			VenditeView.aggiornaLabels();
		}
	};

	private static Action titolo = new AbstractAction() { 


		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {


			JTable table = (JTable) e.getSource();
			int row = Integer.valueOf(e.getActionCommand()); 
			//LibriTableModel model = (LibriTableModel) table.getModel(); 
			//ModelMethods.modificaTitolo(row); 
			VenditeView.aggiornaLabels();
		}
	};

	public static Action getOrdinaAction(){
		return ordina;
	}

	public static Action getCarrelloAction(){
		return carrello;
	}

	public static Action getTitoloAction(){
		return titolo;
	}
}
