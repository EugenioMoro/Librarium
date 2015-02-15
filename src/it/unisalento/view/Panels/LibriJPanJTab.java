package it.unisalento.view.Panels;

import it.unisalento.view.Models.ButtonColumn;
import it.unisalento.view.Models.LibriTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LibriJPanJTab {
	
	private JScrollPane panel;
	private JTable tab;
	private static LibriTableModel model;
	
	@SuppressWarnings("unused")
	public LibriJPanJTab(String option){
		model=new LibriTableModel(option);
		tab=new JTable(model);
		tab.setAutoCreateRowSorter(true);
		panel= new JScrollPane(tab);
		
		switch (option){ //definisco i bottoni
		case LibriTableModel.CLIENTEOPT:{
			
			
			ButtonColumn clienteButton= new ButtonColumn(tab, LibriTableModel.getOrdinaAction(), 8); //Definisco il bottone, input: tabella, azione, colonna
			
		
		}
		break;
		case LibriTableModel.SCAFFALIOPT:{
			ButtonColumn titoloButton= new ButtonColumn(tab, LibriTableModel.getTitoloAction(), 0); //Definisco il bottone, input: tabella, azione, colonna
			//TODO bottone per autori
			ButtonColumn casaButton= new ButtonColumn(tab, LibriTableModel.getCasa(), 2); //Definisco il bottone, input: tabella, azione, colonna
			ButtonColumn genereButton= new ButtonColumn(tab, LibriTableModel.getGenere(), 3); //Definisco il bottone, input: tabella, azione, colonna
			ButtonColumn costoButton= new ButtonColumn(tab, LibriTableModel.getCosto(), 4); //Definisco il bottone, input: tabella, azione, colonna
			ButtonColumn pagineButton= new ButtonColumn(tab, LibriTableModel.getPagine(), 5); //Definisco il bottone, input: tabella, azione, colonna
			ButtonColumn isbnButton= new ButtonColumn(tab, LibriTableModel.getIsbn(), 6); //Definisco il bottone, input: tabella, azione, colonna
			ButtonColumn dispButton= new ButtonColumn(tab, LibriTableModel.getDisp(), 7); //Definisco il bottone, input: tabella, azione, colonna

		} 
		break; 
		case LibriTableModel.VENDITEOPT:{
			ButtonColumn venditeButton= new ButtonColumn(tab, LibriTableModel.getCarrelloAction(), 8); //Definisco il bottone, input: tabella, azione, colonna
		}
		}
	}

	public static void refresh(){
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

	
}
