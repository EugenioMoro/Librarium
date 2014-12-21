package it.unisalento.view.Panels;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import it.unisalento.view.Models.LibriTableModel;

public class LibriJPanJTab {
	
	private static JTable tab=new JTable(new LibriTableModel());
	private static JScrollPane scrollpane= new JScrollPane(tab);

	
	public static JScrollPane getPane(){
		return scrollpane;
		
	}
	
	public static JTable getTable(){
		return tab;
	}
	
	
}
