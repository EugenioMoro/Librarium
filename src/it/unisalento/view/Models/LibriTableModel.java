package it.unisalento.view.Models;

import java.awt.event.ActionEvent;
import java.util.Vector;

import it.unisalento.Model.Libro;
import it.unisalento.business.Session;
import it.unisalento.view.Dialogs.MessageBoxes;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class LibriTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Libro> libri=Session.currentSession().getSearchResults();
	private String[] columnNames={"Titolo", "Autori", "Casa Editrice", "Genere", "Costo", "Pagine", "ISBN", "Disponibilità", "Ordina"};
	
	public void OrdinaLibro(int row, int col){
		//TODO completare con metodi e controlli per ordinare un libro, per ora contiene solo un alert di test
		//Ricorda: sarà qualcosa del tipo RichiestaDAO.ordinaLibro(libri.get(row), Session.currentSession().getCliente())
		
		if(Session.currentSession().getC().getUsername()!=null){
			//TODO implementare metodi per cliente loggato
		}
		else{
			MessageBoxes.alert("Attenzione", "Devi prima fare il log in");
		}
		MessageBoxes.alert("Bona", "Funziona, sei grande");
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
		 * Colonne in ordine: [0]Titolo-[1]Autori-[2]Casa Editrice-[3]Genere-[4]Costo-[5]Pagine-[6]ISBN-[7]Disponibilità-[8]OrdinaButton*/
		return 9;
	}

	@Override
	public int getRowCount() {
		return libri.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		switch (col){
		case 0: return libri.get(row).getTitolo();
		case 1: return libri.get(row).autoriToString();
		case 2: return libri.get(row).getCasaEd().getNome();
		case 3: return libri.get(row).getGenere().getNome();
		case 4: return libri.get(row).getCosto();
		case 5: return libri.get(row).getPagine();
		case 6: return libri.get(row).getIsbn();
		case 7: return libri.get(row).getDisp();
		case 8: return "Ordina";
		
		}
		return null;
	}
	
	@Override
	public String getColumnName(int col) {
	    return columnNames[col];
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
	
	public static Action getAction(){
		return ordina;
	}
	
	
}
