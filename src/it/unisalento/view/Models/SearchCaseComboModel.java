package it.unisalento.view.Models;

import it.unisalento.Model.CasaEd;
import it.unisalento.business.Session;

import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataListener;

public class SearchCaseComboModel extends DefaultComboBoxModel<String> implements ComboBoxModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<CasaEd> caseEd=Session.currentSession().getCaseEd();
	
	@Override
	public void addListDataListener(ListDataListener l) {
		super.addListDataListener(l);

	}

	@Override
	public String getElementAt(int index) {
		
		return caseEd.get(index).getNome();
	}

	@Override
	public int getSize() {
		
		return caseEd.size();
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
