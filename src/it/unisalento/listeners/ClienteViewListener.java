package it.unisalento.listeners;

import it.unisalento.business.MainActivity;
import it.unisalento.business.RichiesteManager;
import it.unisalento.view.Dialogs.ConfirmDialog;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Models.RichiesteTableModel;
import it.unisalento.view.Panels.RichiesteScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

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
		
		RichiesteManager.getInstance().setStoricoCliente();
		JFrame frame=new JFrame();
		RichiesteScrollPane panel=new RichiesteScrollPane(RichiesteTableModel.CLIENTEOPT);
		frame.add(panel.getScrollPane());
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	private void logOutOpt(){
		confirmDialog= new ConfirmDialog();
		confirmDialog.setVisible(true);
	}
	
	
}
