package it.unisalento.view.Models;

import it.unisalento.DataAccessObjects.Autore_DAO;
import it.unisalento.business.Session;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Frames.AutoriInLibroFrame;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class AutoriTableModel extends AbstractTableModel {
	
	public final static String GESTIONEOPT = "gestione";
	public final static String INLIBROOPT = "inlibro";
	public final static String AGGIUNGIOPT= "aggiungi";
	
	private String option;
	private boolean nuovoLibro;
	private String columnNames[]={"Nome", "Cognome"};

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AutoriTableModel(String option, boolean nuovoLibro) {
		this.option=option;
		this.nuovoLibro=nuovoLibro;
	}
	
	@Override
	public int getColumnCount() {
		if (option.equals(GESTIONEOPT)) return 4;
		return 3;
	}

	@Override
	public int getRowCount() {
		try{
			if (option.equals(INLIBROOPT)) return Session.currentSession().getLibroTemp().getAutori().size();
		} catch (Exception e) {
			return 0;
		}
		return Session.currentSession().getAutori().size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col){
		
		case 0: {
			if (option.equals(INLIBROOPT)) return Session.currentSession().getLibroTemp().getAutori().get(row).getNome();
			else return Session.currentSession().getAutori().get(row).getNome();
		}
		
		case 1: {
			if (option.equals(INLIBROOPT)) return Session.currentSession().getLibroTemp().getAutori().get(row).getCognome();
			else return Session.currentSession().getAutori().get(row).getCognome();
		}
		
		case 2: {
			switch (option){
			case GESTIONEOPT: return "Modifica";
			case INLIBROOPT: return "Rimuovi";
			case AGGIUNGIOPT: return "Aggiungi";
			}
		}
		
		case 3: return "Elimina";
		}
		return null;
	}
	
	@Override
	public String getColumnName(int col) {
		if (col<2) return columnNames[col];
		switch (option){
		case GESTIONEOPT: return "Modifica";
		case INLIBROOPT: return "Rimuovi";
		case AGGIUNGIOPT: return "Aggiungi";
		}
		return "";
	}
	
	@Override
    public boolean isCellEditable(int row, int col) {
        if (col>1) return true;
        return false;
	}
	
	@Override
    public Class<?> getColumnClass(int col) {  
		if (col<2) return getValueAt(0, col).getClass();
		return ButtonColumn.class;
    }
	
	private void aggiungiAutore(int index){

		boolean exists=false;

		
			for (int i=0; i<Session.currentSession().getLibroTemp().getAutori().size(); i++){
				if (Session.currentSession().getAutori().get(index).getId()==Session.currentSession().getLibroTemp().getAutori().get(i).getId()) {
					exists=true;
					break;
				}
			}

		if (!exists){
			Session.currentSession().getLibroTemp().getAutori().add(Session.currentSession().getAutori().get(index));
			if (!nuovoLibro)
				Autore_DAO.getInstance().aggiungiALibro(Session.currentSession().getAutori().get(index).getId(), Session.currentSession().getLibroTemp().getId());
		}
		else MessageBoxes.alert("Attenzione", "Autore già in elenco");
	}

	private void rimuoviAutore(int index){
		if(Session.currentSession().getLibroTemp().getAutori().size()>1){
			Session.currentSession().getLibroTemp().getAutori().remove(index);
			if (!nuovoLibro){
				Autore_DAO.getInstance().rimuoviDaLibro(Session.currentSession().getLibroTemp().getId(), Session.currentSession().getLibroTemp().getAutori().get(index).getId());
			}
		}
		else MessageBoxes.alert("Attenzione", "Impossibile rimuovere autore");
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
			AutoriTableModel model = (AutoriTableModel) table.getModel();
			model.rimuoviAutore(row);
			AutoriInLibroFrame.refreshPanels();
		}
	};
	
private static Action aggiungiAction = new AbstractAction() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			JTable table = (JTable) e.getSource(); 
			int row = Integer.valueOf(e.getActionCommand()); 
			AutoriTableModel model = (AutoriTableModel) table.getModel();
			model.aggiungiAutore(row);
			AutoriInLibroFrame.refreshPanels();
		}
	};

public static Action getRimuoviAction() {
	return rimuoviAction;
}

public static Action getAggiungiAction() {
	return aggiungiAction;
}

}
