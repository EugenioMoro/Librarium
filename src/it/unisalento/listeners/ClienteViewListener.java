package it.unisalento.listeners;

import it.unisalento.business.MainActivity;
import it.unisalento.view.Dialogs.ConfirmDialog;
import it.unisalento.view.Dialogs.MessageBoxes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class ClienteViewListener implements ActionListener {
	
	public static final String ACQUISTIOPT="acquisti";
	public static final String DATIOPT="dati";
	public static final String RICHIESTEOPT="richieste";
	public static final String LOGOUTOPT="logout";
	private static JDialog confirmDialog;

	@Override
	public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()){
			case ACQUISTIOPT: acquistiOpt();
			break;
			case DATIOPT: datiOpt();
			break;
			case RICHIESTEOPT: richiesteOpt();
			break;
			case LOGOUTOPT: logOutOpt();
			break;
			case "Cancel": confirmDialog.dispose();
			break;
			case "OK": MainActivity.backToWelcome();
			break;
			default: MessageBoxes.errore("Errore", "Errore Sconosciuto");
			}

	}

	private void acquistiOpt(){
		MessageBoxes.alert("TODO", "Da implementare");
	}
	
	private void datiOpt(){
		MessageBoxes.alert("TODO", "Da implementare");
	}
	
	private void richiesteOpt(){
		MessageBoxes.alert("TODO", "Da implementare");
	}
	
	private void logOutOpt(){
		confirmDialog= new ConfirmDialog();
		confirmDialog.setVisible(true);
	}
	
	
}
