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
	public AutoriJpan(String option){
		model= new AutoriTableModel(option);
		tab=new JTable(model);
		pane= new JScrollPane(tab);
		
		switch (option){
		case AutoriTableModel.AGGIUNGIOPT: ButtonColumn aggiungiButton= new ButtonColumn(tab, AutoriTableModel.getAggiungiAction(), 2); //Definisco il bottone, input: tabella, azione, colonna
		break;
		case AutoriTableModel.INLIBROOPT: ButtonColumn rimuoviButton= new ButtonColumn(tab, AutoriTableModel.getRimuoviAction(), 2); //Definisco il bottone, input: tabella, azione, colonna
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
