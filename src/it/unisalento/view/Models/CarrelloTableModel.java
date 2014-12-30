package it.unisalento.view.Models;

import java.awt.event.ActionEvent;

import it.unisalento.business.CarrelloManager;
import it.unisalento.view.Frames.VenditeView;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class CarrelloTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getColumnCount() {
		return 9;
	}

	@Override
	public int getRowCount() {
		return CarrelloManager.getInstance().getAcquisto().getLibri().size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col){
		case 0: return CarrelloManager.getInstance().getAcquisto().getLibri().get(row).getTitolo();
		case 1: return CarrelloManager.getInstance().getAcquisto().getLibri().get(row).autoriToString();
		case 2: return CarrelloManager.getInstance().getAcquisto().getLibri().get(row).getCasaEd().getNome();
		case 3: return CarrelloManager.getInstance().getAcquisto().getLibri().get(row).getGenere().getNome();
		case 4: return CarrelloManager.getInstance().getAcquisto().getLibri().get(row).getCosto();
		case 5: return CarrelloManager.getInstance().getAcquisto().getLibri().get(row).getPagine();
		case 6: return CarrelloManager.getInstance().getAcquisto().getLibri().get(row).getIsbn();
		case 7: return CarrelloManager.getInstance().getAcquisto().getLibri().get(row).getDisp();
		case 8: return "Rimuovi";
		}
		return null;
	}
	
	@Override
	public String getColumnName(int col) {
		String[] columnNames={"Titolo", "Autori", "Casa Editrice", "Genere", "Costo", "Pagine", "ISBN", "Disponibilità", "Rimuovi"};
		return columnNames[col];
	}
	
	@Override
    public boolean isCellEditable(int row, int col) {
        if (col==8) return true;
        return false;
    }
	
	@Override
    public Class<?> getColumnClass(int col) {  
		if (col==8) return ButtonColumn.class;
		return getValueAt(0, col).getClass();
    }
	
	private static Action rimuoviAction = new AbstractAction() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			JTable table = (JTable) e.getSource(); 
			int row = Integer.valueOf(e.getActionCommand()); 
			CarrelloTableModel model = (CarrelloTableModel) table.getModel();
			rimuovi(row);
			model.fireTableDataChanged();
			VenditeView.aggiornaLabels();
		}
	};

	public static void rimuovi(int row){
		CarrelloManager.getInstance().rimuovi(row);
	}
	
	public static Action getRimuoviAction(){
		return rimuoviAction;
	}
	
}
