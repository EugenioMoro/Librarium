package it.unisalento.view.Models;

import java.util.Vector;

import it.unisalento.Model.Autore;
import it.unisalento.business.Session;


import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.event.ListDataListener;

public class SearchAutoriComboModel extends DefaultComboBoxModel<String> implements ComboBoxModel<String> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<Autore> autori=Session.currentSession().getAutori();

	@Override
	public void addListDataListener(ListDataListener l) {
		super.addListDataListener(l);
	}

	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
		return autori.get(index).getNome().substring(0, 1)+". "+autori.get(index).getCognome();
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return autori.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		super.removeListDataListener(l);
		
	}

	@Override
	public Object getSelectedItem() {
		return super.getSelectedItem();
	}

	@Override
	public void setSelectedItem(Object anItem) {
		super.setSelectedItem(anItem);
	}
	

}
	
	
	

