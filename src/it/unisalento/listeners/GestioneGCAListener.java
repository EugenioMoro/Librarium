package it.unisalento.listeners;

import it.unisalento.business.Session;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Dialogs.NuovoModificaDialog;
import it.unisalento.view.Frames.GestioneGCAFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioneGCAListener implements ActionListener {
	
	public static final String MODIFICAGOPT="modificag";
	public static final String MODIFICACOPT="modificac";
	public static final String NUOVOGENEREOPT="nuovogenere";
	public static final String NUOVACASAOPT="nuovacasa";
	public final static String NUOVOAUTOREOPT="nuovoautore";
	public final static String ELIMINAGENEREOPT="eliminagenere";
	public final static String ELIMINACASAOPT="eliminacasa";
	
	private GestioneGCAFrame frame;
	

	public GestioneGCAListener(GestioneGCAFrame frame) {
		this.frame=frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		
		case MODIFICAGOPT: modificaGenereOpt();
		frame.refresh();
		break;
		
		case MODIFICACOPT: modificaCasaOpt();
		frame.refresh();
		break;
		
		case NUOVOGENEREOPT: nuovoGenereOpt();
		break;
		
		case NUOVACASAOPT: nuovaCasaOpt();
		break;
		
		case NUOVOAUTOREOPT: nuovoAutoreOpt();
		frame.refresh();
		break;
		
		default: MessageBoxes.errore("", "Errore option");
		}
		
	}
	
	private void modificaGenereOpt(){
		if(frame.getGenereComboBox().getSelectedIndex()==-1)
			MessageBoxes.alert("Attenzione", "Scegli un genere");
		else{
			NuovoModificaDialog dialog=new NuovoModificaDialog(Session.currentSession().getGeneri().get(frame.getGenereComboBox().getSelectedIndex()), NuovoModificaDialog.GENEREOPT);
			dialog.setVisible(true);
		}
	}
	
	private void modificaCasaOpt(){
		if(frame.getCasaComboBox().getSelectedIndex()==-1)
			MessageBoxes.alert("Attenzione", "Scegli un genere");
		else{
			NuovoModificaDialog dialog=new NuovoModificaDialog(Session.currentSession().getCaseEd().get(frame.getCasaComboBox().getSelectedIndex()), NuovoModificaDialog.CASAOPT);
			dialog.setVisible(true);
		}
	}
	
	private void nuovoGenereOpt(){
		NuovoModificaDialog dialog = new NuovoModificaDialog(NuovoModificaDialog.GENEREOPT);
		dialog.setVisible(true);
	}
	
	private void nuovaCasaOpt(){
		NuovoModificaDialog dialog = new NuovoModificaDialog(NuovoModificaDialog.CASAOPT);
		dialog.setVisible(true);
	}
	
	private void nuovoAutoreOpt(){
		NuovoModificaDialog dialog = new NuovoModificaDialog(NuovoModificaDialog.AUTOREOPT);
		dialog.setVisible(true);
	}

}
