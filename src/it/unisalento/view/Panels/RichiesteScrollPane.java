package it.unisalento.view.Panels;

import it.unisalento.business.Session;
import it.unisalento.view.Models.ButtonColumn;
import it.unisalento.view.Models.RichiesteTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class RichiesteScrollPane {

	
	
	private JScrollPane scrollPane;
	private JTable tab;
	private AbstractTableModel model;
	
	
	public RichiesteScrollPane(String option) {
		model=new RichiesteTableModel(option);
		tab=new JTable(model);
		tab.setAutoCreateRowSorter(true);
		if (option.equals(RichiesteTableModel.ADDETTOOPT)){
			//definisco i bottoni
			@SuppressWarnings("unused")
			ButtonColumn clienteButton= new ButtonColumn(tab, RichiesteTableModel.getDettagliClienteAction(), 1); //Definisco il bottone, input: tabella, azione, colonna
			@SuppressWarnings("unused")
			ButtonColumn libroButton= new ButtonColumn(tab, RichiesteTableModel.getDettagliLibroAction(), 2); //Definisco il bottone, input: tabella, azione, colonna
			@SuppressWarnings("unused")
			ButtonColumn aggiornaButton= new ButtonColumn(tab, RichiesteTableModel.getAggiornaAction(), 6); //Definisco il bottone, input: tabella, azione, colonna
		} else {
			@SuppressWarnings("unused")
			ButtonColumn libroButton= new ButtonColumn(tab, RichiesteTableModel.getDettagliLibroAction(), 1); //Definisco il bottone, input: tabella, azione, colonna

		}
		scrollPane=new JScrollPane(tab);
	}
	
	public void refresh(){
		Session.currentSession().aggiornaRichieste();
		model.fireTableDataChanged();
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	
}
