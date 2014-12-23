package it.unisalento.view.Models;

import it.unisalento.Model.Genere;
import it.unisalento.business.Session;

import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataListener;

public class SearchGenereComboModel extends DefaultComboBoxModel<String>
		implements ComboBoxModel<String> {
	
	Vector<Genere> generi=Session.currentSession().getGeneri();

	@Override
	public void addListDataListener(ListDataListener arg0) {
		super.addListDataListener(arg0);
	}

	@Override
	public String getElementAt(int arg0) {
	return generi.get(arg0).getNome();
	}

	@Override
	public int getSize() {
		return generi.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		super.removeListDataListener(arg0);
		
	}

	@Override
	public Object getSelectedItem() {
		return super.getSelectedItem();
	}

	@Override
	public void setSelectedItem(Object arg0) {
		super.setSelectedItem(arg0);
	}
	

}
