package it.unisalento.view.Panels;

import it.unisalento.view.Models.ButtonColumn;
import it.unisalento.view.Models.LibriTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LibriJPanJTab {
	
	private JScrollPane panel;
	private JTable tab;
	private LibriTableModel model;
	
	public LibriJPanJTab(String option){
		model=new LibriTableModel(option);
		tab=new JTable(model);
		panel= new JScrollPane(tab);
		
		switch (option){ //definisco i bottoni
		case LibriTableModel.CLIENTEOPT:{
			@SuppressWarnings("unused")
			ButtonColumn clienteButton= new ButtonColumn(tab, LibriTableModel.getOrdinaAction(), 8); //Definisco il bottone, input: tabella, azione, colonna
		}
		break;
		case LibriTableModel.SCAFFALIOPT:{
			@SuppressWarnings("unused")
			ButtonColumn scaffaliButton= new ButtonColumn(tab, LibriTableModel.getModificaAction(), 8); //Definisco il bottone, input: tabella, azione, colonna
		}
		break;
		case LibriTableModel.VENDITEOPT:{
			@SuppressWarnings("unused")
			ButtonColumn venditeButton= new ButtonColumn(tab, LibriTableModel.getCarrelloAction(), 8); //Definisco il bottone, input: tabella, azione, colonna
		}
		}
	}

	public void refresh(){
		model.fireTableDataChanged();
	}
	
	public JScrollPane getPanel() {
		return panel;
	}

	public void setPanel(JScrollPane panel) {
		this.panel = panel;
	}

	public JTable getTab() {
		return tab;
	}

	public void setTab(JTable tab) {
		this.tab = tab;
	}

	public LibriTableModel getModel() {
		return model;
	}

	public void setModel(LibriTableModel model) {
		this.model = model;
	}
	
}
