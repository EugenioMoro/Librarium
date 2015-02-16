package it.unisalento.listeners;

import it.unisalento.Model.Libro;
import it.unisalento.business.ControlliCoerenza;
import it.unisalento.business.NuovoLibroManager;
import it.unisalento.business.Session;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Frames.AutoriInLibroFrame;
import it.unisalento.view.Frames.NuovoLibroFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class NuovoLibroListener implements ActionListener {
	
	public final static String AUTORIOPT="autori";
	public final static String SALVAOPT="salva";
	
	
	private NuovoLibroFrame frame;
	
	public NuovoLibroListener(NuovoLibroFrame frame) {
		this.frame=frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){

		case AUTORIOPT: aggiungiAutori();
		break;

		case SALVAOPT:{
			if (controlloDatiRaw()){
				if (!Session.currentSession().getLibroTemp().getAutori().isEmpty()){
					creaLibro();
					NuovoLibroManager.getInstance().salva();
					MessageBoxes.alert("", "Libro aggiunto al database");
					frame.dispose();
				} else MessageBoxes.alert("Attenzione", "Inserisci almeno un autore");
			}
		}
		break;
		}
	}

	private boolean controlloDatiRaw(){
		
		if (frame.getTitoloTxtField().getText().isEmpty()){
			MessageBoxes.alert("Attenzione", "Inserisci titolo");
			return false;
		} else if (!ControlliCoerenza.checkTitolo(frame.getTitoloTxtField().getText(), true))
			return false;
		
		if (frame.getGenereComboBox().getSelectedIndex()==-1){
			MessageBoxes.alert("Attenzione", "Scegli il genere");
			return false;
		}
		
		if (frame.getCasaComboBox().getSelectedIndex()==-1){
			MessageBoxes.alert("Attenzione", "Scegli la casa editrice");
			return false;
		}
		
		if (frame.getCostoTxtField().getText().isEmpty()){
			MessageBoxes.alert("Attenzione", "Inserisci il costo");
			return false;
		} else if(!ControlliCoerenza.checkCosto(frame.getCostoTxtField().getText(), true)) return false;
		
		if (frame.getDispTextField().getText().isEmpty()){
			MessageBoxes.alert("Attenzione", "Inserisci disponibilità");
			return false;
		} else if (!ControlliCoerenza.checkDisp(frame.getDispTextField().getText(), true)) return false;
		
		if (frame.getISBNTextField().getText().isEmpty()){
			MessageBoxes.alert("Attenzione", "Inserisci ISBN");
			return false;
		} else if(!ControlliCoerenza.checkISBN(frame.getISBNTextField().getText(), true)) return false;
		
		if (frame.getPagineTextField().getText().isEmpty()){
			MessageBoxes.alert("Attenzione", "Inserisci pagine");
			return false;
		} else if (!ControlliCoerenza.checkPagine(frame.getPagineTextField().getText(), true)) return false;
		
		return true;
		
		
	}
	
	private void creaLibro(){
		Libro l = Session.currentSession().getLibroTemp();
		
		l.setTitolo(frame.getTitoloTxtField().getText());
		l.setGenere(Session.currentSession().getGeneri().get(frame.getGenereComboBox().getSelectedIndex()));
		l.setCasaEd(Session.currentSession().getCaseEd().get(frame.getCasaComboBox().getSelectedIndex()));
		l.setCosto(Integer.parseInt(frame.getCostoTxtField().getText()));
		l.setDisp(Integer.parseInt(frame.getDispTextField().getText()));
		l.setIsbn(frame.getISBNTextField().getText());
		l.setPagine(Integer.parseInt(frame.getPagineTextField().getText()));
		
		Session.currentSession().setLibroTemp(l);
	}

	private void aggiungiAutori(){
		AutoriInLibroFrame frame=new AutoriInLibroFrame(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

}
