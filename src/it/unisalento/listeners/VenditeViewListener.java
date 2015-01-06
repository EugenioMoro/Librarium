package it.unisalento.listeners;

import it.unisalento.business.CarrelloManager;
import it.unisalento.business.Session;
import it.unisalento.view.Dialogs.ConfirmDialog;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Dialogs.TesseraDialog;
import it.unisalento.view.Frames.VenditeView;
import it.unisalento.view.Models.AcquistiTableModel;
import it.unisalento.view.Models.ButtonColumn;
import it.unisalento.view.Panels.CarrelloJPanJTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VenditeViewListener implements ActionListener{
	
	public final static String STORICOOPT="storico";
	public final static String VENDIOPT="vendite";
	public final static String LOGOUTOPT="logout";
	public final static String SVUOTAOPT="svuota";
	public final static String OKOPT="ok";

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case LOGOUTOPT: logoutOpt();
		break;
		case STORICOOPT: storicoOpt();
		break;
		case SVUOTAOPT: svuotaOpt();
		break;
		case VENDIOPT: vendiOpt();
		break;
		default: MessageBoxes.alert("TODO", "");
		}
		
	}

	private void logoutOpt(){
		ConfirmDialog confirmDialog = new ConfirmDialog();
		confirmDialog.setVisible(true);
	}
	
	private void storicoOpt(){
		Session.currentSession().aggiornaAcquisti();
		JFrame frame= new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		AcquistiTableModel model= new AcquistiTableModel(AcquistiTableModel.ADDETTOOPT);
		JTable tab = new JTable(model);
		JScrollPane panel= new JScrollPane(tab);
		@SuppressWarnings("unused")
		ButtonColumn libriButton= new ButtonColumn(tab, AcquistiTableModel.getElencoAction(), 2); //Definisco il bottone, input: tabella, azione, colonna
		@SuppressWarnings("unused")
		ButtonColumn clienteButton= new ButtonColumn(tab, AcquistiTableModel.getDettagliAction(), 4); //Definisco il bottone, input: tabella, azione, colonna
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void svuotaOpt(){
		CarrelloManager.getInstance().svuota();
		VenditeView.aggiornaLabels();
		CarrelloJPanJTab.refresh();
	}
	
	private void vendiOpt(){
		JDialog dialog=new TesseraDialog();
		dialog.setVisible(true);
	}
	
	
}
