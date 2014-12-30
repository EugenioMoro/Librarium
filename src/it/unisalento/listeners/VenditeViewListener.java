package it.unisalento.listeners;

import it.unisalento.business.CarrelloManager;
import it.unisalento.view.Dialogs.ConfirmDialog;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Frames.VenditeView;
import it.unisalento.view.Models.AcquistiTableModel;
import it.unisalento.view.Models.ButtonColumn;
import it.unisalento.view.Panels.CarrelloJPanJTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VenditeViewListener implements ActionListener{
	
	public final static String STORICOOPT="storico";
	public final static String VENDIOPT="vendite";
	public final static String LOGOUTOPT="logout";
	public final static String SVUOTAOPT="svuota";

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case LOGOUTOPT: logoutOpt();
		break;
		case STORICOOPT: storicoOpt();
		break;
		case SVUOTAOPT: svuotaOpt();
		break;
		default: MessageBoxes.alert("TODO", "");
		}
		
	}

	private void logoutOpt(){
		ConfirmDialog confirmDialog = new ConfirmDialog();
		confirmDialog.setVisible(true);
	}
	
	private void storicoOpt(){
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
	
}
