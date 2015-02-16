package it.unisalento.listeners;

import it.unisalento.business.RichiesteManager;
import it.unisalento.view.Dialogs.ConfirmDialog;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Frames.NuovoLibroFrame;
import it.unisalento.view.Frames.ScaffaliView;
import it.unisalento.view.Models.RichiesteTableModel;
import it.unisalento.view.Panels.RichiesteScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ScaffaliViewListener implements ActionListener {
	
	public final static String RICHIESTEOPT = "richieste";
	public final static String AUTORICASEOPT = "autoricase";
	public final static String NUOVOLIBROOPT = "nuovolibro";
	public final static String LOGOUTOPT = "logout";

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case RICHIESTEOPT: richiesteOpt();
		break;
		case AUTORICASEOPT: TODO();
		break;
		case NUOVOLIBROOPT: nuovoLibroOpt();
		break;
		case LOGOUTOPT: logoutOpt();
		break;
		}
		
	}
	
	private void TODO(){
		MessageBoxes.alert("", "TODO");
	}
	
	private void richiesteOpt(){
		//Deprecato, il set dello storico avviene al conteggio delle nuove richieste all'interno della view RichiesteManager.getInstance().setStorico();
		RichiesteManager.getInstance().setCountTo0();
		System.out.println(RichiesteManager.getInstance().getNuoveRichesteCount());
		ScaffaliView.refreshLabel();
		JFrame frame = new JFrame("Gestione Richieste");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		RichiesteScrollPane scrollPane = new RichiesteScrollPane(RichiesteTableModel.ADDETTOOPT);
		frame.add(scrollPane.getScrollPane());
		frame.pack();
		frame.setVisible(true);
	}
	
	private void logoutOpt(){
		ConfirmDialog logoutDialog= new ConfirmDialog();
		logoutDialog.setVisible(true);
	}
	
	private void nuovoLibroOpt(){
		NuovoLibroFrame frame=new NuovoLibroFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
}
