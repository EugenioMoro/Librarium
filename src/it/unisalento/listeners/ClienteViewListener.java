package it.unisalento.listeners;

import it.unisalento.business.MainActivity;
import it.unisalento.business.RichiesteManager;
import it.unisalento.business.Session;
import it.unisalento.view.Dialogs.ConfirmDialog;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Frames.DatiClienteFrame;
import it.unisalento.view.Models.AcquistiTableModel;
import it.unisalento.view.Models.ButtonColumn;
import it.unisalento.view.Models.RichiesteTableModel;
import it.unisalento.view.Panels.RichiesteScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ClienteViewListener implements ActionListener {
	
	public static final String ACQUISTIOPT="acquisti";
	public static final String DATIOPT="dati";
	public static final String RICHIESTEOPT="richieste";
	public static final String LOGOUTOPT="logout";
	private static JDialog confirmDialog;
	private static JFrame datiFrame;

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
		JFrame frame= new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		AcquistiTableModel model= new AcquistiTableModel(AcquistiTableModel.CLIENTEOPT);
		JTable tab = new JTable(model);
		JScrollPane panel= new JScrollPane(tab);
		@SuppressWarnings("unused")
		ButtonColumn clienteButton= new ButtonColumn(tab, AcquistiTableModel.getElencoAction(), 2); //Definisco il bottone, input: tabella, azione, colonna
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void datiOpt(){
		datiFrame= new DatiClienteFrame();
		datiFrame.setVisible(true);
	}
	
	private void richiesteOpt(){

		RichiesteManager.getInstance().setStoricoCliente();
		if (!Session.currentSession().getRichieste().isEmpty()){
			JFrame frame=new JFrame();
			RichiesteScrollPane panel=new RichiesteScrollPane(RichiesteTableModel.CLIENTEOPT);
			frame.add(panel.getScrollPane());
			frame.pack();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		} else MessageBoxes.alert("Attenzione", "Nessuna richiesta associata al tuo account");
	}

	private void logOutOpt(){
		confirmDialog= new ConfirmDialog();
		confirmDialog.setVisible(true);
	}
	
	public static void refreshDatiFrame(){
		datiFrame.dispose();
		datiFrame=new DatiClienteFrame();
		datiFrame.setVisible(true);
	}
	
	
}
