package it.unisalento.view.Panels;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import it.unisalento.view.Models.LibriTableModel;

public class LibriJPanJTab {
	
	private static LibriJPanJTab instance;
	

	private JTable tab;
	private JScrollPane scrollpane;
	private AbstractTableModel model;

	private LibriJPanJTab() {
		model=new LibriTableModel();
		tab=new JTable(model);
		scrollpane=new JScrollPane(tab);
	}
	
	public static LibriJPanJTab getInstance(){
		if (instance==null)
			instance=new LibriJPanJTab();
		return instance;
	}
	public JTable getTab() {
		return tab;
	}

	public JScrollPane getScrollpane() {
		return scrollpane;
	}

	public AbstractTableModel getModel() {
		return model;
	}
}
