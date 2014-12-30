package it.unisalento.view.Panels;

import it.unisalento.view.Models.ButtonColumn;
import it.unisalento.view.Models.CarrelloTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CarrelloJPanJTab {
	
	private static CarrelloTableModel model=new CarrelloTableModel();
	private JTable tab;
	private JScrollPane panel;
	
	public CarrelloJPanJTab(){
		tab=new JTable(model);
		panel= new JScrollPane(tab);
		@SuppressWarnings("unused")
		ButtonColumn rimuoviButton= new ButtonColumn(tab, CarrelloTableModel.getRimuoviAction(), 8); //Definisco il bottone, input: tabella, azione, colonna
	}
	
	
	public static void refresh(){
		model.fireTableDataChanged();
	}
	
	
	public CarrelloTableModel getModel() {
		return model;
	}
	public JTable getTab() {
		return tab;
	}
	public JScrollPane getPanel() {
		return panel;
	}

}
