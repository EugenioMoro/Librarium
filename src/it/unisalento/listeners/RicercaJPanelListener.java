package it.unisalento.listeners;

import it.unisalento.business.SearchEngine;
import it.unisalento.business.Session;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Frames.ComboboxTest;
import it.unisalento.view.Panels.RicercaJPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class RicercaJPanelListener implements ActionListener {
	
	private JTable tab;
	
	public RicercaJPanelListener(JTable tab) {
		this.tab=tab;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Ricerca a cascata
		JButton source=(JButton) e.getSource();

		if(source.getText().equals("Ricerca")){
			Session.currentSession().resetSearchResults(); //Si resetta il vettore 

			boolean continua=true;
			boolean effettuata=false;

			if (!RicercaJPanel.getTextField().getText().isEmpty()){
				continua=SearchEngine.getInstance().perTitolo(RicercaJPanel.getTextField().getText()); System.out.println("libro");
				effettuata=true;
			}
			if(RicercaJPanel.getGeneriCombo().getSelectedIndex()!=-1 && continua){
				continua=SearchEngine.getInstance().perGenere(Session.currentSession().getGeneri().get(RicercaJPanel.getGeneriCombo().getSelectedIndex())); System.out.println("genere");
				effettuata=true;
			}
			if(RicercaJPanel.getAutoriCombo().getSelectedIndex()!=-1 && continua){
				continua=SearchEngine.getInstance().perAutore(Session.currentSession().getAutori().get(RicercaJPanel.getAutoriCombo().getSelectedIndex())); System.out.println("autore"+Session.currentSession().getAutori().get(RicercaJPanel.getAutoriCombo().getSelectedIndex()).getCognome());
				effettuata=true;
			}
			if(RicercaJPanel.getCaseCombo().getSelectedIndex()!=-1 && continua){
				continua=SearchEngine.getInstance().perCasa(Session.currentSession().getCaseEd().get(RicercaJPanel.getCaseCombo().getSelectedIndex())); System.out.println("casa");
				effettuata=true;
			}
			
			
			if(!effettuata) MessageBoxes.alert("Attenzione", "Riempi almeno un campo di ricerca");
			else{
				if (!continua){
					MessageBoxes.alert("Attenzione", "La ricerca non ha prodotto alcun risultato\nProva ad allargare i campi di ricerca");
				}
				else{
					MessageBoxes.alert("Risultati", ""+Session.currentSession().getSearchResults().size());
					((AbstractTableModel)tab.getModel()).fireTableDataChanged();
					ComboboxTest.refreshTable();
//					JFrame frame=new JFrame();
//					frame.setVisible(true);
//					frame.add(new JScrollPane(tab));
				}
			}
		}
		else{
			Session.currentSession().resetSearchResults();
			((AbstractTableModel)tab.getModel()).fireTableDataChanged();
		}
	
	}

}
