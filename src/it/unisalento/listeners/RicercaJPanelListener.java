package it.unisalento.listeners;

import it.unisalento.business.SearchEngine;
import it.unisalento.business.Session;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Panels.LibriJPanJTab;
import it.unisalento.view.Panels.RicercaJPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RicercaJPanelListener implements ActionListener {
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Ricerca a cascata
		JButton source=(JButton) e.getSource();

		if(source.getText().equals("Ricerca")){
			Session.currentSession().resetSearchResults(); //Si resetta il vettore 

			boolean continua=true;
			boolean effettuata=false;

			if (!RicercaJPanel.getTextField().getText().isEmpty()){
				continua=SearchEngine.getInstance().perTitolo(RicercaJPanel.getTextField().getText());
				effettuata=true;
			}
			if(RicercaJPanel.getGeneriCombo().getSelectedIndex()!=-1 && continua){
				continua=SearchEngine.getInstance().perGenere(Session.currentSession().getGeneri().get(RicercaJPanel.getGeneriCombo().getSelectedIndex())); 
				effettuata=true;
			}
			if(RicercaJPanel.getAutoriCombo().getSelectedIndex()!=-1 && continua){
				continua=SearchEngine.getInstance().perAutore(Session.currentSession().getAutori().get(RicercaJPanel.getAutoriCombo().getSelectedIndex())); 
				effettuata=true;
			}
			if(RicercaJPanel.getCaseCombo().getSelectedIndex()!=-1 && continua){
				continua=SearchEngine.getInstance().perCasa(Session.currentSession().getCaseEd().get(RicercaJPanel.getCaseCombo().getSelectedIndex()));
				effettuata=true;
			}
			
			
			if(!effettuata) MessageBoxes.alert("Attenzione", "Riempi almeno un campo di ricerca");
			else{
				if (!continua){
					MessageBoxes.alert("Attenzione", "La ricerca non ha prodotto alcun risultato\nProva ad allargare i campi di ricerca");
				}
				else{
					LibriJPanJTab.refresh();
				}
			}
		}
		else{
			Session.currentSession().resetSearchResults();
			RicercaJPanel.resetFields();
			LibriJPanJTab.refresh();
		}
	
	}

}
