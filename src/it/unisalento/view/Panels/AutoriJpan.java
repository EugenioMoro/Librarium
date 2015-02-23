package it.unisalento.view.Panels;

import it.unisalento.view.Models.AutoriTableModel;
import it.unisalento.view.Models.ButtonColumn;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AutoriJpan {
	
	private AutoriTableModel model;
	private JTable tab;
	private JScrollPane pane;
	
	@SuppressWarnings("unused")
	public AutoriJpan(String option, boolean nuovoLibro){
		model= new AutoriTableModel(option, nuovoLibro);
		tab=new JTable(model);
		tab.setAutoCreateRowSorter(true);
		pane= new JScrollPane(tab);
		
		switch (option){
		case AutoriTableModel.AGGIUNGIOPT: ButtonColumn aggiungiButton= new ButtonColumn(tab, AutoriTableModel.getAggiungiAction(), 2); //Definisco il bottone, input: tabella, azione, colonna
		break;
		case AutoriTableModel.INLIBROOPT: ButtonColumn rimuoviButton= new ButtonColumn(tab, AutoriTableModel.getRimuoviAction(), 2); //Definisco il bottone, input: tabella, azione, colonna
		break;
		case AutoriTableModel.GESTIONEOPT: ButtonColumn modificaButton= new ButtonColumn(tab, AutoriTableModel.getModificaAction(), 2); //Definisco il bottone, input: tabella, azione, colonna
		ButtonColumn eliminaButton= new ButtonColumn(tab, AutoriTableModel.getEliminaAction(), 3); //Definisco il bottone, input: tabella, azione, colonna
		break;
		}
	}

	public JScrollPane getPane() {
		return pane;
	}
	
	public void refresh(){
		model.fireTableDataChanged();
	}
}
